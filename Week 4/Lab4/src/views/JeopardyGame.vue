<!-- src\views\JeopardyGame.vue -->

<!-- handles main game page hosting board & players -->
<!-- make sure to call + use useTriviaAPI.js -->

<script setup>
import { provide, onMounted } from 'vue'
import { createGameStore } from '@/store/gameStore'
import { useTriviaAPI } from '@/composables/useTriviaAPI'

import GameBoard from '@/components/GameBoard.vue'
import QuestionInput from '@/components/QuestionInput.vue'
import NotificationLog from '@/components/NotificationLog.vue'
import PlayerScore from '@/components/PlayerScore.vue'

// 1. create + provide store
const gameStore = createGameStore()
provide('gameStore', gameStore)

// 2. fetch trivia questions
const { fetchQuestions, questionsByCategory, loading, error } = useTriviaAPI()

// 3. populate store with questions
onMounted(async () => {
  await fetchQuestions()
  gameStore.setQuestionsByCategory(questionsByCategory.value)
})
</script>

<template>
  <!-- HTML here -->
  <div class="game-container">
    <h1>Jeopardy</h1>

    <!-- loading / error states -->
    <div v-if="loading">Loading trivia questions . . .</div>
    <div v-else-if="error">{{ error }}</div>

    <!-- main layout -->
    <div v-else class="board-and-controls">

      <aside class="scores">
        <PlayerScore
          v-for="p in gameStore.players"
          :key="p.id"
          :player="p"
        />
      </aside>

      <section class="board">
        <GameBoard />
        <QuestionInput />
      </section>

      <aside class="log">
        <NotificationLog />
      </aside>

    </div>

  </div>
</template>

<style scoped>
/* css here */
.game-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 2rem;
}

.board-and-controls {
  display: grid;
  grid-template-columns: 200px 1fr 300px;
  gap: 1rem;
  width: 100%;
  max-width: 1200px;
}

.scores {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.board {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.log {
  /* update at some point */
}
</style>
