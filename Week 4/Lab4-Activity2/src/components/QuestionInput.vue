<!-- src\components\QuestionInput.vue -->

<!-- handles showing questions + input inline -->
<!-- display active question text -->

<template>
  <!-- HTML here -->
  <div v-if="active.value">
    <p class="prompt">
      {{ decodeURIComponent(active.value.category) }}
      for
      <strong v-if="!active.value.isDouble">
        ${{ active.value.value }}
      </strong>
      <strong v-else>
        Double Jeopardy! Wager up to ${{ maxWager }}
      </strong>
    </p>

    <!-- 'Double Jeopardy' wager input -->
    <div v-if="active.value.isDouble && active.value.wager === null">
      <input
        type="number"
        v-model.number="wagerEntry"
        :min="0"
        :max="maxWager"
        placeholder="Enter your wager"
      />
      <button @click="lockWager">Lock Wager</button>
    </div>

    <!-- (once wager locked) show True / False buttons -->
    <div class="boolean-buttons" v-else>
      <button @click="submit('True')">True</button>
      <button @click="submit('False')">False</button>
    </div>
  </div>
</template>

<script setup>
// js here
import { ref, computed, inject } from 'vue'
import { useGameLogic } from '@/composables/useGameLogic'

// grab store
const gameStore = inject('gameStore')
if (!gameStore) throw new Error('gameStore not provided')

const { validateAnswer } = useGameLogic()
const active = computed(() => gameStore.activeQuestion)

const wagerEntry = ref(0)

// max wager either current score or question value
const maxWager = computed(() => {
  const player = gameStore.players[gameStore.currentPlayerIndex.value]
  return Math.min(player.score, active.value.value)
})

function lockWager() {
  // clamp values
  const w = Math.max(0, Math.min(maxWager.value, wagerEntry.value))
  gameStore.activeQuestion.value.wager = w
}

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
