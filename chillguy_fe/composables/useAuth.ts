import type { User } from '~/types/user';

export const useAuth = () => {
  const user = useState<User | null>('user', () => null);
  const config = useRuntimeConfig();

  const fetchUser = async () => {
    try {
      const { data } = await useFetch<User>(`${config.public.apiBaseUrl}/api/user/me`, {
        credentials: 'include' // 쿠키 포함
      });
      if (data.value) {
        user.value = data.value;
      }
    } catch (error) {
      user.value = null;
    }
  };

  const logout = async () => {
    await useFetch(`${config.public.apiBaseUrl}/logout`, {
      method: 'POST',
      credentials: 'include'
    });
    user.value = null;
  };

  return { user, fetchUser, logout };
};
