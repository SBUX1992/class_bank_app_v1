-- 유저 테이블 설계 하기
create table user_tb(
	id int auto_increment primary key,
    username varchar(50) not null unique,
    password varchar(30) not null,
    fullname varchar(50) not null,
    created_at timestamp not null default now()
);

-- 계좌 정보 테이블
create table account_tb(
	id int auto_increment primary key,
    number varchar(30) not null unique,
    password varchar(20) not null,
    balance bigint not null comment '계좌잔액',
    user_id int,
    created_at timestamp not null default now()
);

-- 히스토리 정보 테이블
-- 입금 내역
-- 출금 내역
-- 사용자간의 이체 내역 저장
CREATE TABLE history_tb(
	id INT AUTO_INCREMENT PRIMARY KEY COMMENT '거래 내역 id',
	amount BIGINT not NULL COMMENT '거래 금액',
	w_account_id INT COMMENT '출금 계좌 ID',
	d_account_id INT COMMENT '입금 계좌 ID',
	w_balance BIGINT COMMENT '출금 요청된 계좌의 잔액',
	d_balance BIGINT COMMENT '입금 요청된 계좌의 잔액',
	created_at TIMESTAMP NOT NULL DEFAULT NOW()
);