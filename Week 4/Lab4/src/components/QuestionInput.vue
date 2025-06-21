<!-- src\components\QuestionInput.vue -->

<!-- handles showing questions + input inline -->
<!-- display active question text -->
<!-- provide input field for response, on submit call parseAnswer() + useGameLogic.validateAnswer() -->
<!-- update gameStore w/ result + NotificationLog update -->

<template>
  <!-- HTML here -->
  <div class="question-input" v-if="active.value">
    <p class="prompt">{{ decodeURIComponent(active.value.category) }} for ${{ active.value.value }} : {{ active.value.question }}</p>
    <ul class="all-available-answers">
      <li v-for="(ans, i) in active.value.all_answers" :key="i">{{ ans }}</li>
    </ul>
    <input
      v-model="rawAnswer"
      @keyup.enter="submit"
      placeholder="Who/What/Where is ..."
    />
    <button @click="submit">Submit</button>
  </div>
</template>

<script setup>
// js here
import { ref, computed, inject } from 'vue'
import { parseAnswer } from '@/utils/parseAnswer'
import { useGameLogic } from '@/composables/useGameLogic'

// grab store
const gameStore = inject('gameStore')
if (!gameStore) throw new Error('gameStore not provided')

const { validateAnswer } = useGameLogic()
const active = computed(() => gameStore.activeQuestion)
const rawAnswer = ref('')

function submit() {
  if (!rawAnswer.value) { return }

  const cleaned = parseAnswer(rawAnswer.value)
  validateAnswer(cleaned)
  rawAnswer.value = ''
}
</script>

<style scoped>
/* css here */
.question-input { /* basic layout */}
.prompt { font-weight: bold; }
.all-available-answers { margin: 0.5rem 0; }
</style>
