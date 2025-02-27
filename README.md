# 🎵 Chillguy Music - AI 기반 맞춤형 음악 추천 서비스

![Chillguy Music](chillguy/Chill%20Guy.png)

## 🚀 프로젝트 소개
> **"힘들게 고를 필요 없어. 그냥 듣기만 해. AI가 알아서 다 해줄게."**  
> **"비 오잖아? 재즈 한 곡 어때. 너무 들뜨면 안 되니까."**  
> **"운동한다고? 긴 말 필요 없지. 비트 터지는 걸로 골라놨어."**  

**🎶 음악 추천, 고민하지 마. 그냥 Chill하게 가자.**  
AI가 네 기분과 상황을 알아서 캐치하고, 딱 맞는 노래를 추천해줄게.  
왜 추천했냐고? **그냥 믿어. 이게 지금 너한테 제일 맞는 노래야.**  

☁️ **기분, 날씨, 분위기. 모든 걸 맞춰서 음악을 틀어줄게.**  
아무것도 안 해도 돼. 그냥 Chill 하면 돼. 😌🎵  

---

## 🛠️ **기술 스택**
### **📌 백엔드 (Backend)**
- **언어 & 프레임워크**: Spring Boot (Gradle)
- **데이터베이스**: MySQL
- **API 통신**: RESTful API, OpenAPI (Swagger)
- **인증 및 보안**: OAuth (Google, Spotify, Apple)
- **CI/CD**: GitHub Actions, Docker-compose (Ubuntu Server)

### **🎨 프론트엔드 (Frontend)**
- **언어 & 프레임워크**: Nuxt.js
- **스타일링**: Tailwind CSS

### **🧠 AI & API**
- **음악 추천 알고리즘**: GPT API 활용
- **날씨 데이터**: OpenWeather API
- **음악 검색**: Youtube Music API, Spotify API

---

## 🎯 **핵심 기능**
✅ **AI 기반 음악 추천**  
- 사용자의 기분, 날씨, 활동 정보를 분석하여 맞춤형 음악 추천

✅ **음악 추천 이유 제공**  
- AI가 추천 이유를 설명하는 기능

✅ **유사 곡 추천**  
- 사용자가 좋아할 만한 유사 음악 추천

✅ **날씨 기반 추천**  
- OpenWeather API를 활용하여 현재 날씨에 맞는 음악 추천  

✅ **검색 기능**  
- Youtube Music API, Spotify API를 활용하여 음악 검색  

✅ **사용자 맞춤 플레이리스트**  
- 추천받은 음악을 플레이리스트로 저장  

---

## 📂 **프로젝트 구조**
```plaintext
chillguy-music/
 ├── chillguy-be/  # 백엔드(Spring Boot)
 │   ├── src/main/java/com/chillguy/music/
 │   ├── src/main/resources/application.yml
 │   ├── build.gradle
 │   └── README.md
 │
 ├── chillguy-fe/  # 프론트엔드(Nuxt.js)
 │   ├── components/
 │   ├── pages/
 │   ├── assets/
 │   ├── composables/
 │   ├── package.json
 │   ├── .env
 │   └── README.md
 │
 ├── docker-compose.yml      # 도커 배포 설정
 │
 ├── .github/  # GitHub Actions 및 Issue 템플릿
 │   ├── workflows/
 │   │   ├── backend.yml
 │   │   ├── frontend.yml
 │   ├── ISSUE_TEMPLATE/
 │   │   ├── feature_request.md
 │   │   ├── bug_report.md
 │   │   ├── planning_template.md
 │
 ├── README.md  # 프로젝트 설명
 ├── .gitignore
 ├── LICENSE
 └── package.json
