package com.cg.banking.daoservices;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.cg.banking.beans.Transaction;

public class TransactioDaoImpl implements TransactionDAO{
	private EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("JPA-PU");
	@Override
	public Transaction save(Transaction transaction) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(transaction);
		entityManager.getTransaction().commit();
		entityManager.close();
        return transaction;
	}

	@Override
	public boolean update(Transaction transaction) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(transaction);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;

	}

	@Override
	public Transaction findOne(int transactionId) {
		/*EntityManager entityManager=entityManagerFactory.createEntityManager();
		return entityManager.find(Associate.class, associateId);*/
		return entityManagerFactory.createEntityManager().find(Transaction.class, transactionId);
	}

	@Override
	public List<Transaction> findAll() {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Query query=entityManager.createNamedQuery("from Transaction a",Transaction.class);
		return query.getResultList();

	}

}
