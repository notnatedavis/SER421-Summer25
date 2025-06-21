<!-- src\views\JeopardyGame.vue -->

<!-- handles main game page hosting board & players -->
<!-- make sure to call + use useTriviaAPI.js -->

<script setup>
import { provide, onMounted } from 'vue'
import { createGameStore } from '@/store/gameStore'
import { useTriviaAPI } from '@/composables/useTriviaAPI'
import GameBoard from '@/components/GameBoard.vue'

// create + provide store
const gameStore = createGameStore()
provide('gameStore', gameStore)

// fetch trivia questions
const { fetchQuestions, questionsByCategory, loading, error } = useTriviaAPI()

onMounted(async () => {
  await fetchQuestions()
  gameStore.setQuestionsByCategory(questionsByCategory.value)
})
</script>

<template>
  <!-- HTML here -->
  <div class="game-container">
    <h1>Jeopardy</h1>

    <div v-if="loading">Loading trivia questions . . .</div>

    <div v-else-if="error">{{ error }}</div>

    <GameBoard v-else />
  </div>
</template>

<style scoped>
/* css here */
.game-container {
  text-align: center;
  padding: 2rem;
}
</style>
