<!-- src\components\QuestionInput.vue -->

<!-- handles showing questions + input inline -->
<!-- display active question text -->
<!-- provide input field for response, on submit call parseAnswer() + useGameLogic.validateAnswer() -->
<!-- update gameStore w/ result + NotificationLog update -->

<template>
  <!-- HTML here -->
  <div class="question-input" v-if="active.value">
    <p class="prompt">{{ decodeURIComponent(active.value.category) }} for ${{ active.value.value }} : {{ active.value.question }}</p>

    <!-- show True / False buttons -->
    <div class="boolean-buttons">
      <button @click="submit('True')">True</button>
      <button @click="submit('False')">False</button>
    </div>
  </div>
</template>

<script setup>
// js here
import { computed, inject } from 'vue'
import { useGameLogic } from '@/composables/useGameLogic'

// grab store
const gameStore = inject('gameStore')
if (!gameStore) throw new Error('gameStore not provided')

const { validateAnswer } = useGameLogic()
const active = computed(() => gameStore.activeQuestion)

function submit(answer) {
  validateAnswer(answer)
}
</script>

<style scoped>
/* css here */
.prompt { font-weight: bold; }
.boolean-buttons {
  display: flex;
  justify-content: center;
  gap: 1rem;
  margin-top: 1rem;
}
.boolean-buttons button {
  padding: 0.5rem 1rem;
  font-weight: bold;
}
</style>
