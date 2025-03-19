<script setup>
import { ref } from "vue";
import { useRuntimeConfig } from "#app";
import QuickOption from "~/components/QuickOption.vue";
import TextInput from "~/components/TextInput.vue";
import UserInputOptionButton from "~/components/UserInputOptionButton.vue";
import MusicRecommendationList from "~/components/MusicRecommendationList.vue";

const activeSection = ref("quickOptions");
const isLoading = ref(false);
const recommendations = ref([]);
const aiExplanation = ref('');

const config = useRuntimeConfig();

// 음악 추천 API 호출 함수
const getMusicRecommendations = async (params) => {
  try {
    isLoading.value = true;
    recommendations.value = [];
    aiExplanation.value = '';

    const endpoint = activeSection.value === "quickOptions" 
      ? "/api/music/recommend" 
      : "/api/music/recommend/text";
    
    const data = await useNuxtApp().$apiFetch(endpoint, {
      method: "POST",
      body: JSON.stringify(params),
    });

    console.log("Recommendations:", data);
    
    // 백엔드에서 받은 데이터를 직접 할당
    if (Array.isArray(data)) {
      // 배열 형태로 받은 경우 (예시 데이터와 같은 형식)
      recommendations.value = data.map(item => ({
        ...item,
        // 앨범 커버 URL이 없는 경우 기본 이미지 설정
        albumCoverUrl: `/placeholder.svg?height=200&width=200&text=${encodeURIComponent(item.album)}`
      }));
      aiExplanation.value = "AI가 당신의 상황에 맞는 음악을 추천했습니다.";
    } else if (data.recommendations) {
      // 객체 형태로 받은 경우 (recommendations 필드가 있는 경우)
      recommendations.value = data.recommendations.map(item => ({
        ...item,
        albumCoverUrl: `/placeholder.svg?height=200&width=200&text=${encodeURIComponent(item.album)}`
      }));
      aiExplanation.value = data.explanation || "AI가 당신의 상황에 맞는 음악을 추천했습니다.";
    } else {
      console.error("Unexpected data format:", data);
    }
  } catch (error) {
    console.error("Failed to get recommendations:", error);
  } finally {
    isLoading.value = false;
  }
};

// QuickOption 컴포넌트에서 호출할 함수
const handleQuickOptionSubmit = (options) => {
  getMusicRecommendations({
    type: 'options',
    data: options
  });
};

// TextInput 컴포넌트에서 호출할 함수
const handleTextInputSubmit = (text) => {
  getMusicRecommendations({
    type: 'text',
    data: { text }
  });
};


</script>

<template>
  <div class="min-h-screen bg-gradient-to-b from-neutral-900 to-neutral-800 text-white p-4">
    <div class="max-w-4xl mx-auto py-8">
      <!-- Hero Section -->
      <HeroSection />

      <!-- Tabs -->
      <div class="mb-8">
        <UserInputOptionButton v-model:activeSection="activeSection" />
      </div>
      
      <!-- Input Sections -->
      <div v-if="activeSection === 'quickOptions'">
        <QuickOption @submit="handleQuickOptionSubmit" />
      </div>
      
      <div v-if="activeSection === 'textInput'">
        <TextInput @submit="handleTextInputSubmit" />
      </div>

      <!-- 음악 추천 결과 -->
      <MusicRecommendationList 
        :recommendations="recommendations"
        :aiExplanation="aiExplanation"
        :loading="isLoading"
      />
    </div>
  </div>
</template>