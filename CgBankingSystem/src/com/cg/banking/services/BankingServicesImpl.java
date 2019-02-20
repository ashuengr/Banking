package com.cg.banking.services;

import java.util.List;
import java.util.Scanner;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;
import com.cg.banking.daoservices.AccountDao;
import com.cg.banking.daoservices.AccountDaoImpl;
import com.cg.banking.daoservices.TransactioDaoImpl;
import com.cg.banking.daoservices.TransactionDAO;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.InsufficientAmountException;
import com.cg.banking.exceptions.InvalidAccountTypeException;
import com.cg.banking.exceptions.InvalidAmountException;
import com.cg.banking.exceptions.InvalidPinNumberException;

public class BankingServicesImpl implements BankingServices{
 private AccountDao accountDao= new AccountDaoImpl();
 private TransactionDAO transactionDao = new TransactioDaoImpl();
 Scanner sc=new Scanner(System.in);
@Override
public Account openAccount(String accountType, float initBalance)
		throws InvalidAmountException, InvalidAccountTypeException, BankingServicesDownException {
	 Account account=new Account(accountType,initBalance);
	 int num=(int)(Math.random()*10000);
	 if(num<1000)
		 account.setPinNumber(num*10);
	 else if(num<100)
		 account.setPinNumber(num*100);
	 else if(num<10)
		 account.setPinNumber(num*1000);
	 else
		 account.setPinNumber(num);
	 
	 if(initBalance<2000)
		 throw new InvalidAmountException("invalid initial amount");
	 else if(accountType.isEmpty())
		 throw new InvalidAccountTypeException("Account type is not valid");
	 else
		 account=accountDao.save(account);
	return account;
}

@Override
public float depositAmount(long accountNo, float amount)
		throws AccountNotFoundException, BankingServicesDownException, AccountBlockedException {
	Account account = getAccountDetails(accountNo);

	if(account.getAccountStatus().equalsIgnoreCase("ACTIVE")) {
		float newAmount = account.getAccountBalance() + amount;
		account.setAccountBalance(newAmount);
		Transaction transaction = new Transaction();
		accountDao.update(account);
		transactionDao.save(transaction);
		return newAmount;
	}
	else 
		throw new AccountBlockedException("This account has been blocked");}

@Override
public float withdrawAmount(long accountNo, float amount, int pinNumber) throws InsufficientAmountException,
		AccountNotFoundException, InvalidPinNumberException, BankingServicesDownException, AccountBlockedException {
	Account account = getAccountDetails(accountNo);
	if(account.getAccountStatus().equalsIgnoreCase("ACTIVE")){
		for(int i =0;i<2;i++){
			if(account.getPinNumber()==pinNumber){
				float newAmount = account.getAccountBalance() - amount ; 
				if(newAmount < 500) 
					throw new InsufficientAmountException("Balance cannot go below 500");
				else {
					account.setAccountBalance(newAmount);
					Transaction transaction = new  Transaction();
					accountDao.update(account);
					transactionDao.save(transaction);
				}
				return newAmount;
			}
			else{
				System.out.println("Your PIN is wrong . Kindly enter again");
				pinNumber = sc.nextInt();
			}
		}
		account.setAccountStatus("BLOCKED");
		throw new InvalidPinNumberException("YOU HAVE EXCEEDED PIN ENTERING LIMIT");
	}
	else 
		throw new AccountBlockedException("YOUR ACCOUNT HAS BEEN BLOCKED");
}

@Override
public boolean fundTransfer(long accountNoTo, long accountNoFrom, float transferamount, int pinNumber)
		throws InsufficientAmountException, AccountNotFoundException, InvalidPinNumberException,
		BankingServicesDownException, AccountBlockedException {
	// TODO Auto-generated method stub
	return false;
}

@Override
public Account getAccountDetails(long accountNo) throws AccountNotFoundException, BankingServicesDownException {
	Account account=accountDao.findOne(accountNo);
	if(account==null)
		throw new AccountNotFoundException("Account not found for account"+accountNo);
	return account;
}

@Override
public List<Account> getAllAccountDetails() throws BankingServicesDownException {
	
	return accountDao.findAll();
}

@Override
public List<Transaction> getAccountAllTransaction(long accountNo)
		throws AccountNotFoundException, BankingServicesDownException {
	return transactionDao.findAll();
}

@Override
public String accountStatus(long accountNo)
		throws AccountNotFoundException, BankingServicesDownException, AccountBlockedException {
	// TODO Auto-generated method stub
	return null;
}
}
