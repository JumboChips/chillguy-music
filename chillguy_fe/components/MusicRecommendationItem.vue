<template>
  <div 
    class="bg-gradient-to-r from-neutral-700 to-neutral-800 border border-neutral-600 rounded-lg overflow-hidden transition-all duration-200 hover:shadow-lg hover:from-neutral-600 hover:to-neutral-700"
    :class="{ 'border-green-500 shadow-md': selected }"
  >
    <div class="flex items-center p-3">
      <!-- 선택 체크박스 -->
      <div class="flex-shrink-0 mr-3">
        <input 
          type="checkbox" 
          :checked="selected"
          @change="$emit('toggle-select')"
          class="w-4 h-4 accent-green-500 cursor-pointer"
        >
      </div>
      
      <!-- 음악 정보 -->
      <div class="flex-1 min-w-0">
        <div class="flex justify-between items-start">
          <div class="truncate">
            <h3 class="text-base font-bold text-white truncate">{{ song.title }}</h3>
            <div class="flex items-center text-sm text-neutral-300 mt-0.5">
              <span class="truncate">{{ song.artist }}</span>
              <span class="mx-1.5 text-neutral-500">•</span>
              <span class="truncate">{{ song.album }}</span>
            </div>
          </div>
          
          <!-- 재생 버튼 -->
          <button 
            @click="$emit('play')"
            class="flex-shrink-0 ml-2 p-1.5 bg-green-600 hover:bg-green-500 rounded-full transition-colors transform hover:scale-105"
          >
            <svg class="w-4 h-4 text-white" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <polygon points="5 3 19 12 5 21 5 3"></polygon>
            </svg>
          </button>
        </div>
        
        <!-- 장르 태그 -->
        <div class="mt-2 flex items-center">
          <span class="inline-flex items-center px-2 py-0.5 rounded text-xs font-medium bg-neutral-600 text-neutral-200">
            {{ song.genre }}
          </span>
        </div>
        
        <!-- 추천 이유 -->
        <p class="text-neutral-400 text-xs mt-2 line-clamp-2">{{ song.reason }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
defineProps({
  song: {
    type: Object,
    required: true
  },
  selected: {
    type: Boolean,
    default: false
  }
});

defineEmits(["play", "toggle-select"]);
</script>

<style scoped>
.line-clamp-2 {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>