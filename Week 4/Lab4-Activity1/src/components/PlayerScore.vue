<!-- src\components\PlayerScore.vue -->

<!-- handles showing the score & name for each player -->
<!-- display 3 player scores -->
<!-- accept 'player' prop w/ {id, name, score} -->
<!-- display all player's name + score , highlight current player (visual queue) -->

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
