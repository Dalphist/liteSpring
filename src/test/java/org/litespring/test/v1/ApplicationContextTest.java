package org.litespring.test.v1;

import static org.junit.Assert.*;

import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v1.PetStoreService;


public class ApplicationContextTest {

	@Test
	public void testGetBean() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("petstore-v1.xml");
		PetStoreService pss = (PetStoreService)ac.getBean("petstore");
		assertNotNull(pss);
	}
	
}
