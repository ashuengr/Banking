package com.cg.banking.daoservices;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.cg.banking.beans.Account;
public class AccountDaoImpl implements AccountDao{
	private EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("JPA-PU");
	@Override
	public Account save(Account account) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(account);
		entityManager.getTransaction().commit();
		entityManager.close();
        return account;	}

	@Override
	public boolean update(Account account) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(account);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;

	}

	@Override
	public Account findOne(long accountNo) {
		/*EntityManager entityManager=entityManagerFactory.createEntityManager();
		return entityManager.find(Account.class, accountNo);*/
		return entityManagerFactory.createEntityManager().find(Account.class, accountNo);
	}

	@Override
	public List<Account> findAll() {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Query query=entityManager.createNamedQuery("from Account a",Account.class);
		return query.getResultList();
	}

}
