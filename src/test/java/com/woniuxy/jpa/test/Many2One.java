package com.woniuxy.jpa.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.woniuxy.jpa.entity.Email;
import com.woniuxy.jpa.entity.User;

public class Many2One {


	@Test
	public void save(){
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		User user=new User();
		user.setId(1);
		
		Email email=new Email();
		email.setAddress("fadfasdfasd");
		email.setUser(user);
		
		entityManager.persist(email);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
	}

}
