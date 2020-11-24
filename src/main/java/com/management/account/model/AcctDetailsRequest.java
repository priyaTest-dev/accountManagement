package com.management.account.model;

public class AcctDetailsRequest {

	private int accountId = 0;
	private String startDt = null;
	private String endDt = null;
	private double minAmount = 0.0;
	private double maxAmount = 0.0;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getStartDt() {
		return startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public double getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(double minAmount) {
		this.minAmount = minAmount;
	}

	public double getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(double maxAmount) {
		this.maxAmount = maxAmount;
	}

	@Override
	public String toString() {
		return "AcctDetailsRequest [accountId=" + accountId + ", startDt=" + startDt + ", endDt=" + endDt
				+ ", minAmount=" + minAmount + ", maxAmount=" + maxAmount + "]";
	}

}
