<!-- src\views\JeopardyGame.vue -->

<!-- handles main game page hosting board & players -->

<script setup>
import { ref, watch, provide, onMounted } from 'vue'
import { createGameStore } from '@/store/gameStore'
import { useTriviaAPI } from '@/composables/useTriviaAPI'

import GameBoard from '@/components/GameBoard.vue'
import QuestionInput from '@/components/QuestionInput.vue'
import NotificationLog from '@/components/NotificationLog.vue'
import PlayerScore from '@/components/PlayerScore.vue'

// reactive dropdowns for #ofP & #ofC
const numPlayers = ref(3)
const numCategories = ref(4)

// 1. create + provide store
let gameStore = createGameStore(numPlayers.value)
provide('gameStore', gameStore)

// 2. fetch trivia questions
const { fetchQuestions, refetch, questionsByCategory, loading, error } = useTriviaAPI(gameStore, numCategories.value)

// 3. populate store with questions
onMounted(async () => {
  await fetchQuestions()
  gameStore.setQuestionsByCategory(questionsByCategory.value)
})

// 4. watch for updates
watch(numPlayers, newCount => {
  gameStore = createGameStore(newCount)
  provide('gameStore', gameStore)
  // refetch
  fetchQuestions().then(data => gameStore.setQuestionsByCategory(data))
})
watch(numCategories, newCount => {
  refetch(newCount).then(data => gameStore.setQuestionsByCategory(data))
})

</script>

<template>
  <!-- HTML here -->
  <div class="game-container">
    <h1>Jeopardy</h1>

    <!-- 1. configuration row -->
    <div class="config-row">
      <label>
        Players:
        <select v-model="numPlayers">
          <option v-for="n in [2,3,4,5,6]" :key="n" :value="n">{{ n }}</option>
        </select>
      </label>

      <label>
        Categories:
        <select v-model="numCategories">
          <option v-for="n in [2,3,4,5,6]" :key="n" :value="n">{{ n }}</option>
        </select>
      </label>
    </div>

    <!-- 2. stats row -->
    <div v-if="!loading && !error" class="stats-row">
      <PlayerScore
        v-for="p in gameStore.players"
        :key="p.id"
        :player="p"
      />
    </div>

    <!-- 3. loading / error states -->
    <div v-if="loading">Loading trivia questions (takes a LONG while) . . .</div>
    <div v-else-if="error">{{ error }}</div>

    <!-- 4. Jeopardy grid -->
    <div v-if="!loading && !error" class="board-row">
      <GameBoard />
    </div>

    <!-- 5. Question Input -->
    <QuestionInput v-if="!loading && !error" />

    <!-- 6. Game Log -->
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

/* 2. stats row : horizontal lay out of players */
.stats-row {
  display: flex;
  gap: 2rem;
}

/* 4. jeopardy grid */
.board-row {
  width: 100%;
  display: flex;
  justify-content: center;
}

/* 3. states */
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
