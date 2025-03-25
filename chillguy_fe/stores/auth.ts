// stores/auth.ts
import { defineStore } from 'pinia'
import type { User } from '~/types/user'

export const useAuthStore = defineStore('auth', {
  state: () => ({
    user: null as User | null
  }),
  actions: {
    async fetchUser() {
      const config = useRuntimeConfig();
      const { $apiFetch } = useNuxtApp();

      try {
        const data = await $apiFetch<User>(`${config.public.apiBaseUrl}/api/user`)
        this.user = data;
        console.log('사용자 정보 불러오기 성공:', data);
      } catch (e) {
        console.error('사용자 정보 요청 실패', e);
        this.user = null;
      }
    },
    async logout() {
      const config = useRuntimeConfig();
      const { $apiFetch } = useNuxtApp();
      const router = useRouter();

      try {
        await $apiFetch(`${config.public.apiBaseUrl}/api/auth/logout`, { method: 'POST' })
        this.user = null;
        await router.push('/');
      } catch (e) {
        console.error('로그아웃 실패', e);
      }
    }
  }
})
