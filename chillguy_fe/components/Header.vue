<script setup lang="ts">
import { useAuthStore } from '~/stores/auth'
import { storeToRefs } from 'pinia';

const authStore = useAuthStore();
const { user } = storeToRefs(authStore);
</script>

<template>
        <div>
  <nav class="flex justify-between items-center p-4 bg-neutral-900 shadow-sm">
    <NuxtLink to="/" class="text-lg font-semibold text-white">Home</NuxtLink>

    <div v-if="user" class="flex items-center gap-4">
      <img :src="user.profileImage" alt="프로필 이미지" class="w-10 h-10 rounded-full border border-gray-300" />
      <p class="text-white font-medium">Hello, {{ user.name }}!</p>
      <button @click="authStore.logout" class="px-4 py-2 text-sm text-white bg-red-500 rounded hover:bg-red-600 transition">
        로그아웃
      </button>
    </div>

    <NuxtLink v-else to="/login" class="text-lg font-semibold text-white">Sign In</NuxtLink>
  </nav>
  </div>
</template>