// src\utils\parseAnswer.js

// helper function than handles answer parsing
// strip leading/trailing whitespace
// extract core answer [(Who/What/Where) is {answer}]
// return cleaned answer string to compare against 'correct'

export function parseAnswer(raw) {
  const lower = raw.trim().toLowerCase()

  // match patterns
  const match = lower.match(/^(who|what|where) is\s+(.+)\?*$/i)
  if (match && match[2]) { return match[2].trim() }

  return lower.replace(/\?$/, '').trim()
}
