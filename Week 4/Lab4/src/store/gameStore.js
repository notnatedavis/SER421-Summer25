// src\store\gameStore.js

// handles game-wide state (provide/inject)
// gameStore defines : questionsByCategory, setActiveQuestion(category, rowIndex, question), isUsed(category, rowIndex)
// implement useGameStore()

import { reactive, ref } from 'vue'

export function createGameStore() {
  const questionsByCategory = ref({})
  const activeQuestion = ref(null)
  const usedQuestions = reactive([])

  function isUsed(category, rowIndex) {
    return usedQuestions.some((q) => q.category === category && q.row === rowIndex)
  }
  function setQuestionsByCategory(data) {
    questionsByCategory.value = data
  }

  function setActiveQuestion(category, rowIndex, question) {
    activeQuestion.value = {
      category,
      rowIndex,
      ...question,
    }

    if (!isUsed(category, rowIndex)) {
      usedQuestions.push({ category, row: rowIndex })
    }
  }

  return {
    questionsByCategory,
    activeQuestion,
    usedQuestions,
    setQuestionsByCategory,
    setActiveQuestion,
    isUsed,
  }
}
