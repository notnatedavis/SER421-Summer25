// src\store\gameStore.js

// handles game-wide state (provide/inject)

import { reactive, ref } from 'vue'

export function createGameStore() {
  // fetched questions
  const questionsByCategory = ref({})
  // currently selected questions
  const activeQuestion = ref(null)
  // cells used
  const usedQuestions = reactive([])

  // players : id, name, score
  const players = reactive([
    { id: 1, name: 'Player 1', score: 0 },
    { id: 2, name: 'Player 2', score: 0 },
    { id: 3, name: 'Player 3', score: 0 }
  ])

  // index into players
  const currentPlayerIndex = ref(0)
  // event log entries : { text, correct }
  const notificationLog = ref([])

  function isUsed(category, rowIndex) {
    return usedQuestions.some((q) => q.category === category && q.row === rowIndex)
  }
  function setQuestionsByCategory(data) {
    questionsByCategory.value = data
  }

  function setActiveQuestion(category, rowIndex, question) {
    activeQuestion.value = {
      ...question,
      category,
      rowIndex
    }
  }

  return {
    // state
    questionsByCategory,
    activeQuestion,
    usedQuestions,
    players,
    currentPlayerIndex,
    notificationLog,

    // actions
    setQuestionsByCategory,
    setActiveQuestion,
    isUsed,
  }
}
