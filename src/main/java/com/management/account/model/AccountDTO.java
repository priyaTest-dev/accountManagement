package com.management.account.model;

public class AccountDTO {

	String accountType = null;
	int accountNumber = 0;

	public AccountDTO(String accountType, Integer accountNumber) {
		super();
		this.accountType = accountType;
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

}
