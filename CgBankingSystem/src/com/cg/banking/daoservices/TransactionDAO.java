package com.cg.banking.daoservices;
import java.util.List;
import com.cg.banking.beans.Transaction;
import com.cg.banking.beans.Account;
public interface TransactionDAO {
 Transaction save(Transaction transaction );
 boolean update(Transaction transaction );
 Transaction findOne(int transactionId );
 List<Transaction>findAll();
}
