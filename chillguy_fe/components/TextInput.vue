<template>
  <!-- Text Input Form -->
  <div class="mt-4">
    <div class="bg-neutral-800 border border-neutral-700 rounded-lg p-6">
      <div class="mb-6">
        <h2 class="text-xl font-bold mb-2">Share Your Moment</h2>
        <p class="text-neutral-400">
          Tell me about your current situation, feelings, or what made your day special
        </p>
      </div>

      <div class="space-y-4">
        <textarea 
          v-model="storyText"
          placeholder="예: 오늘은 가족들과 함께 파스타를 만들어 먹었어요. 다들 맛있게 먹어줘서 정말 기뻐요! 이 기분 좋은 순간에 어울리는 음악을 추천해주세요."
          class="w-full min-h-[120px] bg-neutral-900 border border-neutral-600 rounded-md px-3 py-2 text-white resize-none">
        </textarea>

        <div class="flex justify-between items-center text-xs text-neutral-400">
          <span>{{ storyText.length }} / 500 자</span>
          <span v-if="storyText.length < 20 && storyText.length > 0" class="text-yellow-500">
            더 자세히 설명해주시면 더 좋은 추천을 받을 수 있어요.
          </span>
        </div>

        <button
          @click="getRecommendations"
          :disabled="!isValid"
          :class="{ 'opacity-50 cursor-not-allowed': !isValid }"
          class="w-full bg-neutral-700 hover:bg-neutral-600 text-white rounded-md px-4 py-2 flex items-center justify-center transition-colors">
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
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'

const storyText = ref('')

// 유효성 검사
const isValid = computed(() => {
  return storyText.value.length >= 20 && storyText.value.length <= 500
})

// 추천 요청
const getRecommendations = () => {
  if (!isValid.value) return
  
  // 부모 컴포넌트에 이벤트 발생
  emit('submit', storyText.value)
}

const emit = defineEmits(['submit'])
</script>