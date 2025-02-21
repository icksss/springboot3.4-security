# Spring Security Study Project

## 프로젝트 소개
이 프로젝트는 Spring Security의 다양한 기능을 학습하고 테스트하기 위한 프로젝트입니다.

## 기술 스택
- Java 17
- Spring Boot 3.x
- Spring Security 6.x
- Gradle
- SLF4J & Logback

## 주요 기능
1. Spring Security Filter Chain 설정
   - API 경로별 보안 설정
   - 권한 기반 접근 제어

2. 비동기 처리
   - Callable을 이용한 비동기 요청 처리
   - 타임아웃 설정

3. 로깅
   - SLF4J를 이용한 로그 관리
   - 개발 환경별 로그 레벨 설정

## 프로젝트 구조


├── main/
│ ├── java/
│ │ └── com/
│ │ └── kr/
│ │ └── jikim/
│ │ └── config/
│ │ └── SecurityConfig.java
│ │ ├── controllers/
│ │ │ └── MainController.java
│ │ └── JikimApplication.java
│ └── resources/
│ └── application.properties
```

## 시작하기

### 필수 조건
- JDK 17 이상
- Gradle 8.x

### 설치 및 실행
```bash
# 프로젝트 클론
git clone [repository-url]

# 프로젝트 디렉토리로 이동
cd [project-directory]

# 빌드
./gradlew build

# 실행
./gradlew bootRun
```

### 개발 모드
```bash
# 자동 리로드 활성화
./gradlew bootRun --continuous
```

## API 엔드포인트

### 보안 테스트
- GET `/testfilterbefore` - 필터 체인 테스트 (리다이렉션)
- GET `/testfilterafter` - 필터 체인 테스트 (최종 응답)
- GET `/user` - 일반 사용자 접근
- GET `/admin` - 관리자 접근

### 비동기 처리
- GET `/async` - Callable 비동기 처리 테스트

## 설정

### application.properties
```properties
# 로깅 설정
logging.level.root=INFO
logging.level.com.kr.jikim=DEBUG

# 개발 도구
spring.devtools.restart.enabled=true
spring.devtools.livereload.enabled=true
```

## 참고 사항
- Spring Security 6.0 이상에서는 `requestMatcher`가 `securityMatcher`로 변경되었습니다.
- 개발 환경에서는 devtools를 통한 자동 리로드가 지원됩니다.

## 라이선스
이 프로젝트는 MIT 라이선스 하에 있습니다.