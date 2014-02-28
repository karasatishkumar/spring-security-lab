package org.techiekernel.model;

public class Account {
	Long accountId;
	String accountNo;
	String accountName;
	String currency;
	String country;

	public Account(Long accountId, String accountNo, String accountName,
			String currency, String country) {
		this.accountId = accountId;
		this.accountNo = accountNo;
		this.accountName = accountName;
		this.currency = currency;
		this.country = country;
	}

	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountNo=" + accountNo
				+ ", accountName=" + accountName + ", currency=" + currency
				+ ", country=" + country + "]";
	}

}