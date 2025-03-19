import { useRuntimeConfig } from "#imports";

export default defineNuxtPlugin((nuxtApp) => {
  const config = useRuntimeConfig();
  // 자동으로 쿠키를 포함하는 fetch API
  const apiFetch = $fetch.create({
    baseURL: `${config.public.apiBaseUrl}`,
    credentials: "include", // 쿠키 자동 포함
    retry: 0, // 401 발생 시 직접 리프레시 요청
    headers: {
      "Content-Type": "application/json",
    },
    onResponseError: async ({ response, request, options }) => {
      if (response.status === 401) {
        console.warn("Access token expired. Attempting to refresh...");

        try {
          // 리프레시 토큰 요청 (쿠키 자동 포함)
          const refreshResponse = await $fetch<{ accessToken: string }>("/api/auth/refresh", {
            method: "POST" as "POST",
            credentials: "include", // 쿠키 자동 포함
          });

          if (refreshResponse.accessToken) {
            console.log("Token refreshed successfully!");

            // 기존 요청 재시도
            return $fetch(request, { ...options, method: options.method as "GET" | "POST" | "PUT" | "DELETE" });
          }
        } catch (error) {
          console.error("Refresh token failed, logging out...");
          navigateTo("/"); // 리프레시 토큰 만료 시 로그아웃 처리
        }
      }
    },
  });

  return {
    provide: {
      apiFetch,
    },
  };
});
