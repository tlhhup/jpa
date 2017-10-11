package com.woniuxy.jpa.test;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.woniuxy.jpa.entity.Role;
import com.woniuxy.jpa.entity.User;

public class Many2Many {

	@Test
	public void save() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Role role=new Role();
		role.setName("π‹¿Ì‘±");
		
		Set<User> users=new HashSet<User>();
		
		User user=new User();
		user.setId(1);
		
		users.add(user);

		role.setUsers(users);
		entityManager.persist(role);

		entityManager.getTransaction().commit();

		entityManager.close();
		entityManagerFactory.close();
	}

}
