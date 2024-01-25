# 얼굴인식 출석체크
2023 학사 졸업논문

## 💻프로젝트 소개
미리 학습된 학생들의 얼굴을 안드로이드 앱을 통해 실시간으로 학생의 얼굴과 비교하여 출석을 해주는 프로그램

## 개발 기간
- 23.06.12 ~ 23.08.13

## 👬 팀원
- 노장우
  - 백엔드 : QR코드 생성, 학생 로그인, 교수 로그인, 사용자 이미지 전송, 부정 출석 방지를 위한 검증
  - 안드로이드 : QR코드 인식, 학생 로그인, 얼굴 검출, QR코드 유효성 검사
  
- 이준용
  - 백엔드 : 이미지 처리, 딥러닝 서버 통신
  - 딥러닝 : 이미지 학습, 정규화, 얼굴 검출 및 인증

## 💻개발 환경
- `Java 1.8`
- `Android Gradle 8.0`
- `Python 3.8.16`
- Framework : Springboot(2.xx)
- DB : MariaDB
- App Compile Version : 33, min 24

## 주요 기능
- 학생, 교수 로그인
- 안드로이드 앱을 통한 실시간 얼굴 검출 및 사진 전송
- 딥러닝을 통한 사진 학습 및 비교
- 과목 QR코드 생성
- QR코드 인식 및 검증

---
## 주요 기능 실행 화면 

### 안드로이드
- 로그인
  
![image](https://github.com/YesJW/graduation-thesis/assets/86145775/cab8cd88-1c5f-482d-8366-dc2aaccbbe3d) 


- QR코드 인식 화면

![image](https://github.com/YesJW/graduation-thesis/assets/86145775/205c76f5-92a7-4534-81d5-4bae0aa83a99)


- 얼굴 및 눈 검출

![image](https://github.com/YesJW/graduation-thesis/assets/86145775/ca3c5143-abfa-44aa-9d60-5eb895fea91b)
![image](https://github.com/YesJW/graduation-thesis/assets/86145775/e8605f2f-2071-42cf-91f2-a951ca866951)

- 서버로 사진 전송

![image](https://github.com/YesJW/graduation-thesis/assets/86145775/fb9e5522-16a2-4840-a1f7-977cad62e0ed)


- 출석 완료

![image](https://github.com/YesJW/graduation-thesis/assets/86145775/90cd14d1-40eb-4de5-930b-3db612f1d37c)


### Web

- 로그인

![image](https://github.com/YesJW/graduation-thesis/assets/86145775/e888623a-4507-45d9-896f-ce13164121b3) ![image](https://github.com/YesJW/graduation-thesis/assets/86145775/00abc8fc-e218-46b2-a7c9-a557ec4ef02f) 

- 강의 목록

![image](https://github.com/YesJW/graduation-thesis/assets/86145775/9ecb3a84-e837-431b-93dd-88973106f24e)


- QR코드 생성

![image](https://github.com/YesJW/graduation-thesis/assets/86145775/3da1fa9c-522a-4d14-8eec-7dd0fd845a74) 


## 결과

- 얼굴 검출 및 검증까지 걸린 시간 : 약 2초

![image](https://github.com/YesJW/graduation-thesis/assets/86145775/28189f9a-4daf-489f-ba81-53187e732cb4)

테스트 결과 딥러닝 서버에서 얼굴 검출부터 검증까지 걸린 시간이 2초 정도로 평균 5분 이상 걸리던 출석체크 시간이 매우 크게 단축됨
