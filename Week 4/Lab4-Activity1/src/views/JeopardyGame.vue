<!-- src\views\JeopardyGame.vue -->

<!-- handles main game page hosting board & players -->

<script setup>
import { /*computed,*/ provide, onMounted } from 'vue'
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
const { fetchQuestions, questionsByCategory, loading, error } = useTriviaAPI(gameStore)

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

    <!-- 1. player stats row -->
    <div v-if="!loading && !error" class="stats-row">
      <PlayerScore
        v-for="p in gameStore.players"
        :key="p.id"
        :player="p"
      />
    </div>

    <!-- 2. loading / error states -->
    <div v-if="loading">Loading trivia questions . . .</div>
    <div v-else-if="error">{{ error }}</div>

    <!-- 3. Jeopardy grid -->
    <div v-if="!loading && !error" class="board-row">
      <GameBoard />
    </div>

    <!-- 4. Question Input -->
    <QuestionInput v-if="!loading && !error" />

    <!-- 5. Game Log -->
    <NotificationLog />

  </div>
</template>

<style scoped>
/* css here */
.game-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 2rem;
  gap: 1.5rem;
}

/* 1. stats row : horizontal lay out of players */
.stats-row {
  display: flex;
  gap: 2rem;
}

/* 3. jeopardy grid */
.board-row {
  width: 100%;
  display: flex;
  justify-content: center;
}

/* 2. states */
.loading {
  font-style: italic;
}
.error {
  color: var(--danger, red);
}

.log {
  /* update at some point */
}
</style>
