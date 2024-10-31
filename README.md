# spring_calendar

주제 : 스프링을 이용한 캘린더 api

# API
[링크: postman](https://documenter.getpostman.com/view/18429295/2sAY4uD4Gf)
# ERD
![image](https://github.com/user-attachments/assets/b7b044cf-019c-4e63-9a28-37ddb222f53e)
# SQL
```sql
--  users 테이블 생성
CREATE TABLE IF NOT EXISTS users (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(20),
	email VARCHAR(50),
	password VARCHAR(20),
	created_at DATETIME,
	updated_at DATETIME,
	CONSTRAINT user_PK PRIMARY KEY(id)
);

--  todos 테이블 생성
CREATE TABLE IF NOT EXISTS todos (
	id INT NOT NULL AUTO_INCREMENT,
	user_id INT NOT NULL,
	user_name VARCHAR(20),
	password VARCHAR(20),
	title VARCHAR(20),
	contents VARCHAR(225),
	created_at DATETIME,
	updated_at DATETIME,
	CONSTRAINT user_PK PRIMARY KEY(id),
	FOREIGN KEY (user_id) REFERENCES users(id)
);

--  users 테이블에 값 추가
INSERT INTO users (name, email, password, created_at, updated_at)
VALUES ("김민범", "email@email.com", "0000", "2024-10-30T12:22:22", "2024-10-30T12:22:22");

--  일정 생성을 하는 query
INSERT INTO todos (user_name, password, title, contents, created_at, updated_at, user_id)
VALUES ("김민범", "0000", "SQL", "SQL 작성하기", "2024-10-30T12:33:33", "2024-10-30T12:33:33", 1);

--  전체 일정 조회 query
SELECT * FROM todos;

--  선택 일정 조회 query
SELECT * FROM todos WHERE id=1;

--  선택 일정 수정 query
UPDATE todos SET user_name="김민범1", title="SQL1", contents="SQL 작성하기1", updated_at="2024-10-31T11:11:11" WHERE id=1; 

--  선택 일정 삭제 query
DELETE FROM todos WHERE id=1;
```
