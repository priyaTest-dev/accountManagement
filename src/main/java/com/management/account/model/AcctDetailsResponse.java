package com.management.account.model;

public class AcctDetailsResponse {

	private String accountType = null;
	private String accountNumber;
	private String stmtDt = null;
	private double amount = 0.0;

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	

	public String getStmtDt() {
		return stmtDt;
	}

	public void setStmtDt(String stmtDt) {
		this.stmtDt = stmtDt;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
