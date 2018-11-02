package com.uxpsystems.assignement.assignement;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.uxpsystems.assignement.assignement.dao.User;
import com.uxpsystems.assignement.assignement.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.DEFAULT)
public class AssignementApplicationTests {

	@Autowired
	ApplicationContext context;
	
	private User user;
	UserService service;
	
	@Before
	public void contextLoads() {
		user = getUserObject();
		service = (UserService) context.getBean("userService");
	}
	
	@Test
	public void testUserCreateAPI(){
		Long response  = service.createUser(user);
		assertEquals(new Long(1),response);
	}
	
	@Test
	public void testUserGetAPI(){
		User response  = service.getUser(1l);
		assertEquals(user.getName(),response.getName());
	}
	
	@Test
	public void testUserUpdateAPI(){
		user.setId(1l);
		Long response  = service.updateUser(user);
		assertEquals(new Long(1),response);
	}
	
	@Test
	public void testUserDeleteAPI(){
		Long response  = service.deleteUser(1l);
		assertEquals(new Long(1),response);
	}
	
	
	private User getUserObject(){
		User user = new User();
		user.setName("sameer");
		user.setPassword(new char[]{1,2,3,4});
		user.setStatus("Activated");
		
		return user;
	}
}
