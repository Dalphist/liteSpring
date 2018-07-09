package org.litespring.context.support;

import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XMLBeanDefinaionReader;
import org.litespring.context.ApplicationContext;

public class ClassPathXmlApplicationContext implements ApplicationContext {
	
	DefaultBeanFactory factory = null;

	public ClassPathXmlApplicationContext(String configFile) {
		factory = new DefaultBeanFactory();
		XMLBeanDefinaionReader reader = new XMLBeanDefinaionReader(factory);
		reader.loadBeanDefinations(configFile);
	}

	public Object getBean(String beanId) {
		return factory.getBean(beanId);
	}

}
