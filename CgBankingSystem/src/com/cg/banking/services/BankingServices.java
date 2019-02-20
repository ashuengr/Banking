package com.cg.banking.services;
import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;
import com.cg.banking.exceptions.*;
import java.util.List;
public interface BankingServices {
 Account openAccount( String accountType,float initBalance)throws InvalidAmountException,InvalidAccountTypeException,BankingServicesDownException;
 
 float depositAmount(long accountNo,float amount) throws AccountNotFoundException ,BankingServicesDownException,AccountBlockedException;
 
 float withdrawAmount(long accountNo,float amount,int pinNumber)throws InsufficientAmountException,AccountNotFoundException,InvalidPinNumberException,BankingServicesDownException,AccountBlockedException;
 
 boolean fundTransfer(long accountNoTo,long accountNoFrom,float transferamount,int pinNumber)throws InsufficientAmountException,AccountNotFoundException,InvalidPinNumberException,BankingServicesDownException,AccountBlockedException;
 
 Account getAccountDetails(long accountNo)throws AccountNotFoundException,BankingServicesDownException;
 List<Account>getAllAccountDetails()throws BankingServicesDownException;
 List<Transaction>getAccountAllTransaction(long accountNo)throws AccountNotFoundException,BankingServicesDownException;
 public String accountStatus(long accountNo)throws AccountNotFoundException,BankingServicesDownException,AccountBlockedException;
 
}
