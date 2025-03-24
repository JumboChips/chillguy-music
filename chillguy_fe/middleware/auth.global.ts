import { useAuthStore } from '~/stores/auth'


export default defineNuxtRouteMiddleware(async () => {
  const store = useAuthStore();
  if (!store.user) {
    await store.fetchUser();
  }
})