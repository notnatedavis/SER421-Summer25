// src\composables\useTriviaAPI.js

// handles opentdb fetch + parsing
// loads X categories w/ Y questions
// MUST : group questions by category (since opentdb returns flat list)

import { ref } from 'vue' //

const API_BASE = 'https://opentdb.com/api.php' //
const CATEGORY_API = 'https://opentdb.com/api_category.php' // for random category selection
const QUESTIONS_PER_CATEGORY = 5 // adjustable (construct grid dynamically)
const NUM_OF_CATEGORIES = 4

export function useTriviaAPI() {
  const questionsByCategory = ref({}) // pulls from const
  const loading = ref(false) // state flaging
  const error = ref(null) // debugging

  async function fetchQuestions() {
    loading.value = true
    error.value = null
    questionsByCategory.value = {}

    //
    try {
      // get all categories
      const categoryRes = await fetch(CATEGORY_API)
      const categoryData = await categoryRes.json()
      const allCategories = categoryData.trivia_categories

      // randomly select 4 categories
      const selected = getRandomCategories(allCategories, NUM_OF_CATEGORIES)

      // fetch questions for each selected category
      const fetches = selected.map(
        (category) =>
          fetch(
            `${API_BASE}?amount=${QUESTIONS_PER_CATEGORY}&category=${category.id}&type=multiple`,
          )
            .then((res) => res.json())
            .then((data) => ({ category: encodeURIComponent(category.name), data })), // encodeURIComponent as safety catch
      )

      const results = await Promise.all(fetches)

      //
      results.forEach(({ category, data }) => {
        if (data.response_code === 0 && data.results.length) {
          const cleanQuestions = data.results.map((q, index) => ({
            category: q.category,
            type: q.type,
            difficulty: q.difficulty,
            question: decodeHTMLEntities(q.question),
            correct_answer: decodeHTMLEntities(q.correct_answer),
            all_answers: shuffle([
              ...q.incorrect_answers.map((a) => decodeHTMLEntities(a)),
              decodeHTMLEntities(q.correct_answer),
            ]),
            value: getDollarValueByIndex(index),
          }))

          questionsByCategory.value[category] = cleanQuestions
        } else {
          console.warn(`Not enough questions in category: ${category}`)
        }
      })
    } catch (err) {
      error.value = 'Failed to load trivia categories/questions.'
      console.error(err)
    } finally {
      loading.value = false
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
