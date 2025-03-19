<template>
  <!-- Options Form -->
  <div class="mt-4">
    <div class="bg-neutral-800 border border-neutral-700 rounded-lg p-6">
      <div class="mb-6">
        <h2 class="text-xl font-bold mb-2">Tell me about your mood</h2>
        <p class="text-neutral-400">I'll help you find the perfect music for your current situation</p>
      </div>

      <div class="grid md:grid-cols-3 gap-4 mb-6">
        <!-- Activity Select -->
        <div class="space-y-2">
          <label class="text-sm flex items-center gap-2">
            <svg class="w-4 h-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none"
              stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <path d="M22 12h-4l-3 9L9 3l-3 9H2" />
            </svg>
            Current Activity
          </label>
          <select 
            v-model="activity"
            class="w-full bg-neutral-900 border border-neutral-700 rounded-md px-3 py-2 text-white"
          >
            <option value="" disabled>Select activity</option>
            <option value="studying">Studying</option>
            <option value="working">Working</option>
            <option value="exercising">Exercising</option>
            <option value="relaxing">Relaxing</option>
          </select>
        </div>

        <!-- Mood Select -->
        <div class="space-y-2">
          <label class="text-sm flex items-center gap-2">
            <svg class="w-4 h-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none"
              stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <path d="M3 18v-6a9 9 0 0 1 18 0v6" />
              <path
                d="M21 19a2 2 0 0 1-2 2h-1a2 2 0 0 1-2-2v-3a2 2 0 0 1 2-2h3zM3 19a2 2 0 0 0 2 2h1a2 2 0 0 0 2-2v-3a2 2 0 0 0-2-2H3z" />
            </svg>
            Mood
          </label>
          <select 
            v-model="mood"
            class="w-full bg-neutral-900 border border-neutral-700 rounded-md px-3 py-2 text-white"
          >
            <option value="" disabled>Select mood</option>
            <option value="happy">Happy</option>
            <option value="chill">Chill</option>
            <option value="energetic">Energetic</option>
            <option value="focused">Focused</option>
          </select>
        </div>

        <!-- Weather Select -->
        <div class="space-y-2">
          <label class="text-sm flex items-center gap-2">
            <svg class="w-4 h-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none"
              stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <circle cx="12" cy="12" r="5" />
              <line x1="12" y1="1" x2="12" y2="3" />
              <line x1="12" y1="21" x2="12" y2="23" />
              <line x1="4.22" y1="4.22" x2="5.64" y2="5.64" />
              <line x1="18.36" y1="18.36" x2="19.78" y2="19.78" />
              <line x1="1" y1="12" x2="3" y2="12" />
              <line x1="21" y1="12" x2="23" y2="12" />
              <line x1="4.22" y1="19.78" x2="5.64" y2="18.36" />
              <line x1="18.36" y1="5.64" x2="19.78" y2="4.22" />
            </svg>
            Weather
          </label>
          <select 
            v-model="weather"
            class="w-full bg-neutral-900 border border-neutral-700 rounded-md px-3 py-2 text-white"
          >
            <option value="" disabled>Select weather</option>
            <option value="sunny">Sunny</option>
            <option value="rainy">Rainy</option>
            <option value="cloudy">Cloudy</option>
            <option value="snowy">Snowy</option>
          </select>
        </div>
      </div>

      <button
        @click="submitForm"
        :disabled="!isFormValid"
        :class="{ 'opacity-50 cursor-not-allowed': !isFormValid }"
        class="w-full bg-neutral-700 hover:bg-neutral-600 text-white rounded-md px-4 py-2 flex items-center justify-center transition-colors"
      >
        <svg class="w-4 h-4 mr-2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none"
          stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
          <path d="M9 18V5l12-2v13" />
          <circle cx="6" cy="18" r="3" />
          <circle cx="18" cy="16" r="3" />
        </svg>
        Get Music Recommendations
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'

const activity = ref('')
const mood = ref('')
const weather = ref('')

// 폼 유효성 검사
const isFormValid = computed(() => {
  return activity.value && mood.value && weather.value
})

// 폼 제출
const submitForm = () => {
  if (!isFormValid.value) return
  
  // 부모 컴포넌트에 이벤트 발생
  emit('submit', {
    activity: activity.value,
    mood: mood.value,
    weather: weather.value
  })
}

const emit = defineEmits(['submit'])
</script>