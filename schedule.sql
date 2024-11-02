use spring_calendar;
--  users 테이블 생성
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    email VARCHAR(50) UNIQUE,
    password VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT user_PK PRIMARY KEY(id)
);

--  todos 테이블 생성
CREATE TABLE IF NOT EXISTS todos (
    id BIGINT AUTO_INCREMENT,
    user_id BIGINT,
    user_name VARCHAR(20) NOT NULL ,
    password VARCHAR(20) NOT NULL,
    title VARCHAR(20) NOT NULL,
    contents VARCHAR(225) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT user_PK PRIMARY KEY(id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
    );

--  users 테이블에 값 추가
INSERT INTO users (name, email, password, created_at, updated_at)
VALUES ("김민범", "email@email.com", "0000", NOW(), NOW());

--  일정 생성을 하는 query
INSERT INTO todos (user_name, password, title, contents, created_at, updated_at, user_id)
VALUES ("김민범", "0000", "SQL", "SQL 작성하기", NOW(), NOW(), 1);

--  전체 일정 조회 query
SELECT * FROM todos;

--  선택 일정 조회 query
SELECT * FROM todos WHERE id=1;

--  선택 일정 수정 query
UPDATE todos SET user_name="김민범22", title="SQL1", contents="SQL 작성하기1", updated_at=NOW() WHERE id=1;

--  선택 일정 삭제 query
# DELETE FROM todos WHERE id=1;