// src\composables\useGameLogic.js

// handles game state (scoring, player turn, answer validation)
// define : validateAnswer, nextTurn, updateScore, etc.

import { inject } from 'vue'

export function useGameLogic() {
  const gameStore = inject('gameStore')
  if (!gameStore) { throw new Error('gameStore not provided') } // debugging

  function validateAnswer(submitted) {
    const question = gameStore.activeQuestion.value
    const player = gameStore.players[gameStore.currentPlayerIndex.value]

    console.log('[DEBUG] activeQuestion before check:', question)

    if (!question || !question.correct_answer) {
      gameStore.notificationLog.value.push({
        text: `ERROR: No active question or missing answer.`,
        correct: false
      })
      return
    }

    const correctAnswer = question.correct_answer
    console.log('correctAnswer:', correctAnswer)
    console.log('submitted:', submitted)

    // compare case-insensitive
    const isCorrect = correctAnswer.toLowerCase() === submitted.toLowerCase()
    const delta = question.value

    // update score
    player.score += isCorrect ? delta : -delta

    // log update
    gameStore.notificationLog.value.push({
      text: `${player.name} answered "${submitted}" - ${isCorrect ? 'Correct' : 'Incorrect'} ${isCorrect ? '+' : '-'}${delta}`,
      correct: isCorrect
    })

    // advance turn
    nextTurn()

    // clear active question w/ input (should disappear)
    gameStore.activeQuestion.value = null
  }

  function nextTurn() {
    gameStore.currentPlayerIndex.value =
      (gameStore.currentPlayerIndex.value + 1) % gameStore.players.length
  }

  return { validateAnswer, nextTurn }
}
