package com.tencoding.bank.dto;

import lombok.Data;

@Data
public class WithdrawFormDto {
	
	private long amount;
	private String wAccountNumber;
	private String wAccountPassword;
}