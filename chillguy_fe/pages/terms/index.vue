<template>
  <div class="bg-neutral-900 min-h-screen text-neutral-300">
    <div class="max-w-4xl mx-auto px-4 py-12">
      <h1 class="text-3xl font-bold text-white mb-8 flex items-center">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-8 w-8 mr-3 text-blue-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 20H5a2 2 0 01-2-2V6a2 2 0 012-2h10a2 2 0 012 2v1m2 13a2 2 0 01-2-2V7m2 13a2 2 0 002-2V9a2 2 0 00-2-2h-2m-4-3H9M7 16h6M7 8h6v4H7V8z" />
        </svg>
        서비스 이용약관
      </h1>
      
      <div class="prose prose-invert prose-lg max-w-none">
        <p class="text-neutral-300 mb-6 leading-relaxed">
          본 약관은 Chillguy Music(이하 '당사')이 제공하는 모든 서비스(이하 '서비스')의 이용과 관련하여 당사와 이용자 간의 권리, 의무 및 책임사항 등을 규정함을 목적으로 합니다.
        </p>
        
        <div class="mb-8 bg-neutral-800/50 p-5 rounded-lg">
          <h3 class="text-xl font-semibold text-white mb-3 flex items-center">
            <span class="text-blue-400 mr-2">1.</span> 서비스 개요
          </h3>
          <ul class="list-disc pl-6 space-y-2 text-neutral-300">
            <li>당사는 인공지능 기반의 음악 추천 서비스를 제공합니다.</li>
            <li>사용자의 활동, 기분, 날씨 정보 등을 바탕으로 음악을 추천하며, YouTube Music API와 Google Gemini AI를 활용합니다.</li>
          </ul>
        </div>
        
        <div class="mb-8 bg-neutral-800/50 p-5 rounded-lg">
          <h3 class="text-xl font-semibold text-white mb-3 flex items-center">
            <span class="text-blue-400 mr-2">2.</span> 이용자 의무
          </h3>
          <ul class="list-disc pl-6 space-y-2 text-neutral-300">
            <li>타인의 정보를 도용하거나 허위 정보를 제공하지 않아야 합니다.</li>
            <li>서비스 이용 중 저작권 및 제3자의 권리를 침해해서는 안 됩니다.</li>
          </ul>
        </div>
        
        <div class="mb-8 bg-neutral-800/50 p-5 rounded-lg">
          <h3 class="text-xl font-semibold text-white mb-3 flex items-center">
            <span class="text-blue-400 mr-2">3.</span> AI 추천 서비스에 대한 고지
          </h3>
          <ul class="list-disc pl-6 space-y-2 text-neutral-300">
            <li>본 서비스는 Google Gemini API(Gemini 2.0 Flash)를 활용하여 AI 기반 추천 결과를 제공합니다.</li>
            <li>추천 결과는 AI 분석에 기반한 것으로, 콘텐츠의 정확성, 적합성, 신뢰성은 보장되지 않으며, 당사는 이에 대한 책임을 지지 않습니다.</li>
            <li>이용자는 AI가 생성한 결과를 자신의 판단에 따라 활용해야 합니다.</li>
          </ul>
        </div>
        
        <div class="mb-8 bg-neutral-800/50 p-5 rounded-lg">
          <h3 class="text-xl font-semibold text-white mb-3 flex items-center">
            <span class="text-blue-400 mr-2">4.</span> 서비스 제공의 변경 및 중단
          </h3>
          <ul class="list-disc pl-6 space-y-2 text-neutral-300">
            <li>당사는 서비스의 일부 또는 전부를 사전 공지 없이 수정, 변경, 중단할 수 있습니다.</li>
          </ul>
        </div>
        
        <div class="mb-8 bg-neutral-800/50 p-5 rounded-lg">
          <h3 class="text-xl font-semibold text-white mb-3 flex items-center">
            <span class="text-blue-400 mr-2">5.</span> 저작권
          </h3>
          <ul class="list-disc pl-6 space-y-2 text-neutral-300">
            <li>서비스에서 제공되는 음악, 텍스트, 이미지 등의 콘텐츠는 관련 법령에 따라 보호됩니다.</li>
            <li>이용자는 서비스를 통해 얻은 콘텐츠를 무단으로 복제, 배포, 상업적으로 이용할 수 없습니다.</li>
          </ul>
        </div>
        
        <div class="mb-8 bg-neutral-800/50 p-5 rounded-lg">
          <h3 class="text-xl font-semibold text-white mb-3 flex items-center">
            <span class="text-blue-400 mr-2">6.</span> 외부 서비스 연동
          </h3>
          <ul class="list-disc pl-6 space-y-2 text-neutral-300">
            <li>본 서비스는 YouTube API 및 Google OAuth2, Google Gemini API 등 외부 플랫폼과 연동됩니다.</li>
            <li>이용자는 이를 통해 생성된 콘텐츠 및 기능 사용 시 각 플랫폼의 약관을 준수해야 하며, 본 서비스는 각 플랫폼의 이용 정책을 따릅니다.</li>
            <li class="pl-6 mt-2">
              <a href="https://www.youtube.com/t/terms" target="_blank" class="text-blue-400 hover:text-blue-300 hover:underline transition-colors flex items-center">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4 mr-1" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 6H6a2 2 0 00-2 2v10a2 2 0 002 2h10a2 2 0 002-2v-4M14 4h6m0 0v6m0-6L10 14" />
                </svg>
                YouTube Terms of Service
              </a>
            </li>
            <li class="pl-6">
              <a href="https://policies.google.com/privacy" target="_blank" class="text-blue-400 hover:text-blue-300 hover:underline transition-colors flex items-center">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4 mr-1" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 6H6a2 2 0 00-2 2v10a2 2 0 002 2h10a2 2 0 002-2v-4M14 4h6m0 0v6m0-6L10 14" />
                </svg>
                Google Privacy Policy
              </a>
            </li>
          </ul>
        </div>
        
        <div class="mb-8 bg-neutral-800/50 p-5 rounded-lg">
          <h3 class="text-xl font-semibold text-white mb-3 flex items-center">
            <span class="text-blue-400 mr-2">7.</span> 면책 조항
          </h3>
          <ul class="list-disc pl-6 space-y-2 text-neutral-300">
            <li>당사는 서비스 오류 또는 외부 플랫폼의 문제로 발생한 손해에 대해 책임을 지지 않습니다.</li>
            <li>AI 추천 결과에 대한 판단 및 책임은 전적으로 이용자에게 있습니다.</li>
          </ul>
        </div>
        
        <div class="mb-8 bg-neutral-800/50 p-5 rounded-lg">
          <h3 class="text-xl font-semibold text-white mb-3 flex items-center">
            <span class="text-blue-400 mr-2">8.</span> 약관의 변경
          </h3>
          <ul class="list-disc pl-6 space-y-2 text-neutral-300">
            <li>본 약관은 관련 법령 또는 회사 정책에 따라 변경될 수 있으며, 변경 시 공지사항을 통해 안내됩니다.</li>
          </ul>
        </div>
        
        <div class="mb-8 bg-neutral-800/50 p-5 rounded-lg">
          <h3 class="text-xl font-semibold text-white mb-3 flex items-center">
            <span class="text-blue-400 mr-2">9.</span> 문의
          </h3>
          <ul class="list-disc pl-6 space-y-2 text-neutral-300">
            <li>본 약관과 관련된 문의는 이메일(jumbochips1@gmail.com)로 연락 바랍니다.</li>
          </ul>
        </div>
        
        <div class="mt-10 pt-6 border-t border-neutral-700 text-center">
          <p class="text-neutral-400">
            본 약관은 <span class="text-white font-semibold">2025년 3월 25일</span>부터 적용됩니다.
          </p>
        </div>
      </div>
      
      <div class="mt-12 text-center">
        <NuxtLink to="/" class="inline-flex items-center text-blue-400 hover:text-blue-300">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 19l-7-7m0 0l7-7m-7 7h18" />
          </svg>
          홈으로 돌아가기
        </NuxtLink>
      </div>
    </div>
  </div>
</template>

