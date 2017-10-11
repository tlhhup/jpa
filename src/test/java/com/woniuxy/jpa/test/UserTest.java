package com.woniuxy.jpa.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Test;

import com.woniuxy.jpa.entity.UUIDTest;
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
	public void delete(){
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		User user = entityManager.find(User.class, 5);//先将数据从数据库中查询出来，再删除
		entityManager.remove(user);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
	}
	
	@Test
	public void update(){
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		User user = entityManager.find(User.class, 4);
		user.setPassword("fasdfasdf");//持久态的数据，修改属性，自动触发update
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
	}
	
	@Test
	public void update2(){
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		User user=new User();
		user.setId(4);
		user.setPassword("fasdfasdf");
		
		entityManager.merge(user);//可以实现更新，但会影响关联表中的数据，将关系断掉
		
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
	
	@Test
	public void proxyLoad(){
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		User user = entityManager.getReference(User.class, 1);//该方法返回的是代理对象
		
		User user2 = entityManager.getReference(User.class, 1);
		
		entityManager.close();
		entityManagerFactory.close();

		System.out.println(user==user2);//--->该结果为true
		System.out.println(user.getUserName());
	}
	
	@Test
	public void firstCache(){
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.find(User.class, 1);
		
		System.out.println("********************************");
		
		entityManager.find(User.class, 1);
		
		
		entityManager.close();
		entityManagerFactory.close();
	}
	
	@Test
	public void secondCache(){
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.find(User.class, 1);
		
		entityManager.close();
		System.out.println("********************************");

		entityManager = entityManagerFactory.createEntityManager();
		entityManager.find(User.class, 1);
		
		entityManager.close();
		entityManagerFactory.close();
	}
	
	@Test
	public void uuid(){
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		UUIDTest user=new UUIDTest();
		user.setName("fasdfa");
		
		entityManager.persist(user);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
	}
	
}
