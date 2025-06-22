// src\composables\useTriviaAPI.js

// handles opentdb fetch + parsing
// loads X categories w/ Y questions
// MUST : group questions by category (since opentdb returns flat list)

import { ref } from 'vue'

const API_BASE = 'https://opentdb.com/api.php' //
const CATEGORY_API = 'https://opentdb.com/api_category.php' // for random category selection
const QUESTIONS_PER_CATEGORY = 5 // adjustable (construct grid dynamically) hard set for Lab4

// sleep helper function
function sleep(ms) {
  return new Promise(r => setTimeout(r, ms))
}

export function useTriviaAPI(gameStore, numCategories = 4) {
  if (!gameStore) throw new Error('gameStore not provided')

  // reactive state
  const questionsByCategory = ref({}) // pulls from const
  const loading = ref(false) // state flaging
  const error = ref(null) // debugging
  const categoriesCount = ref(numCategories)

  async function fetchQuestions() {

    loading.value = true
    error.value = null
    questionsByCategory.value = {}

    // wipe old log
    gameStore.notificationLog.value.splice(0)

    try {
      // get all categories
      const categoryRes = await fetch(CATEGORY_API)
      const categoryData = await categoryRes.json()
      const allCategories = categoryData.trivia_categories

      // hardcoded avoiding categories
      const EXCLUDED_CATEGORY_IDS = [13, 21, 27, 30, 32]

      const filteredCategories = allCategories.filter(
        cat => !EXCLUDED_CATEGORY_IDS.includes(cat.id)
      )

      // randomly select categories
      const selected = getRandomCategories(filteredCategories, categoriesCount.value)

      // fetch each category’s questions
      for (const cat of selected) {
        await sleep(3000 * categoriesCount.value) // dumb long time

        const url = `${API_BASE}?amount=${QUESTIONS_PER_CATEGORY}&category=${cat.id}&type=boolean`

        // attempt fetch
        let res = await fetch(url)
        if (res.status === 429) { // too many requests
          await sleep(2000)
          res = await fetch(url)
        }

        const data = await res.json()
        if (data.response_code !== 0 || !data.results.length) {
          continue
        }

        // sanitize & assign dollar values
        questionsByCategory.value[cat.name] = data.results.map((q, i) => ({
          category:       q.category,
          type:           q.type,
          difficulty:     q.difficulty,
          question:       decodeHTMLEntities(q.question),
          correct_answer: decodeHTMLEntities(q.correct_answer),
          all_answers:    ["True", "False"],
          value:          getDollarValueByIndex(i)
        }))
      }
    } catch (err) {
      error.value = 'Failed to load questions.'
      console.error(err)
    } finally {
      loading.value = false
    }
  }

  // refetch w/ new count
  async function refetch(newCount) {
    categoriesCount.value = newCount
    await fetchQuestions()
    return questionsByCategory.value
  }

  return {
    fetchQuestions,
    refetch,
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
