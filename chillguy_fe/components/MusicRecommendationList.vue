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
            @click="showPlaylistModal = true" 
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
            :selected="isSelected(index)"
            @toggle-select="toggleSelect(index)"
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

    <!-- 플레이리스트 모달 -->
    <div v-if="showPlaylistModal" class="fixed inset-0 bg-black bg-opacity-70 flex items-center justify-center z-50 p-4">
      <div class="bg-neutral-800 rounded-lg max-w-lg w-full p-6 shadow-xl">
        <h3 class="text-xl font-bold mb-4">Create Playlist</h3>
        
        <div class="mb-4">
          <label class="block text-sm font-medium text-neutral-300 mb-1">Playlist Name</label>
          <input 
            v-model="playlistName" 
            type="text" 
            placeholder="My Awesome Playlist"
            class="w-full bg-neutral-700 border border-neutral-600 rounded-md px-3 py-2 text-white"
          />
        </div>
        
        <div class="mb-4">
          <label class="block text-sm font-medium text-neutral-300 mb-1">Description</label>
          <textarea 
            v-model="playlistDescription" 
            placeholder="A collection of songs that match my current mood"
            class="w-full bg-neutral-700 border border-neutral-600 rounded-md px-3 py-2 text-white h-20 resize-none"
          ></textarea>
        </div>
        
        <div class="mb-6">
          <h4 class="text-sm font-medium text-neutral-300 mb-2">Selected Songs ({{ selectedSongs.length }})</h4>
          <div class="max-h-48 overflow-y-auto bg-neutral-700 rounded-md p-2 custom-scrollbar">
            <div 
              v-for="(index, i) in selectedSongs" 
              :key="i"
              class="py-1 px-2 text-sm text-neutral-200 flex justify-between items-center"
            >
              <span>{{ recommendations[index].title }} - {{ recommendations[index].artist }}</span>
              <button 
                @click="removeFromSelection(i)"
                class="text-neutral-400 hover:text-red-500"
              >
                <svg class="w-4 h-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                  <line x1="18" y1="6" x2="6" y2="18"></line>
                  <line x1="6" y1="6" x2="18" y2="18"></line>
                </svg>
              </button>
            </div>
          </div>
        </div>
        
        <div class="flex justify-between">
          <button 
            @click="showPlaylistModal = false"
            class="px-4 py-2 bg-neutral-700 hover:bg-neutral-600 text-white rounded-md transition-colors"
          >
            Cancel
          </button>
          
          <div class="flex gap-2">
            <button 
              @click="addToPlaylist"
              class="px-4 py-2 bg-green-600 hover:bg-green-700 text-white rounded-md transition-colors flex items-center"
            >
              <svg class="w-4 h-4 mr-2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <path d="M9 18V5l12-2v13" />
                <circle cx="6" cy="18" r="3" />
                <circle cx="18" cy="16" r="3" />
              </svg>
              Add to Local Playlist
            </button>
            
            <button 
              @click="createYouTubePlaylist"
              class="px-4 py-2 bg-red-600 hover:bg-red-700 text-white rounded-md transition-colors flex items-center"
              :disabled="isCreatingPlaylist"
              :class="{ 'opacity-50 cursor-not-allowed': isCreatingPlaylist }"
            >
              <svg v-if="isCreatingPlaylist" class="animate-spin -ml-1 mr-2 h-4 w-4 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
              </svg>
              <svg v-else class="w-4 h-4 mr-2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <path d="M22.54 6.42a2.78 2.78 0 0 0-1.94-2C18.88 4 12 4 12 4s-6.88 0-8.6.46a2.78 2.78 0 0 0-1.94 2A29 29 0 0 0 1 11.75a29 29 0 0 0 .46 5.33A2.78 2.78 0 0 0 3.4 19c1.72.46 8.6.46 8.6.46s6.88 0 8.6-.46a2.78 2.78 0 0 0 1.94-2 29 29 0 0 0 .46-5.25 29 29 0 0 0-.46-5.33z"></path>
                <polygon points="9.75 15.02 15.5 11.75 9.75 8.48 9.75 15.02"></polygon>
              </svg>
              Create YouTube Playlist
            </button>
          </div>
        </div>
      </div>
    </div>
    
    <!-- 성공 알림 모달 -->
    <div v-if="showSuccessModal" class="fixed inset-0 bg-black bg-opacity-70 flex items-center justify-center z-50 p-4">
      <div class="bg-neutral-800 rounded-lg max-w-md w-full p-6 shadow-xl">
        <div class="flex items-center justify-center mb-4">
          <svg class="w-12 h-12 text-green-500" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"></path>
            <polyline points="22 4 12 14.01 9 11.01"></polyline>
          </svg>
        </div>
        <h3 class="text-xl font-bold mb-2 text-center">Success!</h3>
        <p class="text-neutral-300 text-center mb-4">{{ successMessage }}</p>
        <div class="flex justify-center">
          <button 
            @click="showSuccessModal = false"
            class="px-4 py-2 bg-green-600 hover:bg-green-700 text-white rounded-md transition-colors"
          >
            Close
          </button>
        </div>
      </div>
    </div>
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

// 선택된 노래 인덱스 목록 (ID 대신 인덱스 사용)
const selectedSongs = ref([]);

// 현재 재생 중인 플레이리스트
const currentPlaylist = ref([]);

// 현재 재생 중인 노래 인덱스
const currentSongIndex = ref(0);

// 플레이리스트 모달 상태
const showPlaylistModal = ref(false);

// 플레이리스트 이름
const playlistName = ref('My Chill Playlist');

// 플레이리스트 설명
const playlistDescription = ref('A collection of songs recommended by Chill Guy');

// 플레이리스트 생성 중 상태
const isCreatingPlaylist = ref(false);

// 성공 모달 상태
const showSuccessModal = ref(false);
const successMessage = ref('');

// 모든 노래가 선택되었는지 확인
const allSelected = computed(() => {
  return props.recommendations.length > 0 && selectedSongs.value.length === props.recommendations.length;
});

// 노래가 선택되었는지 확인하는 함수 (인덱스 기반)
const isSelected = (index) => {
  return selectedSongs.value.includes(index);
};

// 개별 노래 선택/해제 토글 (인덱스 기반)
const toggleSelect = (index) => {
  const selectedIndex = selectedSongs.value.indexOf(index);
  if (selectedIndex === -1) {
    selectedSongs.value.push(index);
  } else {
    selectedSongs.value.splice(selectedIndex, 1);
  }
};

// 선택 목록에서 제거
const removeFromSelection = (selectionIndex) => {
  selectedSongs.value.splice(selectionIndex, 1);
};

// 전체 선택/해제 토글
const toggleSelectAll = () => {
  if (allSelected.value) {
    selectedSongs.value = [];
  } else {
    selectedSongs.value = props.recommendations.map((_, index) => index);
  }
};

// 플레이리스트에 추가
const addToPlaylist = () => {
  // 선택된 노래만 필터링
  const songsToAdd = selectedSongs.value.map(index => props.recommendations[index]);
  
  // 현재 플레이리스트에 추가
  currentPlaylist.value = [...songsToAdd];
  
  // 첫 번째 노래부터 재생
  currentSongIndex.value = 0;
  
  // 모달 닫기
  showPlaylistModal.value = false;
  
  // 성공 메시지 표시
  successMessage.value = `${songsToAdd.length} songs have been added to your local playlist.`;
  showSuccessModal.value = true;
  
  console.log('Adding to playlist:', songsToAdd);
  console.log('Playlist name:', playlistName.value);
};

// YouTube 플레이리스트 생성
const createYouTubePlaylist = async () => {
  try {
    isCreatingPlaylist.value = true;

    const songsToAdd = selectedSongs.value.map(index => props.recommendations[index]);

    // api 호출
    const createResponse = await useNuxtApp().$apiFetch('/playlist', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('accessToken') || ''}`
      },
      body: JSON.stringify({
        title: playlistName.value,
        description: playlistDescription.value,
        songs: songsToAdd
      })
    });

    successMessage.value = `YouTube playlist "${playlistName.value}" created successfully with ${songsToAdd.length} songs!`;
    showSuccessModal.value = true;
    showPlaylistModal.value = false;
    
  } catch (error) {
    console.error('Error creating YouTube playlist:', error);
    alert('An error occurred while creating the YouTube playlist. Please try again.');
  } finally {
    isCreatingPlaylist.value = false;
  }
};

// 노래 재생
const playSong = (song) => {
  // 현재 플레이리스트에 노래가 있는지 확인
  const songIndex = currentPlaylist.value.findIndex(s => s.title === song.title && s.artist === song.artist);
  
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