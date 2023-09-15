package com.tencoding.bank.dto;

import lombok.Data;

@Data
public class TransferFormDto {

	private long amount;
	private String wAccountNumber;
	private String wAccountPassword;
	private String dAccountNumber;
}
