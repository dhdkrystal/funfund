package com.service;

import com.entity.User;
import com.util.Error;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	@Autowired
	UserService service;

	@Test
	public void addManagerTest() throws Exception{
		Error error;
		User manager = new User();

		manager.setUserName("Ani");
		manager.setUserPassword("1234");
		manager.setStartDate("2018-05-23");
		manager.setRole(0);
		manager.setPosition("manager");
		manager.setDescription("Good job");

		error = service.addManager(manager);
		System.out.println(error.getMessage());
	}

	@Test
	public void deleteManagerTest() throws Exception{
		Error error;
		error = service.deleteManager("Ani");
		System.out.println(error.getMessage());
	}

	@Test
	public void updateManagerTest() throws Exception{
		Error error;
		User manager = new User();

		manager.setUserName("king");
		manager.setUserPassword("123w");
		manager.setPosition("fund manager");
		manager.setDescription("Good job");
		manager.setStartDate("2019-01-01");

		error = service.updateManager(manager);
		System.out.println(error.getMessage());
	}

	@Test
	public void getAllManager(){
		List<User> managerList = new ArrayList<User>();
		managerList = service.getAllManager();
		System.out.println("Manager List: "+managerList.get(0).getUserName()+","+managerList.get(1).getUserName()+","+managerList.get(2).getUserName());
	}

}
