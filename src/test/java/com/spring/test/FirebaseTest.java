package com.spring.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
public class FirebaseTest {
	
	@Autowired
	private FirebaseDao firebaseDao;
	
//	@Test
	public void saveTest() throws Exception{
		//Given
		User user = new User("kim", "viviennes7@naver.com");
		
		//When
		firebaseDao.save(user, "users");
		
	}
	
	@Test 
	public void updateTest() throws Exception{
		Map<String, Object> nameUpdates = new HashMap<String, Object>();
		nameUpdates.put("username", "ms");
		
		firebaseDao.update(nameUpdates,"users/-KheBfOCpdk9jMd13Q7N");
	}
	
	@After
	public void ater() throws Exception{
		Thread.sleep(20000);
	}
}
