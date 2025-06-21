// src\composables\useTriviaAPI.js

// handles opentdb fetch + parsing
// loads X categories w/ Y questions
// MUST : group questions by category (since opentdb returns flat list)

import { ref } from 'vue'

const API_BASE = 'https://opentdb.com/api.php' //
const CATEGORY_API = 'https://opentdb.com/api_category.php' // for random category selection
const QUESTIONS_PER_CATEGORY = 5 // adjustable (construct grid dynamically)
const NUM_OF_CATEGORIES = 4

// sleep helper function
function sleep(ms) {
  return new Promise(r => setTimeout(r, ms))
}

export function useTriviaAPI(gameStore) {
  if (!gameStore) throw new Error('gameStore not provided')

  // reactive state
  const questionsByCategory = ref({}) // pulls from const
  const loading = ref(false) // state flaging
  const error = ref(null) // debugging

  async function fetchQuestions() {

    loading.value = true
    error.value = null
    questionsByCategory.value = {}

    // wipe old log
    gameStore.notificationLog.value.splice(0)

    // DEBUGGING
    gameStore.notificationLog.value.push({
      text: `DEBUG: Fetching category list from ${CATEGORY_API}`,
      correct: true
    })

    try {
      // get all categories
      const categoryRes = await fetch(CATEGORY_API)
      // DEBUGGING
      gameStore.notificationLog.value.push({
        text: `DEBUG: Received status ${categoryRes.status} from category API`,
        correct: true
      })
      const categoryData = await categoryRes.json()
      const allCategories = categoryData.trivia_categories

      // randomly select 4 categories
      const selected = getRandomCategories(allCategories, NUM_OF_CATEGORIES)
      // DEBUGGING
      gameStore.notificationLog.value.push({
        text: `DEBUG: Selected categories: ${selected.map(c=>c.name).join(', ')}`,
        correct: true
      })

      // 3) Sequentially fetch each category’s questions
      for (const cat of selected) {
        await sleep(5000)

        const url = `${API_BASE}?amount=${QUESTIONS_PER_CATEGORY}&category=${cat.id}&type=multiple`
        gameStore.notificationLog.value.push({ text: `DEBUG: Fetching ${url}`, correct: true })

        // attempt fetch
        let res = await fetch(url)
        if (res.status === 429) {
          gameStore.notificationLog.value.push({
            text: `WARN: 429 for ${cat.name}, retrying after delay…`,
            correct: false
          })
          await sleep(2000)
          res = await fetch(url)
        }

        gameStore.notificationLog.value.push({
          text: `DEBUG: ${cat.name} fetch status ${res.status}`,
          correct: true
        })

        const data = await res.json()
        if (data.response_code !== 0 || !data.results.length) {
          gameStore.notificationLog.value.push({
            text: `WARN: No questions for ${cat.name}`,
            correct: false
          })
          continue
        }

        gameStore.notificationLog.value.push({
          text: `DEBUG: ${data.results.length} questions loaded for ${cat.name}`,
          correct: true
        })

        // sanitize & assign dollar values
        questionsByCategory.value[cat.name] = data.results.map((q, i) => ({
          category:       q.category,
          type:           q.type,
          difficulty:     q.difficulty,
          question:       decodeHTMLEntities(q.question),
          correct_answer: decodeHTMLEntities(q.correct_answer),
          all_answers:    shuffle([
            ...q.incorrect_answers.map(a => decodeHTMLEntities(a)),
            decodeHTMLEntities(q.correct_answer)
          ]),
          value:          getDollarValueByIndex(i)
        }))
      }
    } catch (err) {
      error.value = 'Failed to load trivia categories/questions.'
      console.error(err)
      // DEBUGGING
      gameStore.notificationLog.value.push({ text: `ERROR: ${err.message}`, correct: false })
    } finally {
      loading.value = false
      // DEBUGGING
      gameStore.notificationLog.value.push({ text: `DEBUG: fetchQuestions() complete`, correct: true })
    }
  }

  return {
    fetchQuestions,
    questionsByCategory,
    loading,
    error,
  }
}

// --- Helpers --- //

// helper function that decodes HTML entities w/ textarea
function decodeHTMLEntities(str) {
  const txt = document.createElement('textarea')
  txt.innerHTML = str
  return txt.value
}

// helper function for shuffling array
function shuffle(array) {
  let currentIndex = array.length
  while (currentIndex !== 0) {
    const randomIndex = Math.floor(Math.random() * currentIndex)
    currentIndex--
    ;[array[currentIndex], array[randomIndex]] = [array[randomIndex], array[currentIndex]]
  }
  return array
}

// helper function for retrieving random categories
function getRandomCategories(categories, count) {
  const shuffled = shuffle([...categories])
  return shuffled.slice(0, count)
}

// helper function for retrieving associated dollar values
function getDollarValueByIndex(index) {
  const values = [100, 200, 300, 400, 500]
  return values[index] || 100 // 100 is default
}
