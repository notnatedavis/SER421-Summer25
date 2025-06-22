<!-- src\components\PlayerScore.vue -->

<!-- handles showing the score & name for each player -->

<template>
  <!-- HTML here -->
  <div class="player-score" :class="{ active: isActive }">
    <span class="name">{{ player.name }}</span>
    <span class="score">${{ player.score }}</span>
  </div>
</template>

<script setup>
// js here

import { computed, inject } from 'vue'

// grab props + game store
const props = defineProps({ player: Object })
const gameStore = inject('gameStore')
if (!gameStore) throw new Error('gameStore not provided')

const isActive = computed(() =>
  gameStore.currentPlayerIndex.value === (props.player.id - 1)
)
</script>

<style scoped>
/* css here */
.player-score {
  padding: 0.5rem;
  border: 2px solid transparent;
}
.player-score.active {
  color: green;
  font-weight: bold;
  border-color: green;
}
.name {
  font-weight: bold;
  margin-right: 0.5rem;
}
</style>
