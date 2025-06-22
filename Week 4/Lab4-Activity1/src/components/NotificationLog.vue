<!-- src\components\NotificationLog.vue -->

<!-- handles rolling log of game events -->
<!-- inject/consume gameStore.notificationLog -->
<!-- render scrolling list of events , "Player 1 answered 'Paris' - Correct! +200" -->
<!-- update format/color ? -->

<template>
  <!-- HTML here -->
  <div class="notification-log">
    <h3>Game Log</h3> <!-- DEBUGGING -->
    <ul>
      <li
        v-for="(entry, i) in log"
        :key="i"
        :class="{ correct: entry.correct, incorrect: !entry.correct }"
      >{{ entry.text }}
      </li>
    </ul>
  </div>
</template>

<script setup>
// js here
import { inject, computed } from 'vue'

// grab store
const gameStore = inject('gameStore')
if (!gameStore) throw new Error('gameStore not provided')

const log = computed(() => gameStore.notificationLog.value)
</script>

<style scoped>
/* css here */
.notification-log { max-height: 500px; overflow-y: auto; }
.correct { color: green; }
.incorrect { color: red; }
</style>
