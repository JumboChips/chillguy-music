import { useRuntimeConfig } from "#imports";
import type { User } from "~/types/user";

export function useAuth() {
  const user = ref<User | null>(null);
  const config = useRuntimeConfig();
  const { $apiFetch } = useNuxtApp();



  // 사용자 정보 요청
  const fetchUser = async () => {
    try {
      const data = await $apiFetch<User>(`${config.public.apiBaseUrl}/api/user`);
      user.value = data;
      console.log("사용자 정보 불러오기 성공:", data); // 디버깅용
    } catch (error) {
      console.error("사용자 정보를 불러올 수 없습니다.", error);
    }
  };

  // 액세스 토큰 가져오기
  const getAccessToken = () => {
    return localStorage.getItem('accessToken');
  };


  // 로그아웃 (쿠키 삭제)
  const logout = async () => {
    try {
      await $apiFetch(`${config.public.apiBaseUrl}/auth/logout`, { method: "POST" });
    } catch (error) {
      console.error("로그아웃 실패", error);
    }

    // ✅ 로그아웃 후 홈페이지로 이동
    window.location.href = "/";
  };

  return { user, fetchUser, logout, getAccessToken };
}
