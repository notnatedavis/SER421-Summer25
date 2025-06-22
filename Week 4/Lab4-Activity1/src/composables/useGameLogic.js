// src\composables\useGameLogic.js

// handles game state (scoring, player turn, answer validation)

import { inject } from 'vue'

export function useGameLogic() {
  const gameStore = inject('gameStore')
  if (!gameStore) { throw new Error('gameStore not provided') } // debugging

  function validateAnswer(submitted) {
    const question = gameStore.activeQuestion.value
    const player = gameStore.players[gameStore.currentPlayerIndex.value]

    if (!question || !question.correct_answer) {
      return
    }

    const correctAnswer = question.correct_answer

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

    gameStore.usedQuestions.push({
      category: question.category,
      row: question.rowIndex,
      playerName: player.name,
      correct: isCorrect
    })

    checkEndGame()

    // advance turn
    nextTurn()

    // clear active question w/ input (should disappear)
    gameStore.activeQuestion.value = null
  }

  function checkEndGame() {
    const totalQuestions =
      Object.values(gameStore.questionsByCategory.value)
        .reduce((sum, qList) => sum + qList.length, 0)

    if (gameStore.usedQuestions.length >= totalQuestions) {
      // find highest score
      const maxScore = Math.max(...gameStore.players.map(p => p.score))
      const winners = gameStore.players.filter(p => p.score === maxScore)
      const winnerNames = winners.map(w => w.name).join(', ')

      // clear all previous notifications
      gameStore.notificationLog.value.splice(0)

      // push winner
      gameStore.notificationLog.value.push({
        text: `Game Over! Winner: ${winnerNames} with ${maxScore} points.`,
        correct: true
      })
    }
  }

  function nextTurn() {
    gameStore.currentPlayerIndex.value =
      (gameStore.currentPlayerIndex.value + 1) % gameStore.players.length
  }

  return { validateAnswer, nextTurn }
}
