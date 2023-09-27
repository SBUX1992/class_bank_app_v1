SELECT * FROM user_tb;

UPDATE `user_tb` SET username= "",
PASSWORD = "", fullname = ""
WHERE id = 100;



DELETE FROM `user_tb`
		WHERE `username` = "";
		
		
select `id` from `user_tb` where `username`= "";


SELECT * FROM account_tb;

INSERT INTO account_tb(NUMBER, PASSWORD, balance, user_id, created_at)
VALUES("", "", , , NOW());

UPDATE `account_tb` SET NUMBER = "",
PASSWORD = "", balance = "", user_id = ""
WHERE id = "";

DELETE FROM `account_tb` WHERE id = "";

SELECT * FROM `account_tb`;

SELECT * FROM `account_tb` WHERE id = "1";

SELECT * FROM `account_tb` WHERE NUMBER = "1111";

SELECT * FROM `history_tb`;

INSERT INTO history_tb(amount, w_account_id, d_account_id, w_balance, d_balance, created_at)
VALUES();

UPDATE `history_tb` SET amount = "",
w_account_id = "", d_account_id = "", w_balance = "", d_balance = ""
WHERE id = "";

DELETE FROM `history_tb` WHERE id = "";

SELECT * FROM `history_tb` WHERE id = "1";

SELECT * from account_tb;



SELECT * FROM user_tb WHERE username = '길동' AND PASSWORD = '1234';


select * from account_tb where user_id = 3;


