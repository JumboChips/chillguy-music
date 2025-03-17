<template>
  <div class="mt-8 space-y-6">
    <div class="bg-neutral-800 border border-neutral-700 rounded-lg overflow-hidden">
      <div class="p-6 flex justify-between items-center">
        <div>
          <h2 class="text-xl font-bold mb-2">Your Personalized Playlist</h2>
          <p class="text-neutral-400">Based on your current vibe</p>
        </div>
        
        <!-- 플레이리스트 컨트롤 -->
        <div class="flex items-center gap-3">
          <div class="flex items-center">
            <input 
              type="checkbox" 
              id="select-all" 
              class="w-4 h-4 bg-neutral-700 border-neutral-600 rounded"
              :checked="allSelected"
              @change="toggleSelectAll"
            >
            <label for="select-all" class="ml-2 text-sm text-neutral-300">Select All</label>
          </div>
          
          <button 
            @click="addToPlaylist" 
            class="px-4 py-2 bg-green-600 hover:bg-green-700 text-white rounded-md flex items-center transition-colors"
            :disabled="selectedSongs.length === 0"
            :class="{ 'opacity-50 cursor-not-allowed': selectedSongs.length === 0 }"
          >
            <svg class="w-4 h-4 mr-2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <line x1="12" y1="5" x2="12" y2="19"></line>
              <line x1="5" y1="12" x2="19" y2="12"></line>
            </svg>
            Add to Playlist
          </button>
        </div>
      </div>

      <div class="p-6">
        <!-- 로딩 상태 -->
        <div v-if="loading" class="flex justify-center py-8">
          <div class="animate-spin rounded-full h-12 w-12 border-t-2 border-b-2 border-white"></div>
        </div>
        
        <!-- 추천 결과가 없을 때 -->
        <div v-else-if="!recommendations || recommendations.length === 0" class="text-center py-8 text-neutral-400">
          <svg class="w-16 h-16 mx-auto mb-4 text-neutral-600" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <circle cx="12" cy="12" r="10"></circle>
            <line x1="12" y1="8" x2="12" y2="12"></line>
            <line x1="12" y1="16" x2="12.01" y2="16"></line>
          </svg>
          <p>No recommendations yet. Try adjusting your preferences or input.</p>
        </div>
        
        <!-- 추천 음악 목록 -->
        <div v-else class="space-y-4 max-h-[600px] overflow-y-auto pr-2 custom-scrollbar">
          <MusicRecommendationItem 
            v-for="(item, index) in recommendations" 
            :key="index"
            :song="item"
            :selected="isSelected(item.id)"
            @toggle-select="toggleSelect(item.id)"
            @play="playSong(item)"
          />
        </div>
        
        <!-- AI 설명 -->
        <div v-if="aiExplanation" class="mt-6 bg-neutral-700 border border-neutral-600 rounded-lg p-6">
          <p class="text-sm text-neutral-300">{{ aiExplanation }}</p>
        </div>
      </div>
    </div>
    
    <!-- 플레이어 -->
    <MusicPlayer 
      v-if="currentPlaylist.length > 0" 
      :playlist="currentPlaylist"
      :currentSongIndex="currentSongIndex"
      @change-song="changeSong"
    />
  </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue';
import MusicRecommendationItem from './MusicRecommendationItem.vue';
import MusicPlayer from './MusicPlayer.vue';

// Props
const props = defineProps({
  recommendations: {
    type: Array,
    default: () => []
  },
  aiExplanation: {
    type: String,
    default: ''
  },
  loading: {
    type: Boolean,
    default: false
  }
});

// 선택된 노래 ID 목록
const selectedSongs = ref([]);

// 현재 재생 중인 플레이리스트
const currentPlaylist = ref([]);

// 현재 재생 중인 노래 인덱스
const currentSongIndex = ref(0);

// 모든 노래가 선택되었는지 확인
const allSelected = computed(() => {
  return props.recommendations.length > 0 && selectedSongs.value.length === props.recommendations.length;
});

// 노래가 선택되었는지 확인하는 함수
const isSelected = (songId) => {
  return selectedSongs.value.includes(songId);
};

// 개별 노래 선택/해제 토글
const toggleSelect = (songId) => {
  const index = selectedSongs.value.indexOf(songId);
  if (index === -1) {
    selectedSongs.value.push(songId);
  } else {
    selectedSongs.value.splice(index, 1);
  }
};

// 전체 선택/해제 토글
const toggleSelectAll = () => {
  if (allSelected.value) {
    selectedSongs.value = [];
  } else {
    selectedSongs.value = props.recommendations.map(song => song.id);
  }
};

// 플레이리스트에 추가
const addToPlaylist = () => {
  // 선택된 노래만 필터링
  const songsToAdd = props.recommendations.filter(song => selectedSongs.value.includes(song.title));
  
  // 현재 플레이리스트에 추가
  currentPlaylist.value = [...songsToAdd];
  
  // 첫 번째 노래부터 재생
  currentSongIndex.value = 0;
  
  // 여기에 API 호출 로직 추가 (사용자가 구현)
  console.log('Adding to playlist:', songsToAdd);
};

// 노래 재생
const playSong = (song) => {
  // 현재 플레이리스트에 노래가 있는지 확인
  const songIndex = currentPlaylist.value.findIndex(s => s.id === song.id);
  
  if (songIndex !== -1) {
    // 이미 플레이리스트에 있으면 해당 노래로 이동
    currentSongIndex.value = songIndex;
  } else {
    // 플레이리스트에 없으면 추가하고 재생
    currentPlaylist.value = [song];
    currentSongIndex.value = 0;
  }
};

// 노래 변경
const changeSong = (index) => {
  currentSongIndex.value = index;
};

// 추천 목록이 변경되면 선택 상태 초기화
watch(() => props.recommendations, () => {
  selectedSongs.value = [];
}, { deep: true });
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
</style>