<template>
  <div class="fixed bottom-0 left-0 right-0 bg-neutral-900 border-t border-neutral-700 p-4 z-50">
    <div class="max-w-4xl mx-auto">
      <div class="flex flex-col md:flex-row items-center gap-4">
        <!-- 현재 재생 중인 노래 정보 -->
        <div class="flex items-center gap-3 flex-1">
          <img 
            :src="currentSong.albumCoverUrl || `/placeholder.svg?height=60&width=60&text=${encodeURIComponent(currentSong.album || '')}`" 
            :alt="`${currentSong.title} 앨범 커버`"
            class="w-12 h-12 rounded-md shadow-lg"
          />
          <div>
            <h4 class="font-bold text-white">{{ currentSong.title }}</h4>
            <p class="text-sm text-neutral-400">{{ currentSong.artist }}</p>
          </div>
        </div>
        
        <!-- 재생 컨트롤 -->
        <div class="flex items-center gap-3">
          <button 
            @click="prevSong"
            class="p-2 bg-neutral-800 hover:bg-neutral-700 rounded-full transition-colors"
            :disabled="playlist.length <= 1"
            :class="{ 'opacity-50 cursor-not-allowed': playlist.length <= 1 }"
          >
            <svg class="w-5 h-5 text-white" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <polygon points="19 20 9 12 19 4 19 20"></polygon>
              <line x1="5" y1="19" x2="5" y2="5"></line>
            </svg>
          </button>
          
          <button 
            @click="togglePlay"
            class="p-3 bg-green-600 hover:bg-green-700 rounded-full transition-colors"
          >
            <svg v-if="isPlaying" class="w-6 h-6 text-white" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <rect x="6" y="4" width="4" height="16"></rect>
              <rect x="14" y="4" width="4" height="16"></rect>
            </svg>
            <svg v-else class="w-6 h-6 text-white" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <polygon points="5 3 19 12 5 21 5 3"></polygon>
            </svg>
          </button>
          
          <button 
            @click="nextSong"
            class="p-2 bg-neutral-800 hover:bg-neutral-700 rounded-full transition-colors"
            :disabled="playlist.length <= 1"
            :class="{ 'opacity-50 cursor-not-allowed': playlist.length <= 1 }"
          >
            <svg class="w-5 h-5 text-white" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <polygon points="5 4 15 12 5 20 5 4"></polygon>
              <line x1="19" y1="5" x2="19" y2="19"></line>
            </svg>
          </button>
        </div>
        
        <!-- 볼륨 및 추가 컨트롤 -->
        <div class="flex items-center gap-3">
          <div class="flex items-center gap-2">
            <svg class="w-4 h-4 text-neutral-400" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <polygon points="11 5 6 9 2 9 2 15 6 15 11 19 11 5"></polygon>
              <path d="M15.54 8.46a5 5 0 0 1 0 7.07"></path>
              <path d="M19.07 4.93a10 10 0 0 1 0 14.14"></path>
            </svg>
            <input 
              type="range" 
              min="0" 
              max="100" 
              v-model="volume" 
              class="w-20 h-2 bg-neutral-700 rounded-lg appearance-none cursor-pointer"
            >
          </div>
          
          <button 
            @click="togglePlaylistView"
            class="p-2 bg-neutral-800 hover:bg-neutral-700 rounded-full transition-colors"
          >
            <svg class="w-5 h-5 text-white" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <line x1="8" y1="6" x2="21" y2="6"></line>
              <line x1="8" y1="12" x2="21" y2="12"></line>
              <line x1="8" y1="18" x2="21" y2="18"></line>
              <line x1="3" y1="6" x2="3.01" y2="6"></line>
              <line x1="3" y1="12" x2="3.01" y2="12"></line>
              <line x1="3" y1="18" x2="3.01" y2="18"></line>
            </svg>
          </button>
        </div>
      </div>
      
      <!-- 진행 바 -->
      <div class="mt-3">
        <div class="relative h-1 bg-neutral-700 rounded-full">
          <div 
            class="absolute top-0 left-0 h-full bg-green-600 rounded-full"
            :style="{ width: `${progress}%` }"
          ></div>
        </div>
        <div class="flex justify-between text-xs text-neutral-500 mt-1">
          <span>{{ formatTime(currentTime) }}</span>
          <span>{{ formatTime(duration) }}</span>
        </div>
      </div>
      
      <!-- 플레이리스트 뷰 -->
      <div v-if="showPlaylist" class="mt-4 bg-neutral-800 border border-neutral-700 rounded-lg p-3 max-h-60 overflow-y-auto custom-scrollbar">
        <div 
          v-for="(song, index) in playlist" 
          :key="index"
          @click="$emit('change-song', index)"
          class="flex items-center gap-3 p-2 rounded-md cursor-pointer transition-colors"
          :class="{ 'bg-neutral-700': index === currentSongIndex }"
        >
          <span class="text-sm text-neutral-500">{{ index + 1 }}</span>
          <img 
            :src="song.albumCoverUrl || '/placeholder.svg?height=40&width=40'" 
            :alt="`${song.title} 앨범 커버`"
            class="w-10 h-10 rounded-md"
          />
          <div>
            <h5 class="text-sm font-medium text-white">{{ song.title }}</h5>
            <p class="text-xs text-neutral-400">{{ song.artist }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue';

// Props
const props = defineProps({
  playlist: {
    type: Array,
    required: true
  },
  currentSongIndex: {
    type: Number,
    default: 0
  }
});

// Emits
const emit = defineEmits(['change-song']);

// 상태
const isPlaying = ref(false);
const volume = ref(80);
const currentTime = ref(0);
const duration = ref(0);
const showPlaylist = ref(false);

// 현재 재생 중인 노래
const currentSong = computed(() => {
  return props.playlist[props.currentSongIndex] || {};
});

// 진행률
const progress = computed(() => {
  return duration.value ? (currentTime.value / duration.value) * 100 : 0;
});

// 재생/일시정지 토글
const togglePlay = () => {
  isPlaying.value = !isPlaying.value;
  // 여기에 실제 재생/일시정지 로직 추가 (사용자가 구현)
};

// 이전 노래
const prevSong = () => {
  if (props.playlist.length <= 1) return;
  
  const newIndex = props.currentSongIndex === 0 
    ? props.playlist.length - 1 
    : props.currentSongIndex - 1;
    
  emit('change-song', newIndex);
};

// 다음 노래
const nextSong = () => {
  if (props.playlist.length <= 1) return;
  
  const newIndex = props.currentSongIndex === props.playlist.length - 1 
    ? 0 
    : props.currentSongIndex + 1;
    
  emit('change-song', newIndex);
};

// 플레이리스트 뷰 토글
const togglePlaylistView = () => {
  showPlaylist.value = !showPlaylist.value;
};

// 시간 포맷팅 (초 -> MM:SS)
const formatTime = (seconds) => {
  if (!seconds) return '00:00';
  
  const mins = Math.floor(seconds / 60);
  const secs = Math.floor(seconds % 60);
  
  return `${mins.toString().padStart(2, '0')}:${secs.toString().padStart(2, '0')}`;
};

// 노래가 변경되면 재생 시작
watch(() => props.currentSongIndex, () => {
  // 실제 구현에서는 새 노래 로드 및 재생 로직 추가
  isPlaying.value = true;
  currentTime.value = 0;
  duration.value = 180; // 예시 값 (3분)
});

// 컴포넌트 마운트 시 초기화
// 실제 구현에서는 오디오 객체 생성 및 이벤트 리스너 설정
</script>

<style scoped>
.custom-scrollbar::-webkit-scrollbar {
  width: 6px;
}

.custom-scrollbar::-webkit-scrollbar-track {
  background: #262626;
  border-radius: 10px;
}

.custom-scrollbar::-webkit-scrollbar-thumb {
  background: #404040;
  border-radius: 10px;
}

.custom-scrollbar::-webkit-scrollbar-thumb:hover {
  background: #525252;
}

/* 볼륨 슬라이더 스타일링 */
input[type="range"]::-webkit-slider-thumb {
  -webkit-appearance: none;
  appearance: none;
  width: 12px;
  height: 12px;
  background: white;
  border-radius: 50%;
  cursor: pointer;
}

input[type="range"]::-moz-range-thumb {
  width: 12px;
  height: 12px;
  background: white;
  border-radius: 50%;
  cursor: pointer;
}
</style>