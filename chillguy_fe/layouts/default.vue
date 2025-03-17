<template>
  <div class="">
    <!-- Header 영역 -->
    <Header />

    <!-- 메인 콘텐츠 영역 -->
    <main class="">
      <NuxtPage />
    </main>

    <!-- Footer 영역 -->

  </div>
</template>

<script setup lang="ts">
import Header from '~/components/Header.vue';
</script>