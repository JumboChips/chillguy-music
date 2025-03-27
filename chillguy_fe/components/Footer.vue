<template>
  <footer class="bg-neutral-900 text-neutral-300 py-6 border-t border-neutral-800">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <!-- Top section with logo and description -->
      <div class="flex flex-wrap items-center justify-between mb-4">
        <!-- Logo and description -->
        <div class="flex items-center space-x-2 mb-3 sm:mb-0">
          <span class="text-white text-2xl font-bold">🎵</span>
          <h2 class="text-white text-xl font-bold">Chillguy Music</h2>
        </div>
        
        <!-- Language Switcher -->
        <div class="flex items-center space-x-2 bg-neutral-800/50 rounded-full px-2 py-1">
          <button 
            @click="locale = 'ko'; window.location.href = '/'"
            class="flex items-center text-sm px-2 py-1 rounded-full transition-colors"
            :class="locale === 'ko' ? 'bg-blue-500 text-white' : 'text-neutral-400 hover:text-white'"
          >
            🇰🇷 한국어
          </button>
          <button 
            @click="locale = 'en'; window.location.href = '/eng/privacy/'"
            class="flex items-center text-sm px-2 py-1 rounded-full transition-colors"
            :class="locale === 'en' ? 'bg-blue-500 text-white' : 'text-neutral-400 hover:text-white'"
          >
            🇺🇸 English
          </button>
        </div>
      </div>
      
      <!-- Middle section with description and legal links -->
      <div class="flex flex-wrap items-start justify-between py-3 border-t border-b border-neutral-800">
        <p class="text-neutral-400 text-sm max-w-xl mb-4 md:mb-0">
          {{ locale === 'ko' 
            ? '인공지능 기반의 음악 추천 서비스로, 당신의 활동, 기분, 날씨 정보 등을 바탕으로 최적의 음악을 추천해 드립니다.' 
            : 'An AI-powered music recommendation service that suggests the perfect music based on your activities, mood, weather information, and more.' 
          }}
        </p>
        
        <!-- Legal Links -->
        <div class="flex flex-wrap items-center space-x-6">
          <NuxtLink 
            :to="locale === 'ko' ? '/privacy/' : '/eng/privacy/'" 
            class="flex items-center text-sm text-neutral-300 hover:text-white transition-colors duration-200 group"
          >
            <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4 mr-1 text-neutral-500 group-hover:text-blue-400 transition-colors duration-200" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z" />
            </svg>
            {{ locale === 'ko' ? '개인정보처리방침' : 'Privacy Policy' }}
          </NuxtLink>
          <NuxtLink 
            :to="locale === 'ko' ? '/terms/' : '/eng/terms/'" 
            class="flex items-center text-sm text-neutral-300 hover:text-white transition-colors duration-200 group"
          >
            <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4 mr-1 text-neutral-500 group-hover:text-blue-400 transition-colors duration-200" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 20H5a2 2 0 01-2-2V6a2 2 0 012-2h10a2 2 0 012 2v1m2 13a2 2 0 01-2-2V7m2 13a2 2 0 002-2V9a2 2 0 00-2-2h-2m-4-3H9M7 16h6M7 8h6v4H7V8z" />
            </svg>
            {{ locale === 'ko' ? '서비스 이용약관' : 'Terms of Service' }}
          </NuxtLink>
        </div>
      </div>
      
      <!-- Bottom section with copyright -->
      <div class="pt-4 text-center text-xs text-neutral-500">
        <p>&copy; {{ new Date().getFullYear() }} Chillguy Music. All rights reserved.</p>
      </div>
    </div>

    <div class="pt-2 text-center text-xs text-neutral-500">
  <a href="/privacy" target="_blank" rel="noopener noreferrer" class="underline hover:text-white">
    개인정보처리방침
  </a>
  |
  <a href="/terms" target="_blank" rel="noopener noreferrer" class="underline hover:text-white">
    서비스 이용약관
  </a>
</div>
  </footer>
</template>

<script setup>
import { ref } from 'vue';

// 현재 로케일 상태
const locale = ref('ko');
</script>

