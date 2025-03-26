<template>
  <footer class="bg-neutral-900 text-neutral-300 py-10 border-t border-neutral-800">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <div class="flex flex-col md:flex-row justify-between items-start space-y-6 md:space-y-0 md:space-x-8">
        <!-- Logo and description -->
        <div class="max-w-md">
          <h2 class="text-white text-2xl font-bold mb-4 flex items-center">
            <span class="mr-2">🎵</span> Chillguy Music
          </h2>
          <p class="text-neutral-400 leading-relaxed">
            An AI-powered music recommendation service that suggests the perfect music based on your activities, 
            mood, weather information, and more.
          </p>
        </div>
        
        <!-- Legal -->
        <div class="bg-neutral-800/50 p-5 rounded-lg shadow-lg">
          <h3 class="text-white text-lg font-semibold mb-4 flex items-center">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" />
            </svg>
            Legal
          </h3>
          <ul class="space-y-3">
            <li>
              <NuxtLink 
                to="/eng/privacy/" 
                class="flex items-center text-neutral-300 hover:text-white transition-colors duration-200 group"
              >
                <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4 mr-2 text-neutral-500 group-hover:text-blue-400 transition-colors duration-200" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z" />
                </svg>
                Privacy Policy
              </NuxtLink>
            </li>
            <li>
              <NuxtLink 
                to="/eng/terms/" 
                class="flex items-center text-neutral-300 hover:text-white transition-colors duration-200 group"
              >
                <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4 mr-2 text-neutral-500 group-hover:text-blue-400 transition-colors duration-200" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 20H5a2 2 0 01-2-2V6a2 2 0 012-2h10a2 2 0 012 2v1m2 13a2 2 0 01-2-2V7m2 13a2 2 0 002-2V9a2 2 0 00-2-2h-2m-4-3H9M7 16h6M7 8h6v4H7V8z" />
                </svg>
                Terms of Service
              </NuxtLink>
            </li>
          </ul>
          
          <!-- Language Switcher -->
          <div class="mt-4 pt-4 border-t border-neutral-700">
            <div class="flex items-center space-x-3">
              <a 
                href="/" 
                class="flex items-center text-sm px-2 py-1 rounded text-neutral-400 hover:text-white"
              >
                🇰🇷 한국어
              </a>
              <span 
                class="flex items-center text-sm px-2 py-1 rounded bg-blue-500 text-white"
              >
                🇺🇸 English
              </span>
            </div>
          </div>
        </div>
      </div>
      
      <div class="mt-10 pt-6 border-t border-neutral-800 text-center text-sm">
        <p class="text-neutral-500">&copy; {{ new Date().getFullYear() }} Chillguy Music. All rights reserved.</p>
      </div>
    </div>
  </footer>
</template>

<script setup>
// 현재 연도는 자동으로 계산됩니다
</script>

