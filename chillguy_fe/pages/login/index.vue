<script setup lang="ts">
import { ref } from 'vue';

definePageMeta({
  layout: false,
})

const showServiceMessage = ref(false);
const config = useRuntimeConfig();

const handleSpotifyLogin = () => {
  showServiceMessage.value = true;
};

const closeMessage = () => {
  showServiceMessage.value = false;
};

const loginWithGoogle = () => {
  window.location.href = `${config.public.apiBaseUrl}/api/oauth2/authorization/google`;
};
</script>

<template>
  <div class="min-h-screen bg-gradient-to-b from-neutral-900 to-neutral-800 text-white flex items-center justify-center p-4">
    <div class="w-full max-w-md">
      <!-- Logo and Title -->
      <div class="text-center mb-10">
        <h1 class="text-4xl font-bold mb-2">로그인</h1>
        <p class="text-neutral-400">계정에 로그인하여 맞춤형 음악 추천을 받아보세요</p>
      </div>
      
      <!-- Login Buttons -->
      <div class="space-y-4">
        <!-- Google Login Button -->
        <button 
          class="w-full flex items-center justify-center bg-white text-gray-800 py-3 px-4 rounded-lg hover:bg-gray-100 transition-colors"
          @click="loginWithGoogle"
        >
          <svg class="w-5 h-5 mr-3" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
            <path d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z" fill="#4285F4"/>
            <path d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z" fill="#34A853"/>
            <path d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z" fill="#FBBC05"/>
            <path d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z" fill="#EA4335"/>
          </svg>
          Google로 로그인
        </button>
        
        <!-- Spotify Login Button -->
        <button 
          class="w-full flex items-center justify-center bg-[#1DB954] text-white py-3 px-4 rounded-lg hover:bg-[#1ed760] transition-colors"
          @click="handleSpotifyLogin"
        >
          <svg class="w-5 h-5 mr-3" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
            <path d="M12 2C6.47715 2 2 6.47715 2 12C2 17.5228 6.47715 22 12 22C17.5228 22 22 17.5228 22 12C22 6.47715 17.5228 2 12 2ZM16.5917 16.5917C16.2931 16.8903 15.8162 16.8903 15.5176 16.5917C14.4889 15.563 13.0432 15 11.5 15C9.95685 15 8.51115 15.563 7.48239 16.5917C7.18379 16.8903 6.70685 16.8903 6.40825 16.5917C6.10964 16.2931 6.10964 15.8162 6.40825 15.5176C7.72429 14.2015 9.53903 13.5 11.5 13.5C13.461 13.5 15.2757 14.2015 16.5917 15.5176C16.8903 15.8162 16.8903 16.2931 16.5917 16.5917ZM18.364 13.636C18.0654 13.9346 17.5885 13.9346 17.2899 13.636C15.7327 12.0788 13.6911 11.1818 11.5 11.1818C9.30895 11.1818 7.26734 12.0788 5.71015 13.636C5.41155 13.9346 4.93461 13.9346 4.636 13.636C4.3374 13.3374 4.3374 12.8605 4.636 12.5619C6.47881 10.7191 8.89085 9.68182 11.5 9.68182C14.1092 9.68182 16.5212 10.7191 18.364 12.5619C18.6626 12.8605 18.6626 13.3374 18.364 13.636ZM20.1362 10.8638C19.8376 11.1624 19.3607 11.1624 19.0621 10.8638C16.9763 8.77795 14.3273 7.63636 11.5 7.63636C8.67271 7.63636 6.02369 8.77795 3.93789 10.8638C3.63929 11.1624 3.16235 11.1624 2.86375 10.8638C2.56514 10.5652 2.56514 10.0883 2.86375 9.78967C5.23054 7.42288 8.25439 6.13636 11.5 6.13636C14.7456 6.13636 17.7695 7.42288 20.1362 9.78967C20.4348 10.0883 20.4348 10.5652 20.1362 10.8638Z" fill="white"/>
          </svg>
          Spotify로 로그인
        </button>
      </div>
      
      <!-- Service Message Modal -->
      <div v-if="showServiceMessage" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 p-4">
        <div class="bg-neutral-800 rounded-lg p-6 max-w-md w-full border border-neutral-700">
          <h3 class="text-xl font-bold mb-4">서비스 준비 중</h3>
          <p class="mb-6">Spotify 로그인 서비스는 현재 준비 중입니다. Google 로그인을 이용해 주세요.</p>
          <button 
            @click="closeMessage"
            class="w-full bg-[#1DB954] text-white py-2 px-4 rounded-lg hover:bg-[#1ed760] transition-colors"
          >
            확인
          </button>
        </div>
      </div>
      
      <!-- Footer -->
      <div class="mt-10 text-center text-neutral-500 text-sm">
        <p>로그인하면 서비스 이용약관 및 개인정보 처리방침에 동의하게 됩니다.</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* Additional styles if needed */
</style>