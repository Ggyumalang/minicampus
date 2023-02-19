# minicampus(백오피스 기능구현) API

## 프로젝트 개요
- 회원 로그인 및 로그아웃 , 수강신청 등의 수강 관련 기본 기능 구현
- 백오피스 관련 회원 관리 , 수강 관리 및 강좌 관리 , 배너 관리 등 구현

## 프로젝트 상세 기능
<b><회원></b>
- 회원가입
- 로그인 및 로그아웃
- 비밀번호 찾기(비밀번호 초기화 기능)
- 자신의 회원 정보 조회, 수정 및 회 탈퇴 
- 비밀번호 변경
- 자신의 수강목록 조회
- 현재 열려있는 강좌 목록 조회
- 강좌 상세 조회 및 수강 신청 기능

<b><관리자></b>
- 회원가입 인증메일 전송
- 비밀번호 초기화 메일 전송
- 관리자(백오피스) 회원 관리
  - 회원 로그인시 로그인 히스토리(로그) 기능
  - 관리자 회원 상세 정보에 로그인 목록 보기 기능
- 관리자(백오피스) 카테고리 관리
  - 카테고리 추가, 수정 및 삭제 기능
- 관리자(백오피스) 강좌 관리
  - 강좌 등록, 수정 및 삭제 기능
  - 강좌 관련 이미지 업로드 기능
- 관리자(백오피스) 수강 관리
  - 전체 강좌 및 강좌별 조회 기능
  - 수강 상태에 따른 수강 취소 및 결제 완료 기
- 관리자(백오피스) 배너관리
  - 강좌 등록, 수정 및 삭제 기능
  - 배너 이미지 업로드 기능
- 프론트 배너 노출 기능
  - 공개여부 및 정렬 순서에 따른 프론트 배너 노출 기능
  - 이미지 클릭 시 강좌 상세 페이지 
- 로그아웃

## ERD

## Version & Dependencies

java.version 1.8

## Spring version

2.5.4

## Dependencies
- Lombok
- JPA
- Spring Security
- Spring Web
- MariaDB
- JavaMailSender
- Thymeleaf
- MyBatis
