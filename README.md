
# 📣 서비스 소개

7만여개의 스팀 게임을 고르기 어려운 겜린이들에게 작은 도움을 드리고자 만들었습니다.

플레이한 게임의 성격, 선호하는 게임 장르, 내가 부여한 게임 별점을 기반으로 나의 게임 성향을 분석하고 성향에 맞는 게임을 추천 해드립니다.

**SIXTEAM(식스팀)** 이 개발한 **SSGAME(스겜)** 과 함께  스팀 게임을 즐겨보세요. ☺️

---

## 📽️ 소개 영상

---

## 👀 서비스 시연

### 로그인

<img src="로그인.png" width="100%" height="100%">

### 성향분석

<img src="성향분석.png" width="100%" height="100%">

### 맞춤 게임 추천

<img src="게임추천.png" width="100%" height="100%">

### 내 게임 목록

<img src="내게임목록.png" width="100%" height="100%">

### 회원정보 수정

<img src="회원정보.png" width="100%" height="100%">

---

## 🕹️ 주요 기능

### 👪 회원

- 회원가입
    
    steam api 를 통한 소셜 로그인
    
- 로그인
- 회원 수정
    
    비밀 번호, StreamID, 선호 카테고리를 변경할 수 있습니다.
    
- 회원 탈퇴
- 내 게임 목록
    
    사용자가 플레이한 steam 게임 목록을 불러옵니다.
    
    각 게임마다 별점으로 평가가 가능합니다. 
    
    평가된 별점 점수는 성향 분석에 이용됩니다.
    

### 🎮 게임

- 추천
    
    협업 필터링 알고리즘을 이용하여 플레이한 게임 + 선호 카테고리 + 각 게임별 별점을 통해 게임 성향을 분석하여 사용자성향에 맞는 게임을 추천합니다.
    
- 분석
    
    7가지의 카테고리를 나눠 7각형 형태 그래프로 사용자의 게임 성향을 분석하여 제공합니다.
    
- 정보
    
    추천 게임의 정보를 제공합니다.
    

---

## 🔧 빌드 방법

git clone한 디렉토리에서 git pull을 받습니다.

빌드 디렉토리에는 Dockerfile이 있어야 하며 배포 문서를 참고 하세요.

- Frontend 빌드 <br/>
    $ cd frontend <br/>
    $ sudo docker stop frontend <br/>
    $ sudo docker rm frontend <br/>
    $ npm install <br/>
    $ npm run build <br/>
    $ sudo docker build -t frontend . <br/>
    $ sudo docker run -d -p 3000:3000 --name frontend frontend <br/>
- Backend 빌드 <br/>
    $ cd frontend <br/>
    $ sudo docker stop backend <br/>
    $ sudo docker rm backend <br/>
    $ gradle build <br/>
    $ sudo docker build -t backend . <br/>
    $ docker run -d -p 8080:8080 --name backend backend <br/>
- NodeJs 빌드 <br/>
    $ cd api <br/>
    $ sudo docker stop nodejs <br/>
    $ sudo docker rm nodejs <br/>
    $ sudo docker build -t nodejs . <br/>
    $ sudo docker run -d -p 4000:4000 --name nodejs nodejs <br/>
- Django 빌드 <br/>
    $ cd ssgame_django/ssgame <br/>
    $ sudo docker stop django <br/>
    $ sudo docker rm django <br/>
    $ sudo docker build -t django . <br/>
    $ sudo docker run -d -p 8000:8000 --name django django <br/>
- Nginx 리로드 <br/>
    $ sudo service nginx reload <br/>

---

# 🖥️ 개발

### 📅 프로젝트 기간

- 2022.02.28~2022.03.13 (1~2주차) - 기획 및 설계
- 2022.03.14~2022.04.03 (3~5주차) - 개발
- 2022.04.04~2022.04.08 (6주차) - Bug-Fix 및 프로젝트 마무리(발표준비, UCC, README작성)

---

### ⚙️ 개발 환경

**Backend**

- JAVA 11
- Gradle 7.3.2
- spring boot 2.5.10
- spring data JPA
- spring security
- JWT
- Querydsl
- MYSQL 8.0.28
- Python
- Django
- Pandas
- Node.js

**Frontend**

- React
- Styled-components
- Storybook
- ESLint
- Atomic Component Design Pattern
- Material-ui
- recharts
- react-reveal

**Deploy**

- AWS EC2
- Docker
- Nginx

---

### 🛠️ 서비스 아키텍쳐


---

### 📺 화면 설계

<img src="화면설계.png" width="100%" height="100%">

---

### ⌨️ 기능 명세

<img src="기능명세.png" width="100%" height="100%">

---

### 💡 API 명세

<img src="API명세.png" width="100%" height="100%">

---

### 🌐 ER Diagram

<img src="API명세.png" width="100%" height="100%">
---

### 🗂️ 디렉토리 구조

**Backend (Spring boot)**

```
com.sixteam.ssgame
	ㄴapi
		ㄴmember // 회원관리 (회원가입, 로그인, 로그아웃, 회원 정보 관리)
			ㄴdto
			ㄴentity
				ㄴMember
				ㄴMemberPreferredCategory
			ㄴexception
			ㄴcontoller
			ㄴservice
			ㄴrepository
		ㄴmain // 메인
			ㄴdto
			ㄴentity
			ㄴexception
			ㄴcontoller
			ㄴservice
			ㄴrepository
		ㄴanalyze // 분석 (카테고리)
			ㄴdto
			ㄴentity
				ㄴCategory
				ㄴMemberFrequentGenre
				ㄴRadarChartInfo
			ㄴexception
			ㄴcontoller
			ㄴservice
			ㄴrepository
		ㄴrecommendation // 추천
			ㄴdto
			ㄴentity
				ㄴMemberRecommendedGame
			ㄴexception
			ㄴcontoller
			ㄴservice
			ㄴrepository
		ㄴgame // 게임 정보
			ㄴdto
			ㄴentity
				ㄴTag
				ㄴGameTag
				ㄴGenre
				ㄴGameGenre
				ㄴGameInfo
				ㄴMemberGameList
				ㄴMemberPreferredTag
			ㄴexception
			ㄴcontoller
			ㄴservice
			ㄴrepository
	ㄴglobal // 내용 추가시 수정
			ㄴcommon
				ㄴauth
				ㄴdto
				ㄴutil
				ㄴsteamapi
			ㄴconfig
				ㄴsecurity
			ㄴerror
				ㄴGlobalExceptionHandler.java
				ㄴexception
				ㄴdto
  
```

**Backend (Django)**

```
ssgame_django
	├──ssgame
		└──config
			├──__init__.py
			├──asgi.py
			├──settings.py
			├──urls.py
			├──wsgi.py
			└──ssgameapp
				├──migrations
				├──__init__.py
				├──admin.py
				├──apps.py
				├──models.py
				├──tests.py
				├──urls.py
				└──views.py
			├──db_settings.py
			└──manage.py
	└──ssgamevenv
			├──Include
			├──LIb
			└──Scripts
```

**Frontend**

```
├── src
    ├── App.js
    ├── index.js
	├── hooks        // 커스텀 훅
	├── stores       // redux 설정, 함수들
	├── utils        // 외부 함수(차트) 등 모듈화 해 재사용 하는 함수
	├── commons
		├── styles      // global styles
		├── route       // route 관한 global
		└── setting     // api port setting
	├── assets/img  
	├── apis
    └── components
		├── atoms
		    ├── Button...
		        ├── styles.js
		        ├── index.stories.js       // Story
		        └── index.js
		├── molecules
			├── 
		├── organisms
			├── 
		├── templates
			├── 
		├── pages
			├── 
```

---

# 🤝 협업


### 🤙 GROUD RULE

- 활발한 커뮤니케이션! 혼자만의 생각 NOPE!
- 깃 컨벤션 지키기
- 모든 회의는 기록으로 남기기
- FE와 BE의 우정을 위해 API 명세 명확히하기

---

### 📏 협업 툴

- [Gitlab](https://lab.ssafy.com/s06-bigdata-rec-sub2/S06P22A306)
- Notion
- [Jira](https://jira.ssafy.com/projects/S06P22A306/summary)
- Mattermost
- Discord

---

### 💫 Git 컨벤션

|  | 형식 | 예시 |
| --- | --- | --- |
|Jira Story|[구현스택: 기능명세서index] 기능명세서 기능명|[FE: A02] 회원 가입 기능 구현|
|Jira SubTask|테스크 여러 개<br/>[구현스택: 기능명세서index] sub 기능 내용<br/>담당자 여러 명 - 태스크가 하나<br/>[구현스택: 기능명세서index] 기능명세서 기능명 - 이름|[BE: A02] 회원 가입 항목 유효성 검사 <br/>[BE: A02] 회원 가입 기능 구현 - 홍길동|
|Branch|구현스택/기능/중분류/요구사항 index|fe/feature/member/A01|
|Commit|#지라이슈번호 커밋헤더: 내용|#S06P22A306-8 Create: Setting 파일 추가|
|MR(PR) Title|[기술스택: 중분류] #지라이슈번호 기능구현제목|[FE: 회원관리] #S06P12A305-12 회원 가입 API 추가|

**Commit Header Type**

- Create - 새로운 소스 파일을 생성하는 경우
- Config - 환경설정 파일을 수정하는 경우
- Feat - 새로운 기능을 추가하고 로직을 작성하는 경우
- Fix - 완성된 기능의 버그를 고쳐야 하는 경우
- !HOTFIX - 완성된 기능의 치명적인 버그를 고쳐야 하는 경우
- Refactor - 완성된 기능의 로직을 수정해야 하는 경우
- Rename - 파일, 패키지, 디렉터리 이름을 수정하거나 위치를 옮기는 작업만 진행한 경우
- Remove - 자바파일(class, interface, enum 등)을 삭제하는 작업만 수행한 경우

---

### 🌊 Git Flow 전략

**사용 브랜치**

- master (오류가 없는 코드, 버전 완료)
- develop (FE, BE 최신 버전 상태로 배포될 코드)
- backend (BE 최신 버전 코드)
- frontend (FE 최신 버전 코드)
- feature (기능 단위 개발 코드)
    - be/feature/중분류/기능코드
    - fe/feature/중분류/기능코드

**진행 방식**

- feature 브랜치 완료 시 backend/frontend 브랜치로 pull request를 통해 merge한다.
- backend/frontend 브랜치 완료 시 develop 브랜치로 pull request를 통해 merger한다.
- develop 브랜치 완료 후 버전 완성 시 master 브랜치로 pull request를 통해 merger한다.

---

### 💬 SCRUM

🕑 오전 미팅 10:10 진행, 금일 할 일, 이슈, 의논 사항 공유

🕑 13:00 팀장 미팅은 텍스트로 전달

🕑 오후 미팅 종례 직후 금일 진행 사항 공유 및 명일 할 일 명시

-----------------------------

## 💪 역할

### 🙋‍♂️ 김경현 (BE)

- 데이터 컬럼 분석
- 데이터 전처리 및 DB 세팅
- 추천 알고리즘 구상
- API 개발
    - 게임 정보
    - 성향(태그, 카테고리) 가중치 계산
    - 성향 분석
- 프로젝트 발표

### 🙋‍♀️ 김은선(BE)

- ERD 작성
- Spring Security
- Spring boot 초기 setting
- API 개발
    - 회원 가입 & 로그인
    - 회원 관리
    - 게임 정보
- 스크럼 회의록 작성 🫂
- UCC 제작

### 🙋‍♂️ 김응주 (FE)

- 로그인/로그아웃 구현
- 회원정보 수정 Page 구현
- 내 게임 목록 Page 구현
- Node 서버 및 Steam ID 연동 구축
- UCC 제작

### 🙋‍♀️ 이지윤 (BE)

- Spring boot 초기 setting
- ERD 작성 및 DB 세팅
- 추천 알고리즘 구상
- Django 초기 세팅
- API 개발
    - spring boot
        - 추천 게임 리스트 조회
        - 게임 별점 입력 및 수정
    - Django
        - 사용자 별 추천 게임 목록 갱신 (협업 필터링 알고리즘 적용)
- README 작성 및 Notion 정리

### 🙋‍♂️ 한정섭 (BE)

- 스팀 게임 추천 컬럼 분석
- CI/CD 구현
- UCC 제작

### 🙋‍♀️ 형다은 (FE)

- UI 디자인
- 게임 취향 분석 Radar Chart 구성
- 게임 성향 분석
- 맞춤 게임 추천
- 게임 정보
- PPT 제작
