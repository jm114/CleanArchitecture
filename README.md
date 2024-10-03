# TDD-CleanArchitecture

## TODO
- Default
    - [x] 아키텍처 준수를 위한 애플리케이션 패키지 설계
    - [ ] 특강 도메인 테이블 설계 및 목록/신청 등 기본 기능 구현
    - [ ] 각 기능에 대한 단위 테스트 작성
- Step3
    - [X] 설계한 테이블에 대한 **ERD** 및 이유를 설명하는 **README** 작성
    - [ ] 선착순 30명 이후의 신청자의 경우 실패하도록 개선
    - [ ] 동시에 동일한 특강에 대해 40명이 신청했을 때, 30명만 성공하는 것을 검증하는 **통합 테스트** 작성
- Step4
    - [ ] 같은 사용자가 동일한 특강에 대해 신청 성공하지 못하도록 개선
    - [ ] 동일한 유저 정보로 같은 특강을 5번 신청했을 때, 1번만 성공하는 것을 검증하는 **통합 테스트** 작성

## 테이블 설계(ERD)
![img.png](img.png)  

COURSE (강좌) : 강좌의 기본 정보를 관리합니다.  
 
LECTURE (특강) : 특강은 여러 날에 걸쳐 진행될 수 있으며, 수업일과 제한 인원으로 구성됩니다

USER (사용자) : 특강을 수강하는 수강생 정보를 관리합니다.

ENROLLMENT (수강신청) : 수강생이 특강을 신청한 내역을 관리합니다.

---
## API DOCS
### 1. 특강 신청 API

- **URL**: `/lectures/apply`
- **Method**: `POST`
- **Description**: user별로 특강을 신청한다.

#### Request
- **lectureId**: 강의id
- **userId**: 사용자id
- **applyTime** : 수강신청시간

#### Response
- **Status**: `201 Created`

==========================================
### 2. 특강 신청 여부 조회  API
- **URL**: `/lectures/applyCheck`
- **Method**: `GET`
- **Description**: 요청한 특강이 신청 가능한지 확인한다.

#### Request
- **lectureId**: 강의id
- **userId**: 사용자id

#### Response
- **Status**: `200 OK`
- 
==========================================
### 3. 신청 가능한 특강 목록 API
- **URL**: `/lectures/lists`
- **Method**: `GET`
- **Description**: 신청 가능한 강의를 조회한다.

#### Request
- **userId**: 사용자id

#### Response
- **Status**: `200 OK`

==========================================
### 4. 신청 완료 특강 목록 API

- **URL**: `/lectures/myLists`
- **Method**: `GET`
- **Description**: 신청 완료한 강의를 조회한다.

#### Request
- **userId**: 사용자id

#### Response
- **Status**: `200 OK`

---
<details>
    <summary class="large-text"> API Specs</summary>

    [요구사항]
    - 아래 2가지 API 를 구현합니다.
        - 특강 신청 API
        - 특강 신청 여부 조회 API
    - 각 기능 및 제약 사항에 대해 단위 테스트를 반드시 하나 이상 작성하도록 합니다.
    - 다수의 인스턴스로 어플리케이션이 동작하더라도 기능에 문제가 없도록 작성하도록 합니다.
    - 동시성 이슈를 고려 하여 구현합니다.

    1. (핵심) 특강 신청 API  
    v 특정 userId 로 선착순으로 제공되는 특강을 신청하는 API 를 작성합니다.
    - 동일한 신청자는 동일한 강의에 대해서 한 번의 수강 신청만 성공할 수 있습니다.
    - 특강은 선착순 30명만 신청 가능합니다.
    - 이미 신청자가 30명이 초과되면 이후 신청자는 요청을 실패합니다.

    2. 특강 선택 API(+신청 여부 조회 API)
      - 날짜별로 현재 신청 가능한 특강 목록을 조회하는 API 를 작성합니다.
      - 특강의 정원은 30명으로 고정이며, 사용자는 각 특강에 신청하기전 목록을 조회해볼 수 있어야 합니다.
   
    3. 특강 신청 완료 목록 조회 API
      - 특정 userId 로 신청 완료된 특강 목록을 조회하는 API 를 작성합니다.
      - 각 항목은 특강 ID 및 이름, 강연자 정보를 담고 있어야 합니다.
</details>




<style>
  .large-text {
    font-size: 22px; /* 텍스트 크기를 24px로 설정 */
  }
</style>