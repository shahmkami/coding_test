package com.smallworld.model;

public class Transaction {
	
	private int mtn;
	private double amount;
	private String senderFullName;
	private int senderAge;
	private String beneficiaryFullName;
	private String beneficiaryAge;
	private Integer issueId;
	private boolean issueSolved;
	private String issueMessage;
	
	public int getMtn() {
		return mtn;
	}
	public void setMtn(int mtn) {
		this.mtn = mtn;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getSenderFullName() {
		return senderFullName;
	}
	public void setSenderFullName(String senderFullName) {
		this.senderFullName = senderFullName;
	}
	public int getSenderAge() {
		return senderAge;
	}
	public void setSenderAge(int senderAge) {
		this.senderAge = senderAge;
	}
	public String getBeneficiaryFullName() {
		return beneficiaryFullName;
	}
	public void setBeneficiaryFullName(String beneficiaryFullName) {
		this.beneficiaryFullName = beneficiaryFullName;
	}
	public String getBeneficiaryAge() {
		return beneficiaryAge;
	}
	public void setBeneficiaryAge(String beneficiaryAge) {
		this.beneficiaryAge = beneficiaryAge;
	}
	public Integer getIssueId() {
		return issueId;
	}
	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}
	public boolean isIssueSolved() {
		return issueSolved;
	}
	public void setIssueSolved(boolean issueSolved) {
		this.issueSolved = issueSolved;
	}
	public String getIssueMessage() {
		return issueMessage;
	}
	public void setIssueMessage(String issueMessage) {
		this.issueMessage = issueMessage;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "\n{ mtn: "+this.getMtn()+", amount: "+this.getAmount()+", issueId:"+this.getIssueId()+", isIssueResolved: "+this.issueSolved+" }";
	}
	
}
