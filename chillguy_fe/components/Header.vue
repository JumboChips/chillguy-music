<script setup lang="ts">
import { useAuthStore } from '~/stores/auth'

const authStore = useAuthStore();
const { user } = storeToRefs(authStore);
</script>

<template>
        <div>
  <nav class="flex justify-between items-center p-4 bg-neutral-900 shadow-sm">
    <NuxtLink to="/" class="text-lg font-semibold text-white">홈</NuxtLink>

    <div v-if="user" class="flex items-center gap-4">
      <img :src="user.profileImage" alt="프로필 이미지" class="w-10 h-10 rounded-full border border-gray-300" />
      <p class="text-white font-medium">안녕하세요, {{ user.name }}님!</p>
      <button @click="authStore.logout" class="px-4 py-2 text-sm text-white bg-red-500 rounded hover:bg-red-600 transition">
        로그아웃
      </button>
    </div>

    <NuxtLink v-else to="/login" class="text-lg font-semibold text-white">로그인</NuxtLink>
  </nav>
  </div>
</template>