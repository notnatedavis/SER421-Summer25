// src\composables\useGameLogic.js

// handles game state (scoring, player turn, answer validation)
// define : validateAnswer, nextTurn, updateScore, etc.

import { inject } from 'vue'

export function useGameLogic() {
  const gameStore = inject('gameStore')
  if (!gameStore) { throw new Error('gameStore not provided') } // debugging

  function validateAnswer(correctAnswer, submitted) {
    const player = gameStore.players[gameStore.currentPlayerIndex]
    const question = gameStore.activeQuestion

    // compare case-insensitive
    const isCorrect = correctAnswer.toLowerCase() === submitted.toLowerCase()
    const delta = question.value

    // update score
    player.score += isCorrect ? delta : -delta

    // log update
    gameStore.notificationLog.push({
      text: `${player.name} answered "${submitted}" - ${isCorrect ? 'Correct' : 'Incorrect'} ${isCorrect ? '+' : '-'}${delta}`,
      correct: isCorrect
    })

    // advance turn
    nextTurn()

    // clear active question w/ input (should disappear)
    gameStore.activeQuestion = null
  }

  function nextTurn() {
    gameStore.currentPlayerIndex =
      (gameStore.currentPlayerIndex + 1) % gameStore.players.length
  }

  return { validateAnswer, nextTurn }
}
