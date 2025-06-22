<!-- src\components\GameBoard.vue -->

<!-- handles rendering main Jeopardy table/canvas -->
<!-- clickable (questions) grid -->
<!-- top row is categories with questions corresponding to each -->
<!-- when a cell is clicked set activeQuestion & render QuestionInput.vue below board -->
<!-- on submit parse answer + validate -->
<!-- disable already clicked cells , styling clicked cells as 'used' -->
<!-- each question has a .value field -->
<!-- presentation only no data fetching -->

<template>
  <!-- HTML here -->
  <div class="game-board">
    <table>
      <thead>
        <tr>
          <th v-for="(category, index) in categories" :key="index">
            {{ decodeURIComponent(category) }}
          </th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="row in questionRows" :key="row">
          <td
            v-for="(category, colIndex) in categories"
            :key="colIndex"
            :class="{ used: isUsed(category, row) }"
            @click="handleCellClick(category, row)"
          >
            <template v-if="getUsedData(category, row)">
              <span :style="{ color: getUsedData(category, row).correct ? 'limegreen' : 'crimson' }">
                {{ getUsedData(category, row).playerName }}
              </span>
            </template>

            <template v-else>
              ${{ getCellValue(category, row) }}
            </template>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
// js here

import { computed, inject, toRefs } from 'vue'

// props + global store
const gameStore = inject('gameStore')
if (!gameStore) throw new Error('gameStore not provided')

const { questionsByCategory } = toRefs(gameStore)
const setActiveQuestion = gameStore.setActiveQuestion
const isUsed = gameStore.isUsed

// load categories dynamically from fetched questions
const categories = computed(() => Object.keys(questionsByCategory.value))

// hardcoded questions per category (rn @ 5)
const questionRows = [0, 1, 2, 3, 4]

// --- Helper Functions --- //

// function for click cell action from player
function handleCellClick(category, rowIndex) {
  if (isUsed(category, rowIndex)) {
    return
  }

  const question = questionsByCategory.value[category][rowIndex]
  setActiveQuestion(category, rowIndex, question)
}

// function for retrieving associated value of cell
function getCellValue(category, rowIndex) {
  const question = questionsByCategory.value[category][rowIndex]
  return question ? question.value : ''
}

// function for rendering cells as used or not
function getUsedData(category, rowIndex) {
  const match = gameStore.usedQuestions.find(
    q => q.category === category && q.row === rowIndex
  )
  return match
}

</script>

<style scoped>
/* css here */

.game-board {
  margin: 2rem auto;
  max-width: 800px;
  text-align: center;
}

th,
td {
  padding: 1rem;
  border: 2px solid white;
  background-color: var(--primary, #677b8f);
  color: white;
  cursor: pointer;
  font-weight: bold;
}

.used {
  background-color: #736a6a;
  color: #999;
  cursor: default;
}
</style>
