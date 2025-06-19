<!-- Activity 2 : SFC for Simple Survey -->
<!-- ndavispe, Lab3, 6/13/25 -->
<!-- (https://play.vuejs.org/#eNqdV1tPKzcQ/ivDSj3hljtQaU+CStGp1OoIWuDliPDg7DpZn2zsxfbmchD/vWN7L84mtGp5gPXMeOabzzNj8xbcZFlnldMgDEZH7TbcRJqtmN7CAEJ4/O0WZkLCI1tmKYXHXK7oFtrt6wm3xjwmK6Yyeg5fyXR4Dlfd/rA7uPQsjk/cApeaohOiKa4ARjFbQZQSpcaTQFm/7UhwTRinchLYHYA/1olKxBrWCUMIrzn7AXf3TxAJA0lT596aGperNpuhxyOmbgsDz1nh7mHQqXehLBnUSCIhaTuhJC5QOBOAbyKXEOVSUq7BWoXw9ua+4P0dRK5BzIzoNadKM8FVJ6V8rhPUVpG6ycAH43NQbmtPUxEtdmI70MMO+KgN7uH+7gPQwaCaBH8VJvgJZzXK51cuXjyMFuXQQ1kRi4WAkaJEsIgC4+C+FLoLF3RbqXBdoioFO2BcNhfNbFCRkilNd0QoZDzL9a4MQG8ziv4liZnAeE11yMnS6Ftlli2TMUfTPcsVSXNjWkJtGqzaSxHTFC0IV2sqHV9NuwZo5LugyefVMNtMcdRFbvfJLgmc5lrjoUqxPkhif6eOrdztgF+ilEUL9CCwlCCMmSLTlMamUriA8Xi8X6Zt6GOQ+8Wo63wcQLm/2sVfN+v93ddv2LGUu4Zl6l/61W/XKntJVZ7qvYZwuV/t9nBWF51zZJpB041udjEkZEUrNDHoxA2VIy9dlREs752RwDRBRhunYIeCGwFrov77PDD8mVgetdkBYsvvUdcbobhUkWSZxm+6yYTUENMZQcLgzWyKiSY4fO03gKQ6lxzeoNutAJnJviRaMz4HRN/ahLVqDK0SExZMCL3zalmmE8Jzqx/CFZzCzzBunUNrEMJgiJ3W77n1MIQnZNc1DmgBdEXlVicmIFOtl3ODxsy1ynfRY8b1xcC6GJrf+P1SASjGjrHpGWXf/rJ2nrV1jUOm3FV7fnGi98/m77t1a6ohx2LAke6UdTnWFFYkYgKqU/grz9R0lJXvnfanT42RsrPdMnJMYHwNBI7QCc/T9MRCK8GBKyoDxCaFl9cnW/6lX4SJGeDNFGk8t16xGezFfWyUzIrxz+gwRlSdnXl5Yv4zOPZxPrMXL8VaeFIGPjv7bMF5sKoqL2grLMvcPPYlxZKOa6b9yAid0zXcSEm2DlIT/UlnxtL0uCbOeV1SnYgYD7xwKhYVgzg2S2xVomYsfkCPmYw77JQbMOlGsi40Lkx1YW+XDVo8hm7LgT1SeouPGTzYjMZW3z2FQe8nSImcY6+cdie8479FXPgZPpDaa8rmiQ5hKtLYxrdSxX7gi6TfGdAlChEBOmg8Cv6PD4T1cNlxeIorrfYyI0uWbkNo3eIcZBjgjq5bVfT67nJblpgaw5ksMozRyzaVoT/mPzQ9DHwf4+7wd+4ikQoZwlxSymtXFfontqTKYIcHsSTcZNAg5NIj1b8OPC7sgYbg5KUxVoARXwfngcZ9fMbmne9KcHxr253ussJnrbzPbMVNgqpe8cGRpmL9h5VpmdNiGJinCo0WB+Tf1cbIJsGfyCjFF/UkqHTa1BXehUb95fHO3ouVEl84eYrW/6B8oEqkucHozH7NeYywPTuL9veluYhwvj+pLxtNuSqTMkDr3pwE+A+HmbAfpV7DHXYuypYK3v8G+hbaBQ==) -->

<template>
  <div class="survey-container">

    <!-- show while quiz NOT complete -->
    <div v-if="!isComplete">

      <!-- R2. -->
      <h2 class="score-header">
        Your current score: {{ score }} out of {{ questions.length }}
      </h2>

      <div class="question-block">
        <!-- R3.  -->
        <h3 class="question-header">
          {{ "Question " + questions[qno] }}
        </h3>

        <div v-for="choice in choices" :key="choice" class="choice">
          <!-- R4.  -->
          <label>
            <input
              type="radio" 
              :name="'question' + qno"
              :value="choice"
              v-model="answers[qno]"
            >
            {{ choice }}
          </label>
        </div>

        <div class="button-row">
          <!-- R1. -->
          <button @click="ok" :disabled="qno === questions.length - 1">Ok</button>
        </div>
      </div>
    </div>

    <!-- show ONLY when quiz is complete -->
    <div v-if="isComplete" class="result-block">
      <!-- R6. -->
      <p class="completion-text">
        You have completed the quiz!
        <span class="score-italic">
          Your score was {{ score }} out of {{ questions.length }}
        </span>
      </p>
    </div>

  </div>
</template>

<script>
export default {
  data() {
    return { // question formatting as 'x: question = '
      qno: 0,
      questions: ['1: 6 * 7 =', '2: 23 + 10 =', '3: The answer to everything is'], // R3. 
      qanswers: ['42', '33', '42'],
      choices: ['0', '1', '13', '33', '42'], // R4. 
      answers: []
    };
  },
  computed: {
    isComplete() {
      return this.answers.length === this.questions.length &&
             this.answers.every(a => a !== null);
    },
    score() { // R2. & R6. 
      let correct = 0;
      for (let i = 0; i < this.questions.length; i++) {
        if (this.answers[i] === this.qanswers[i]) correct++; // R6. 
      }
      return correct;
    }
  },
  created() {
    this.answers = new Array(this.questions.length).fill(null);
  },
  methods: {
    ok() { // R1. 
      if (this.qno < this.questions.length - 1) {
        this.qno++;
      }
    },
  }
};
</script>

<!-- C1. -->
<style scoped>

/* 20% larger */
.score-header {
  font-weight: bold;
  font-size: 1.2em;
}

.question-header {
  font-weight: bold;
  font-size: 1.2em;
}

/* R5. */
.choice {
  font-family: 'Courier New';
}

.button-row {
  margin-top: 10px;
}

.result-block {
  margin-top: 10px;
  font-weight: bold;
}

/* R5. */
.completion-text {
  color: green;
  font-family: 'Times New Roman';
  font-size: 1.5em;
}

.score-italic {
  font-style: italic;
}

</style>