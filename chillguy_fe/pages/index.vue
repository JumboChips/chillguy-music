<script setup lang="ts">
const { user, fetchUser, logout } = useAuth();

onMounted(() => {
  fetchUser();
})

watchEffect(() => {
  if (!user.value) {
    fetchUser();
  }
});

</script>

<template>
  <div class="min-h-screen bg-gradient-to-b from-neutral-900 to-neutral-800 text-white p-4">
      <div>
  <nav class="flex justify-between items-center p-4 bg-neutral-900 shadow-sm">
    <NuxtLink to="/" class="text-lg font-semibold text-white">홈</NuxtLink>

    <div v-if="user" class="flex items-center gap-4">
      <img :src="user.profileImage" alt="프로필 이미지" class="w-10 h-10 rounded-full border border-gray-300" />
      <p class="text-white font-medium">안녕하세요, {{ user.name }}님!</p>
      <button @click="logout" class="px-4 py-2 text-sm text-white bg-red-500 rounded hover:bg-red-600 transition">
        로그아웃
      </button>
    </div>

    <NuxtLink v-else to="/login" class="text-lg font-semibold text-white">로그인</NuxtLink>
  </nav>
    <slot />
  </div>
    <main class="max-w-4xl mx-auto py-8">
      <!-- Hero Section -->
      <div class="flex flex-col items-center mb-12 text-center">
        <div class="relative w-48 h-48 mb-6">
          <img
            src="https://hebbkx1anhila5yf.public.blob.vercel-storage.com/Chill%20Guy%20Png%20Photo%20Free%20Download-c9hCBybDGOmTb8hDp5mBFRaoWqzPO5.png"
            alt="Chill Guy Character" class="w-full h-full object-contain" />
        </div>
        <h1 class="text-4xl font-bold mb-4">Chill Music Recommendations</h1>
        <p class="text-xl text-neutral-400">Let the Chill Guy find your perfect vibe</p>
      </div>

      <!-- Tabs -->
      <div class="mb-8">
        <div class="grid grid-cols-2 w-full max-w-md mx-auto bg-neutral-800 rounded-lg p-1">
          <button class="flex items-center justify-center px-4 py-2 rounded-md bg-neutral-700 text-white">
            <svg class="w-4 h-4 mr-2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none"
              stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <path d="M22 12h-4l-3 9L9 3l-3 9H2" />
            </svg>
            Quick Options
          </button>
          <button class="flex items-center justify-center px-4 py-2 rounded-md text-neutral-400 hover:text-white">
            <svg class="w-4 h-4 mr-2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none"
              stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <path d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z" />
            </svg>
            Tell Your Story
          </button>
        </div>

        <!-- Options Form -->
        <div class="mt-4">
          <div class="bg-neutral-800 border border-neutral-700 rounded-lg p-6">
            <div class="mb-6">
              <h2 class="text-xl font-bold mb-2">Tell me about your mood</h2>
              <p class="text-neutral-400">I'll help you find the perfect music for your current situation</p>
            </div>

            <div class="grid md:grid-cols-3 gap-4 mb-6">
              <!-- Activity Select -->
              <div class="space-y-2">
                <label class="text-sm flex items-center gap-2">
                  <svg class="w-4 h-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none"
                    stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <path d="M22 12h-4l-3 9L9 3l-3 9H2" />
                  </svg>
                  Current Activity
                </label>
                <select class="w-full bg-neutral-900 border border-neutral-700 rounded-md px-3 py-2 text-white">
                  <option value="" disabled selected>Select activity</option>
                  <option value="studying">Studying</option>
                  <option value="working">Working</option>
                  <option value="exercising">Exercising</option>
                  <option value="relaxing">Relaxing</option>
                </select>
              </div>

              <!-- Mood Select -->
              <div class="space-y-2">
                <label class="text-sm flex items-center gap-2">
                  <svg class="w-4 h-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none"
                    stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <path d="M3 18v-6a9 9 0 0 1 18 0v6" />
                    <path
                      d="M21 19a2 2 0 0 1-2 2h-1a2 2 0 0 1-2-2v-3a2 2 0 0 1 2-2h3zM3 19a2 2 0 0 0 2 2h1a2 2 0 0 0 2-2v-3a2 2 0 0 0-2-2H3z" />
                  </svg>
                  Mood
                </label>
                <select class="w-full bg-neutral-900 border border-neutral-700 rounded-md px-3 py-2 text-white">
                  <option value="" disabled selected>Select mood</option>
                  <option value="happy">Happy</option>
                  <option value="chill">Chill</option>
                  <option value="energetic">Energetic</option>
                  <option value="focused">Focused</option>
                </select>
              </div>

              <!-- Weather Select -->
              <div class="space-y-2">
                <label class="text-sm flex items-center gap-2">
                  <svg class="w-4 h-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none"
                    stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <circle cx="12" cy="12" r="5" />
                    <line x1="12" y1="1" x2="12" y2="3" />
                    <line x1="12" y1="21" x2="12" y2="23" />
                    <line x1="4.22" y1="4.22" x2="5.64" y2="5.64" />
                    <line x1="18.36" y1="18.36" x2="19.78" y2="19.78" />
                    <line x1="1" y1="12" x2="3" y2="12" />
                    <line x1="21" y1="12" x2="23" y2="12" />
                    <line x1="4.22" y1="19.78" x2="5.64" y2="18.36" />
                    <line x1="18.36" y1="5.64" x2="19.78" y2="4.22" />
                  </svg>
                  Weather
                </label>
                <select class="w-full bg-neutral-900 border border-neutral-700 rounded-md px-3 py-2 text-white">
                  <option value="" disabled selected>Select weather</option>
                  <option value="sunny">Sunny</option>
                  <option value="rainy">Rainy</option>
                  <option value="cloudy">Cloudy</option>
                  <option value="snowy">Snowy</option>
                </select>
              </div>
            </div>

            <button
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

        <!-- Text Input Form (Hidden by default) -->
        <div class="mt-4 hidden">
          <div class="bg-neutral-800 border border-neutral-700 rounded-lg p-6">
            <div class="mb-6">
              <h2 class="text-xl font-bold mb-2">Share Your Moment</h2>
              <p class="text-neutral-400">
                Tell me about your current situation, feelings, or what made your day special
              </p>
            </div>

            <div class="space-y-4">
              <textarea placeholder="예: 오늘은 가족들과 함께 파스타를 만들어 먹었어요. 다들 맛있게 먹어줘서 정말 기뻐요! 이 기분 좋은 순간에 어울리는 음악을 추천해주세요."
                class="w-full min-h-[120px] bg-neutral-900 border border-neutral-600 rounded-md px-3 py-2 text-white resize-none"></textarea>

              <button
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
      </div>

      <!-- Recommendations Section -->
      <div class="mt-8 space-y-6">
        <div class="bg-neutral-800 border border-neutral-700 rounded-lg overflow-hidden">
          <div class="p-6">
            <h2 class="text-xl font-bold mb-2">Your Personalized Playlist</h2>
            <p class="text-neutral-400">Based on your current vibe</p>
          </div>

          <div class="p-6 space-y-4">
            <!-- Example recommendation -->
            <div class="flex items-center justify-between p-4 rounded-lg bg-neutral-700">
              <div class="flex items-center gap-4">
                <img src="" alt="Album cover" class="w-16 h-16 rounded" />
                <div>
                  <h3 class="font-semibold">Chill Beats</h3>
                  <p class="text-sm text-neutral-400">Lo-fi Artist</p>
                </div>
              </div>
              <div class="flex items-center gap-2">
                <button class="p-2 hover:bg-neutral-600 rounded-full transition-colors">
                  <svg class="w-4 h-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none"
                    stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <polygon points="5 3 19 12 5 21 5 3" />
                  </svg>
                </button>
                <button class="p-2 hover:bg-neutral-600 rounded-full transition-colors">
                  <svg class="w-4 h-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none"
                    stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <path
                      d="M14 9V5a3 3 0 0 0-3-3l-4 9v11h11.28a2 2 0 0 0 2-1.7l1.38-9a2 2 0 0 0-2-2.3zM7 22H4a2 2 0 0 1-2-2v-7a2 2 0 0 1 2-2h3" />
                  </svg>
                </button>
                <button class="p-2 hover:bg-neutral-600 rounded-full transition-colors">
                  <svg class="w-4 h-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none"
                    stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <circle cx="18" cy="5" r="3" />
                    <circle cx="6" cy="12" r="3" />
                    <circle cx="18" cy="19" r="3" />
                    <line x1="8.59" y1="13.51" x2="15.42" y2="17.49" />
                    <line x1="15.41" y1="6.51" x2="8.59" y2="10.49" />
                  </svg>
                </button>
              </div>
            </div>

            <!-- AI Explanation -->
            <div class="bg-neutral-700 border border-neutral-600 rounded-lg p-6">
              <p class="text-sm text-neutral-300">
                Based on your chill mood and relaxing activity, I think you'll enjoy this lo-fi track.
                The gentle beats and smooth melodies create the perfect atmosphere for unwinding.
              </p>
              <div class="mt-4">
                <h4 class="text-sm font-semibold mb-2">You might also like:</h4>
                <ul class="text-sm text-neutral-400">
                  <li>• "Midnight Dreams" by Ambient Waves</li>
                  <li>• "Study Session" by Beat Maker</li>
                  <li>• "Rainy Day" by Chill Vibes</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>