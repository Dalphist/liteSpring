package org.litespring.test.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinition;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XMLBeanDefinaionReader;
import org.litespring.service.v1.PetStoreService;

public class BeanFactoryTest {
	private DefaultBeanFactory factory = null;
	XMLBeanDefinaionReader reader = null;
	
	@Before
	public void setUp() {
		factory = new DefaultBeanFactory();
		reader = new XMLBeanDefinaionReader(factory);
	}
	
	@Test
	public void getBeanTest() {
		reader.loadBeanDefinations("petstore-v1.xml");
		
		BeanDefinition bd = factory.getBeanDefinition("petstore");
		assertEquals("org.litespring.service.v1.PetStoreService",bd.getBeanClassName());
		
		PetStoreService pss = (PetStoreService)factory.getBean("petstore");
		assertNotNull(pss);
	}
	
	@Test
	public void invalidBeanTest() {
		reader.loadBeanDefinations("petstore-v1.xml");
		try {
			PetStoreService pss = (PetStoreService)factory.getBean("invalidBean");
		} catch (BeanCreationException e) {
			return;
		}
		Assert.fail("invalidBeanTest Faild!");
	}
	
	@Test
	public void invalidXMLTest() {
		try {
			reader.loadBeanDefinations("xxx.xml");
		} catch (BeanDefinitionStoreException e) {
			return;
		}
		Assert.fail("invalidBeanTest Faild!");
	}
}
