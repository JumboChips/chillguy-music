<template>
  <div class="">
    <!-- Header 영역 -->
    <Header />

    <!-- 메인 콘텐츠 영역 -->
    <main class="">
      <NuxtPage />
    </main>

    <!-- Footer 영역 -->
    <Footer />
  </div>
</template>

<script setup lang="ts">
import Header from '~/components/Header.vue';
import Footer from '~/components/Footer.vue';
import { useAuthStore } from '~/stores/auth'

const authStore = useAuthStore();

// SSR에서도 실행됨
await useAsyncData('auth-user', () => authStore.fetchUser())
</script>