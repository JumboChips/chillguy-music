// https://nuxt.com/docs/api/configuration/nuxt-config
import tailwindcss from "@tailwindcss/vite";


export default defineNuxtConfig({
  compatibilityDate: '2024-11-01',
  devtools: { enabled: true },
  css: ['~/assets/css/main.css'],
  vite: {
    plugins: [
      tailwindcss(),
    ],
  },
  modules: [
    '@pinia/nuxt',
  ],
  runtimeConfig: {
    public: {
      apiBaseUrl: process.env.CHILLGUY_FRONTEND_API_URL || 'https://chillguy-music.com'
    }
  },
})
