<script setup lang="ts">
const config = useRuntimeConfig();
const loginWithGoogle = () => {
  window.location.href = `${config.public.apiBaseUrl}/oauth2/authorization/google`;
};
</script>

<template>
  <button @click="loginWithGoogle">
    Google로 로그인
  </button>
</template>
