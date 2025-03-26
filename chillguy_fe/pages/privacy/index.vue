<template>
  <div class="bg-neutral-900 min-h-screen text-neutral-300">
    <div class="max-w-4xl mx-auto px-4 py-12">
      <h1 class="text-3xl font-bold text-white mb-8 flex items-center">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-8 w-8 mr-3 text-blue-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z" />
        </svg>
        개인정보처리방침
      </h1>
      
      <div class="prose prose-invert prose-lg max-w-none">
        <p class="text-neutral-300 mb-6 leading-relaxed">
          Chillguy Music(이하 '당사')은 개인정보 보호법 등 관련 법령을 준수하며, 사용자(이하 '이용자')의 개인정보를 보호하기 위해 최선을 다하고 있습니다. 본 방침은 당사의 웹사이트 및 서비스에서 수집하는 개인정보에 대해 설명합니다.
        </p>
        
        <div class="mb-8 bg-neutral-800/50 p-5 rounded-lg">
          <h3 class="text-xl font-semibold text-white mb-3 flex items-center">
            <span class="text-blue-400 mr-2">1.</span> 수집하는 개인정보 항목
          </h3>
          <ul class="list-disc pl-6 space-y-2 text-neutral-300">
            <li>구글 OAuth2 로그인을 통해 수집: 이름, 이메일 주소, 프로필 이미지 URL</li>
            <li>AI 추천 기능 사용 시 입력되는 정보: 기분, 날씨, 활동, 텍스트 입력 등 비식별 정보</li>
          </ul>
        </div>
        
        <div class="mb-8 bg-neutral-800/50 p-5 rounded-lg">
          <h3 class="text-xl font-semibold text-white mb-3 flex items-center">
            <span class="text-blue-400 mr-2">2.</span> 개인정보 수집 목적
          </h3>
          <ul class="list-disc pl-6 space-y-2 text-neutral-300">
            <li>사용자 식별 및 로그인 기능 제공</li>
            <li>개인 맞춤형 음악 추천 서비스 제공 (AI 분석 포함)</li>
            <li>서비스 이용 통계 및 품질 개선</li>
          </ul>
        </div>
        
        <div class="mb-8 bg-neutral-800/50 p-5 rounded-lg">
          <h3 class="text-xl font-semibold text-white mb-3 flex items-center">
            <span class="text-blue-400 mr-2">3.</span> AI 기술 활용 안내
          </h3>
          <ul class="list-disc pl-6 space-y-2 text-neutral-300">
            <li>당사는 Google Gemini API(Gemini 2.0 Flash)를 활용하여 AI 기반 음악 추천 서비스를 제공합니다.</li>
            <li>사용자가 입력한 데이터(예: 기분, 날씨, 활동 등)는 AI 모델 분석을 위해 일시적으로 사용되며, 저장되지 않습니다.</li>
            <li>AI 추천 결과는 비개인화된 방식으로 처리되며, Gemini API의 개인정보 보호 정책을 준수합니다.</li>
          </ul>
        </div>
        
        <div class="mb-8 bg-neutral-800/50 p-5 rounded-lg">
          <h3 class="text-xl font-semibold text-white mb-3 flex items-center">
            <span class="text-blue-400 mr-2">4.</span> 개인정보 보유 및 이용 기간
          </h3>
          <ul class="list-disc pl-6 space-y-2 text-neutral-300">
            <li>이용자가 서비스 탈퇴 또는 삭제 요청 시 즉시 파기</li>
            <li>단, 관련 법령에 따라 일정 기간 보존이 필요한 경우 해당 법령에 따름</li>
          </ul>
        </div>
        
        <div class="mb-8 bg-neutral-800/50 p-5 rounded-lg">
          <h3 class="text-xl font-semibold text-white mb-3 flex items-center">
            <span class="text-blue-400 mr-2">5.</span> 개인정보 제3자 제공
          </h3>
          <ul class="list-disc pl-6 space-y-2 text-neutral-300">
            <li>당사는 원칙적으로 이용자의 개인정보를 외부에 제공하지 않습니다.</li>
            <li>단, 이용자의 동의가 있거나 법령에 따라 필요한 경우 예외적으로 제공될 수 있습니다.</li>
          </ul>
        </div>
        
        <div class="mb-8 bg-neutral-800/50 p-5 rounded-lg">
          <h3 class="text-xl font-semibold text-white mb-3 flex items-center">
            <span class="text-blue-400 mr-2">6.</span> 이용자의 권리
          </h3>
          <ul class="list-disc pl-6 space-y-2 text-neutral-300">
            <li>언제든지 개인정보 열람, 수정, 삭제를 요청할 수 있습니다.</li>
            <li>요청은 이메일(jumbochips1@gmail.com)을 통해 접수 가능합니다.</li>
          </ul>
        </div>
        
        <div class="mb-8 bg-neutral-800/50 p-5 rounded-lg">
          <h3 class="text-xl font-semibold text-white mb-3 flex items-center">
            <span class="text-blue-400 mr-2">7.</span> 쿠키 사용 여부
          </h3>
          <ul class="list-disc pl-6 space-y-2 text-neutral-300">
            <li>당사는 사용자 경험 향상을 위해 쿠키를 사용할 수 있습니다.</li>
          </ul>
        </div>
        
        <div class="mb-8 bg-neutral-800/50 p-5 rounded-lg">
          <h3 class="text-xl font-semibold text-white mb-3 flex items-center">
            <span class="text-blue-400 mr-2">8.</span> 외부 서비스 이용 고지
          </h3>
          <ul class="list-disc pl-6 space-y-2 text-neutral-300">
            <li>본 서비스는 Google OAuth2 로그인 및 YouTube API, Gemini AI API를 활용하고 있으며, 이에 따라 Google의 개인정보 보호정책 및 API 서비스 약관을 준수합니다.</li>
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
            <span class="text-blue-400 mr-2">9.</span> 개인정보 보호책임자
          </h3>
          <ul class="list-disc pl-6 space-y-2 text-neutral-300">
            <li>이름: Chillguy Music 운영팀</li>
            <li>이메일: jumbochips1@gmail.com</li>
          </ul>
        </div>
        
        <div class="mt-10 pt-6 border-t border-neutral-700 text-center">
          <p class="text-neutral-400">
            본 방침은 <span class="text-white font-semibold">2025년 3월 25일</span>부터 적용됩니다.
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

