<template>
  <div class="flex items-center justify-between p-4 rounded-lg bg-neutral-800 text-white shadow-lg">
    <!-- 🎵 앨범 이미지 -->
    <div class="flex items-center gap-4">
      <img :src="currentThumbnail" alt="Album cover" class="w-16 h-16 rounded-lg shadow-md" />
      <div>
        <h3 class="font-semibold">{{ currentTitle }}</h3>
        <p class="text-sm text-neutral-400">{{ currentArtist }}</p>
      </div>
    </div>

    <!-- 🎵 컨트롤 버튼 -->
    <div class="flex items-center gap-4">
      <button @click="prevVideo" class="p-2 hover:bg-neutral-600 rounded-full transition-colors">
        ⏮️
      </button>
      <button @click="togglePlayPause" class="p-4 bg-white text-black rounded-full shadow-md">
        {{ isPlaying ? "⏸️" : "▶️" }}
      </button>
      <button @click="nextVideo" class="p-2 hover:bg-neutral-600 rounded-full transition-colors">
        ⏭️
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from "vue";

const props = defineProps({
  playlistId: String, // 🎵 YouTube Playlist ID
});

const player = ref(null);
const isPlaying = ref(false);
const currentTitle = ref("재생 준비 중...");
const currentArtist = ref("AI 추천 음악");
const currentThumbnail = ref("https://via.placeholder.com/150");

// 🎵 YouTube IFrame API 로드
onMounted(() => {
  if (window.YT) {
    initializePlayer();
  } else {
    loadYouTubeAPI();
  }
});

watch(() => props.playlistId, (newVal) => {
  if (newVal && player.value) {
    player.value.loadPlaylist({ list: newVal });
  }
});

function loadYouTubeAPI() {
  const tag = document.createElement("script");
  tag.src = "https://www.youtube.com/iframe_api";
  document.body.appendChild(tag);

  window.onYouTubeIframeAPIReady = initializePlayer;
}

function initializePlayer() {
  player.value = new window.YT.Player("hiddenPlayer", {
    height: "0",
    width: "0",
    playerVars: {
      listType: "playlist",
      list: props.playlistId,
      autoplay: 1,
      controls: 0,
    },
    events: {
      onReady: () => (isPlaying.value = true),
      onStateChange: handlePlayerStateChange,
    },
  });
}

function handlePlayerStateChange(event) {
  if (event.data === window.YT.PlayerState.PLAYING) {
    isPlaying.value = true;
    fetchCurrentSongInfo();
  } else if (event.data === window.YT.PlayerState.PAUSED) {
    isPlaying.value = false;
  }
}

// 🎵 현재 곡 정보 가져오기
function fetchCurrentSongInfo() {
  try {
    currentTitle.value = player.value.getVideoData().title;
    currentThumbnail.value = `https://img.youtube.com/vi/${player.value.getVideoData().video_id}/hqdefault.jpg`;
  } catch (error) {
    currentTitle.value = "재생 중...";
  }
}

// 🎵 컨트롤 기능
function togglePlayPause() {
  if (isPlaying.value) {
    player.value.pauseVideo();
  } else {
    player.value.playVideo();
  }
}

function nextVideo() {
  player.value.nextVideo();
}

function prevVideo() {
  player.value.previousVideo();
}
</script>

<style scoped>
button {
  font-size: 16px;
  cursor: pointer;
}
</style>
