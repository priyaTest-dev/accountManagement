package com.management.account.model;

public class StatementDTO {

	private String stmtDt = null;
	private double amount = 0.0;
	private int accountId = 0;

	public StatementDTO(int accountId, String stmtDt, double amount) {
		super();
		this.stmtDt = stmtDt;
		this.amount = amount;
		this.accountId = accountId;
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

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

}
