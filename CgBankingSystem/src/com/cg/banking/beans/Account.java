package com.cg.banking.beans;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Entity
public class Account
{
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
 private long accountNo;
 private float initBalance;
 private int pinNumber;
 private String accountType,accountStatus;
 private float accountBalance;
 @OneToMany(mappedBy="account")
 @MapKey
private Map<Integer,Transaction>transactions;

public long getAccountNo() {
	return accountNo;
}

public void setAccountNo(long accountNo) {
	this.accountNo = accountNo;
}

public float getInitBalance() {
	return initBalance;
}

public void setInitBalance(float initBalance) {
	this.initBalance = initBalance;
}

public int getPinNumber() {
	return pinNumber;
}

public void setPinNumber(int pinNumber) {
	this.pinNumber = pinNumber;
}

public String getAccountType() {
	return accountType;
}

public void setAccountType(String accountType) {
	this.accountType = accountType;
}

public String getAccountStatus() {
	return accountStatus;
}

public void setAccountStatus(String accountStatus) {
	this.accountStatus = accountStatus;
}

public float getAccountBalance() {
	return accountBalance;
}

public void setAccountBalance(float accountBalance) {
	this.accountBalance = accountBalance;
}

public Map<Integer, Transaction> getTransactions() {
	return transactions;
}

public void setTransactions(Map<Integer, Transaction> transactions) {
	this.transactions = transactions;
}

public Account(long accountNo, float initBalance, int pinNumber, String accountType, String accountStatus,
		float accountBalance, Map<Integer, Transaction> transactions) {
	super();
	this.accountNo = accountNo;
	this.initBalance = initBalance;
	this.pinNumber = pinNumber;
	this.accountType = accountType;
	this.accountStatus = accountStatus;
	this.accountBalance = accountBalance;
	this.transactions = transactions;
}

public Account(long accountNo, String accountType) {
	super();
	this.accountNo = accountNo;
	this.accountType = accountType;
}
public Account(String accountType,float initBalance) {
	this.accountType = accountType;
	this.initBalance=initBalance;
}
public Account() {
	super();
}

@Override
public String toString() {
	return "Account [accountNo=" + accountNo + ", initBalance=" + initBalance + ", pinNumber=" + pinNumber
			+ ", accountType=" + accountType + ", accountStatus=" + accountStatus + ", accountBalance=" + accountBalance
			+ ", transactions=" + transactions + "]";
}
}
