# spring_calendar

주제 : 스프링을 이용한 캘린더 api

# API
[링크: postman](https://documenter.getpostman.com/view/18429295/2sAY4xAMVN)
# ERD
![image](https://github.com/user-attachments/assets/b7b044cf-019c-4e63-9a28-37ddb222f53e)

# 기능
### Lv 1. 일정 생성 및 조회  `필수`

- [x]  **일정 생성(일정 작성하기)**
    - [x]  일정 생성 시, 포함되어야할 데이터
        - [x]  `할일`, `작성자명`, `비밀번호`, `작성/수정일`을 저장
        - [x]  `작성/수정일`은 날짜와 시간을 모두 포함한 형태
    - [x]  각 일정의 고유 식별자(ID)를 자동으로 생성하여 관리
    - [x]  최초 입력 시, 수정일은 작성일과 동일
- [x]  **전체 일정 조회(등록된 일정 불러오기)**
    - [x]  다음 조건을 바탕으로 등록된 일정 목록을 전부 조회
        - [x]  `수정일` (형식 : YYYY-MM-DD)
        - [x]  `작성자명`
    - [x]  조건 중 한 가지만을 충족하거나, 둘 다 충족을 하지 않을 수도, 두 가지를 모두 충족할 수도 있습니다.
    - [x]  `수정일` 기준 내림차순으로 정렬하여 조회
- [x]  **선택 일정 조회(선택한 일정 정보 불러오기)**
    - [x]  선택한 일정 단건의 정보를 조회할 수 있습니다.
    - [x]  일정의 고유 식별자(ID)를 사용하여 조회합니다.

### Lv 2. 일정 수정 및 삭제  `필수`

- [x]  **선택한 일정 수정**
    - [x]  선택한 일정 내용 중 `할일`, `작성자명` 만 수정 가능
        - [x]  서버에 일정 수정을 요청할 때 `비밀번호`를 함께 전달합니다.
        - [x]  `작성일` 은 변경할 수 없으며, `수정일` 은 수정 완료 시, 수정한 시점으로 변경합니다.
- [x]  **선택한 일정 삭제**
    - [x]  선택한 일정을 삭제할 수 있습니다.
        - [x]  서버에 일정 수정을 요청할 때 `비밀번호`를 함께 전달합니다.

---

## 3️⃣ 도전 기능 가이드

### Lv 3. 연관 관계 설정  `도전`

- [x]  **작성자와 일정의 연결**
    - [x]  설명
        - [x]  동명이인의 작성자가 있어 어떤 작성자가 등록한 ‘할 일’인지 구별할 수 없음
        - [x]  작성자를 식별하기 위해 이름으로만 관리하던 작성자에게 고유 식별자를 부여합니다.
        - [x]  작성자 테이블을 생성하고 일정 테이블에 FK를 생성해 연관관계를 설정해 봅니다.
    - [x]  조건
        - [x]  작성자는 `이름` 외에 `이메일`, `등록일`, `수정일` 정보를 가지고 있습니다.
            - [x]  작성자의 정보는 추가로 받을 수 있습니다.(조건만 만족한다면 다른 데이터 추가 가능)
        - [x]  고유 식별자를 통해 작성자를 조회할 수 있도록 기존 코드를 변경합니다.
        - [x]  작성자의 고유 식별자가 일정 테이블의 외래키가 될 수 있도록 합니다.

### Lv 4. 페이지네이션  `도전`

- [x]  설명
    - [x]  많은 양의 데이터를 효율적으로 표시하기 위해 데이터를 여러 페이지로 나눕니다.
        - [x]  `페이지 번호`와 `페이지 크기`를 쿼리 파라미터로 전달하여 요청하는 항목을 나타냅니다.
        - [x]  전달받은 페이지 번호와 크기를 기준으로 쿼리를 작성하여 필요한 데이터만을 조회하고 반환
- [x]  조건
    - [x]  등록된 일정 목록을 `페이지 번호`와 `크기`를 기준으로 모두 조회
    - [x]  조회한 일정 목록에는 `작성자 이름`이 포함
    - [x]  범위를 넘어선 페이지를 요청하는 경우 빈 배열을 반환
    - [x]  Paging 객체를 활용할 수 있음

### Lv 5. 예외 처리  `도전`

- [x]  설명
    - [x]  예외 상황에 대한 처리를 위해 [`HTTP 상태 코드(링크)`](https://developer.mozilla.org/ko/docs/Web/HTTP/Status)와 `에러 메시지`를 포함한 정보를 사용하여 예외를 관리할 수 있습니다.
        - [x]  필요에 따라 사용자 정의 예외 클래스를 생성하여 예외 처리를 수행할 수 있습니다.
        - [x]  `@ExceptionHandler`를 활용하여 공통 예외 처리를 구현할 수도 있습니다.
        - [x]  예외가 발생할 경우 적절한 HTTP 상태 코드와 함께 사용자에게 메시지를 전달하여 상황을 관리합니다.
- [x]  조건
    - [x]  수정, 삭제 시 요청할 때 보내는 `비밀번호`가 일치하지 않을 때 예외가 발생합니다.
    - [x]  선택한 일정 정보를 조회할 수 없을 때 예외가 발생합니다.
        - [x]  잘못된 정보로 조회하려고 할 때
        - [x]  이미 삭제된 정보를 조회하려고 할 때

### Lv 6. null 체크 및 특정 패턴에 대한 검증 수행  `도전`

- [x]  설명
    - [x]  유효성 검사
        - [x]  잘못된 입력이나 요청을 미리 방지할 수 있습니다.
        - [x]  데이터의 `무결성을 보장`하고 애플리케이션의 예측 가능성을 높여줍니다.
        - [x]  Spring에서 제공하는 `@Valid` 어노테이션을 이용할 수 있습니다.
- [x]  조건
    - [x]  `할일`은 최대 200자 이내로 제한, 필수값 처리
    - [x]  `비밀번호`는 필수값 처리
    - [x]  작성자의 `이메일` 정보가 형식에 맞는지 확인

# Trouble Shooting
- [페이지네이션 트러블슈팅](https://velog.io/@alsqja2626/Trouble-Shooting-Spring-JDBC-%ED%8E%98%EC%9D%B4%EC%A7%80%EB%84%A4%EC%9D%B4%EC%85%98)
- [리팩토링 트러블슈팅](https://velog.io/@alsqja2626/Trouble-Shooting-%ED%9A%A8%EC%9C%A8%EA%B3%BC-%EA%B0%80%EB%8F%85%EC%84%B1%EC%9D%84-%EC%9C%84%ED%95%9C-%EB%A6%AC%ED%8E%99%ED%86%A0%EB%A7%81)
- [Bean Conflict 트러블슈팅](https://velog.io/@alsqja2626/TroubleShooting-ConflictingBeanDefinitionException-%EB%AC%B8%EC%A0%9C-%ED%95%B4%EA%B2%B0)
