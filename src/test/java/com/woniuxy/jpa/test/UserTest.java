package com.woniuxy.jpa.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.woniuxy.jpa.entity.User;

public class UserTest {

	@Test
	public void save(){
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		User user=new User();
		user.setUserName("admin");
		user.setPassword("admin");
		
		entityManager.persist(user);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
	}
	
}
