package com.woniuxy.jpa.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.junit.Test;

import com.woniuxy.jpa.entity.Email;
import com.woniuxy.jpa.entity.User;

public class One2Many {

	@Test
	public void save() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		User user = new User();
		user.setUserName("lisi1");

		List<Email> emails = new ArrayList<Email>();

		Email email = new Email();
		// email.setId(1);---->1.设置为数据库中存在的邮件

		// 2.级联
		email.setAddress("123123asdfasdf");

		emails.add(email);

		// 维系关系
		user.setEmails(emails);

		entityManager.persist(user);

		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	public void cascadeDelete() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		User user = entityManager.find(User.class, 3);//1.如果不设置级联删除，关联表中至少将关系断掉并不会删除

		entityManager.remove(user);

		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	public void query() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createQuery("from User");

		List<User> users = query.getResultList();

		entityManager.close();
		entityManagerFactory.close();

		for (User user : users) {
			System.out.println(user.getUserName());
		}
	}
	
	@Test
	public void lazy(){
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		User user = entityManager.find(User.class, 1);//对一对多的数据默认开启懒加载,由注解的FetchType决定
		
		System.out.println(user);
		
		entityManager.close();
		entityManagerFactory.close();
	}
	
}
