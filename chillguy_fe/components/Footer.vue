<template>
  <footer class="bg-neutral-900 text-neutral-300 py-8 border-t border-neutral-800">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <div class="flex flex-col md:flex-row justify-between items-start space-y-4 md:space-y-0 md:space-x-8">
        <!-- Logo and description -->
        <div>
          <h2 class="text-white text-xl font-bold mb-4">Chillguy Music</h2>
          <p class="text-sm">
            인공지능 기반의 음악 추천 서비스로, 당신의 활동, 기분, 날씨 정보 등을 바탕으로 
            최적의 음악을 추천해 드립니다.
          </p>
        </div>
        
        <!-- Legal -->
        <div>
          <h3 class="text-white text-lg font-semibold mb-4">Legal</h3>
          <ul class="space-y-2">
            <li>
              <button 
                @click="showPrivacyPolicy = true" 
                class="hover:text-white transition-colors cursor-pointer"
              >
                개인정보처리방침
              </button>
            </li>
            <li>
              <button 
                @click="showTermsOfService = true" 
                class="hover:text-white transition-colors cursor-pointer"
              >
                서비스 이용약관
              </button>
            </li>
          </ul>
        </div>
      </div>
      
      <div class="mt-8 pt-6 border-t border-neutral-800 text-center text-sm">
        <p>&copy; {{ new Date().getFullYear() }} Chillguy Music. All rights reserved.</p>
      </div>
    </div>
    
    <!-- Privacy Policy Modal -->
    <Teleport to="body">
      <div v-if="showPrivacyPolicy" class="fixed inset-0 bg-black bg-opacity-75 z-50 flex items-center justify-center p-4 overflow-y-auto">
        <div class="bg-neutral-800 rounded-lg max-w-3xl w-full max-h-[90vh] overflow-y-auto">
          <div class="p-6">
            <div class="flex justify-between items-center mb-6">
              <h2 class="text-2xl font-bold text-white">개인정보처리방침</h2>
              <button @click="showPrivacyPolicy = false" class="text-neutral-400 hover:text-white">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                </svg>
              </button>
            </div>
            <div class="prose prose-invert prose-lg max-w-none">
              <p class="text-neutral-300 mb-6">
                Chillguy Music(이하 '당사')은 개인정보 보호법 등 관련 법령을 준수하며, 사용자(이하 '이용자')의 개인정보를 보호하기 위해 최선을 다하고 있습니다. 본 방침은 당사의 웹사이트 및 서비스에서 수집하는 개인정보에 대해 설명합니다.
              </p>
              
              <div class="mb-6">
                <h3 class="text-xl font-semibold text-white mb-3">1. 수집하는 개인정보 항목</h3>
                <ul class="list-disc pl-6 space-y-1 text-neutral-300">
                  <li>구글 OAuth2 로그인을 통해 수집: 이름, 이메일 주소, 프로필 이미지 URL</li>
                  <li>AI 추천 기능 사용 시 입력되는 정보: 기분, 날씨, 활동, 텍스트 입력 등 비식별 정보</li>
                </ul>
              </div>
              
              <div class="mb-6">
                <h3 class="text-xl font-semibold text-white mb-3">2. 개인정보 수집 목적</h3>
                <ul class="list-disc pl-6 space-y-1 text-neutral-300">
                  <li>사용자 식별 및 로그인 기능 제공</li>
                  <li>개인 맞춤형 음악 추천 서비스 제공 (AI 분석 포함)</li>
                  <li>서비스 이용 통계 및 품질 개선</li>
                </ul>
              </div>
              
              <div class="mb-6">
                <h3 class="text-xl font-semibold text-white mb-3">3. AI 기술 활용 안내</h3>
                <ul class="list-disc pl-6 space-y-1 text-neutral-300">
                  <li>당사는 Google Gemini API(Gemini 2.0 Flash)를 활용하여 AI 기반 음악 추천 서비스를 제공합니다.</li>
                  <li>사용자가 입력한 데이터(예: 기분, 날씨, 활동 등)는 AI 모델 분석을 위해 일시적으로 사용되며, 저장되지 않습니다.</li>
                  <li>AI 추천 결과는 비개인화된 방식으로 처리되며, Gemini API의 개인정보 보호 정책을 준수합니다.</li>
                </ul>
              </div>
              
              <div class="mb-6">
                <h3 class="text-xl font-semibold text-white mb-3">4. 개인정보 보유 및 이용 기간</h3>
                <ul class="list-disc pl-6 space-y-1 text-neutral-300">
                  <li>이용자가 서비스 탈퇴 또는 삭제 요청 시 즉시 파기</li>
                  <li>단, 관련 법령에 따라 일정 기간 보존이 필요한 경우 해당 법령에 따름</li>
                </ul>
              </div>
              
              <div class="mb-6">
                <h3 class="text-xl font-semibold text-white mb-3">5. 개인정보 제3자 제공</h3>
                <ul class="list-disc pl-6 space-y-1 text-neutral-300">
                  <li>당사는 원칙적으로 이용자의 개인정보를 외부에 제공하지 않습니다.</li>
                  <li>단, 이용자의 동의가 있거나 법령에 따라 필요한 경우 예외적으로 제공될 수 있습니다.</li>
                </ul>
              </div>
              
              <div class="mb-6">
                <h3 class="text-xl font-semibold text-white mb-3">6. 이용자의 권리</h3>
                <ul class="list-disc pl-6 space-y-1 text-neutral-300">
                  <li>언제든지 개인정보 열람, 수정, 삭제를 요청할 수 있습니다.</li>
                  <li>요청은 이메일(jumbochips1@gmail.com)을 통해 접수 가능합니다.</li>
                </ul>
              </div>
              
              <div class="mb-6">
                <h3 class="text-xl font-semibold text-white mb-3">7. 쿠키 사용 여부</h3>
                <ul class="list-disc pl-6 space-y-1 text-neutral-300">
                  <li>당사는 사용자 경험 향상을 위해 쿠키를 사용할 수 있습니다.</li>
                </ul>
              </div>
              
              <div class="mb-6">
                <h3 class="text-xl font-semibold text-white mb-3">8. 외부 서비스 이용 고지</h3>
                <ul class="list-disc pl-6 space-y-1 text-neutral-300">
                  <li>본 서비스는 Google OAuth2 로그인 및 YouTube API, Gemini AI API를 활용하고 있으며, 이에 따라 Google의 개인정보 보호정책 및 API 서비스 약관을 준수합니다.</li>
                  <li class="pl-6">
                    <a href="https://www.youtube.com/t/terms" target="_blank" class="text-blue-400 hover:underline">YouTube Terms of Service</a>
                  </li>
                  <li class="pl-6">
                    <a href="https://policies.google.com/privacy" target="_blank" class="text-blue-400 hover:underline">Google Privacy Policy</a>
                  </li>
                </ul>
              </div>
              
              <div class="mb-6">
                <h3 class="text-xl font-semibold text-white mb-3">9. 개인정보 보호책임자</h3>
                <ul class="list-disc pl-6 space-y-1 text-neutral-300">
                  <li>이름: Chillguy Music 운영팀</li>
                  <li>이메일: jumbochips1@gmail.com</li>
                </ul>
              </div>
              
              <p class="text-neutral-300 mt-8 pt-4 border-t border-neutral-700">
                본 방침은 2025년 3월 25일부터 적용됩니다.
              </p>
            </div>
          </div>
        </div>
      </div>
    </Teleport>
    
    <!-- Terms of Service Modal -->
    <Teleport to="body">
      <div v-if="showTermsOfService" class="fixed inset-0 bg-black bg-opacity-75 z-50 flex items-center justify-center p-4 overflow-y-auto">
        <div class="bg-neutral-800 rounded-lg max-w-3xl w-full max-h-[90vh] overflow-y-auto">
          <div class="p-6">
            <div class="flex justify-between items-center mb-6">
              <h2 class="text-2xl font-bold text-white">서비스 이용약관</h2>
              <button @click="showTermsOfService = false" class="text-neutral-400 hover:text-white">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                </svg>
              </button>
            </div>
            <div class="prose prose-invert prose-lg max-w-none">
              <p class="text-neutral-300 mb-6">
                본 약관은 Chillguy Music(이하 '당사')이 제공하는 모든 서비스(이하 '서비스')의 이용과 관련하여 당사와 이용자 간의 권리, 의무 및 책임사항 등을 규정함을 목적으로 합니다.
              </p>
              
              <div class="mb-6">
                <h3 class="text-xl font-semibold text-white mb-3">1. 서비스 개요</h3>
                <ul class="list-disc pl-6 space-y-1 text-neutral-300">
                  <li>당사는 인공지능 기반의 음악 추천 서비스를 제공합니다.</li>
                  <li>사용자의 활동, 기분, 날씨 정보 등을 바탕으로 음악을 추천하며, YouTube Music API와 Google Gemini AI를 활용합니다.</li>
                </ul>
              </div>
              
              <div class="mb-6">
                <h3 class="text-xl font-semibold text-white mb-3">2. 이용자 의무</h3>
                <ul class="list-disc pl-6 space-y-1 text-neutral-300">
                  <li>타인의 정보를 도용하거나 허위 정보를 제공하지 않아야 합니다.</li>
                  <li>서비스 이용 중 저작권 및 제3자의 권리를 침해해서는 안 됩니다.</li>
                </ul>
              </div>
              
              <div class="mb-6">
                <h3 class="text-xl font-semibold text-white mb-3">3. AI 추천 서비스에 대한 고지</h3>
                <ul class="list-disc pl-6 space-y-1 text-neutral-300">
                  <li>본 서비스는 Google Gemini API(Gemini 2.0 Flash)를 활용하여 AI 기반 추천 결과를 제공합니다.</li>
                  <li>추천 결과는 AI 분석에 기반한 것으로, 콘텐츠의 정확성, 적합성, 신뢰성은 보장되지 않으며, 당사는 이에 대한 책임을 지지 않습니다.</li>
                  <li>이용자는 AI가 생성한 결과를 자신의 판단에 따라 활용해야 합니다.</li>
                </ul>
              </div>
              
              <div class="mb-6">
                <h3 class="text-xl font-semibold text-white mb-3">4. 서비스 제공의 변경 및 중단</h3>
                <ul class="list-disc pl-6 space-y-1 text-neutral-300">
                  <li>당사는 서비스의 일부 또는 전부를 사전 공지 없이 수정, 변경, 중단할 수 있습니다.</li>
                </ul>
              </div>
              
              <div class="mb-6">
                <h3 class="text-xl font-semibold text-white mb-3">5. 저작권</h3>
                <ul class="list-disc pl-6 space-y-1 text-neutral-300">
                  <li>서비스에서 제공되는 음악, 텍스트, 이미지 등의 콘텐츠는 관련 법령에 따라 보호됩니다.</li>
                  <li>이용자는 서비스를 통해 얻은 콘텐츠를 무단으로 복제, 배포, 상업적으로 이용할 수 없습니다.</li>
                </ul>
              </div>
              
              <div class="mb-6">
                <h3 class="text-xl font-semibold text-white mb-3">6. 외부 서비스 연동</h3>
                <ul class="list-disc pl-6 space-y-1 text-neutral-300">
                  <li>본 서비스는 YouTube API 및 Google OAuth2, Google Gemini API 등 외부 플랫폼과 연동됩니다.</li>
                  <li>이용자는 이를 통해 생성된 콘텐츠 및 기능 사용 시 각 플랫폼의 약관을 준수해야 하며, 본 서비스는 각 플랫폼의 이용 정책을 따릅니다.</li>
                  <li class="pl-6">
                    <a href="https://www.youtube.com/t/terms" target="_blank" class="text-blue-400 hover:underline">YouTube Terms of Service</a>
                  </li>
                  <li class="pl-6">
                    <a href="https://policies.google.com/privacy" target="_blank" class="text-blue-400 hover:underline">Google Privacy Policy</a>
                  </li>
                </ul>
              </div>
              
              <div class="mb-6">
                <h3 class="text-xl font-semibold text-white mb-3">7. 면책 조항</h3>
                <ul class="list-disc pl-6 space-y-1 text-neutral-300">
                  <li>당사는 서비스 오류 또는 외부 플랫폼의 문제로 발생한 손해에 대해 책임을 지지 않습니다.</li>
                  <li>AI 추천 결과에 대한 판단 및 책임은 전적으로 이용자에게 있습니다.</li>
                </ul>
              </div>
              
              <div class="mb-6">
                <h3 class="text-xl font-semibold text-white mb-3">8. 약관의 변경</h3>
                <ul class="list-disc pl-6 space-y-1 text-neutral-300">
                  <li>본 약관은 관련 법령 또는 회사 정책에 따라 변경될 수 있으며, 변경 시 공지사항을 통해 안내됩니다.</li>
                </ul>
              </div>
              
              <div class="mb-6">
                <h3 class="text-xl font-semibold text-white mb-3">9. 문의</h3>
                <ul class="list-disc pl-6 space-y-1 text-neutral-300">
                  <li>본 약관과 관련된 문의는 이메일(jumbochips1@gmail.com)로 연락 바랍니다.</li>
                </ul>
              </div>
              
              <p class="text-neutral-300 mt-8 pt-4 border-t border-neutral-700">
                본 약관은 2025년 3월 25일부터 적용됩니다.
              </p>
            </div>
          </div>
        </div>
      </div>
    </Teleport>
  </footer>
</template>

<script setup>
import { ref } from 'vue';

const showPrivacyPolicy = ref(false);
const showTermsOfService = ref(false);
</script>

