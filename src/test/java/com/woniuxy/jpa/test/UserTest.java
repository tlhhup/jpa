package com.woniuxy.jpa.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
	
	@Test
	public void query(){
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Query query = entityManager.createQuery("from User");
		
		List<User> users = query.getResultList();
		
		entityManager.close();
		entityManagerFactory.close();
	
		for(User user:users){
			System.out.println(user.getUserName());
		}
	}
	
}
