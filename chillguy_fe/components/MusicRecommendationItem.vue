<template>
  <div 
    class="bg-neutral-700 border border-neutral-600 rounded-lg overflow-hidden transition-all duration-200"
    :class="{ 'bg-neutral-600': selected }"
  >
    <div class="flex flex-col sm:flex-row">
      <!-- 선택 체크박스 -->
      <div class="sm:w-[50px] flex items-center justify-center p-3">
        <input 
          type="checkbox" 
          :checked="selected"
          @change="$emit('toggle-select')"
          class="w-5 h-5 bg-neutral-700 border-neutral-600 rounded"
        >
      </div>
      
      <!-- 음악 정보 -->
      <div class="flex-1 p-4">
        <h3 class="text-lg font-bold text-white">{{ song.title }}</h3>
        <p class="text-neutral-300">{{ song.artist }}</p>
        <p class="text-neutral-300">{{ song.album }}</p>
        <p class="text-neutral-300">{{ song.genre }}</p>

        <!-- 추천 이유 -->
        <p class="text-neutral-400 text-sm mt-2">{{ song.reason }}</p>

        <!-- 플레이 버튼 -->
        <button 
          @click="$emit('play')"
          class="mt-3 p-2 bg-neutral-600 hover:bg-neutral-500 rounded-full transition-colors"
        >
          ▶ Play
        </button>
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