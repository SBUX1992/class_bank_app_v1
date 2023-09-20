-- 샘플 데이터 입력
insert into user_tb(username, password, fullname, created_at)
values('길동', '1234', '고', now());

insert into user_tb(username, password, fullname, created_at)
values('둘리', '1234', '애기공룡', now());

insert into user_tb(username, password, fullname, created_at)
values('마이', '1234', '콜', now());

select * from user_tb;


-- 기본 계좌 등록
INSERT INTO account_tb (NUMBER, PASSWORD, balance, user_id, created_at)
VALUES('1111', '1234', 1300, 1, NOW());

INSERT INTO account_tb (NUMBER, PASSWORD, balance, user_id, created_at)
VALUES('2222', '1234', 1100, 2, NOW());

INSERT INTO account_tb (NUMBER, PASSWORD, balance, user_id, created_at)
VALUES('3333', '1234', 0, 3, NOW());

SELECT * FROM account_tb;

-- 이체 내역을 기록(1번 계좌에서 2번 계좌로 100원 이체한다)
INSERT INTO history_tb (amount, w_balance, d_balance, w_account_id, d_account_id, created_at)
VALUES(100, 900, 1100, 1, 2, now());

-- 출금 내역(1번 계좌에서 2번 계좌로 100원을 출금 했다)
INSERT INTO history_tb (amount, w_balance, d_balance, w_account_id, d_account_id, created_at)
VALUES(100, 800, null, 1, null, now());

-- 입금 내역(1번 계좌에서 500원을 입금 했다)
INSERT INTO history_tb (amount, w_balance, d_balance, w_account_id, d_account_id, created_at)
VALUES(500, null, 1300, null, 1, NOW());

# test
INSERT INTO history_tb (amount, w_balance, d_balance, w_account_id, d_account_id, created_at)
VALUES(300, NULL, 1500, NULL, 1, NOW());

select * from user_tb;
select * from account_tb;
select * from history_tb;



