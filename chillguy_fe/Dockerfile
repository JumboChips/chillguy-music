# 최신 Node.js 22 LTS 사용 (Slim 버전으로 가볍게)
FROM node:22-slim

# 작업 디렉토리 설정
WORKDIR /app

# 환경 변수 설정 (Nuxt 런타임 환경)
ENV NUXT_HOST=0.0.0.0
ENV NUXT_PORT=3000

# package.json, package-lock.json 복사 후 의존성 설치
COPY package.json package-lock.json ./
RUN npm ci --omit=dev  # ⬅ 더 빠르고 안정적인 npm 설치 (개발용 패키지 제외)

# 전체 프로젝트 복사
COPY . .

# Nuxt 앱 빌드
RUN npm run build

# 포트 노출
EXPOSE 3000

# Nuxt 실행
CMD ["npm", "run", "preview"]
