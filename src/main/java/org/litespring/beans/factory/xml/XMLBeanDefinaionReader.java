package org.litespring.beans.factory.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.factory.BeanDefinition;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.BeanDefinationRegister;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.util.ClassUtils;

public class XMLBeanDefinaionReader {
	private static final String ID_ATTRIBUTE = "id";
	private static final String CLASS_ATTRIBUTE = "class";
	private BeanDefinationRegister register;
	
	public XMLBeanDefinaionReader(DefaultBeanFactory factory) {
		this.register = factory;
	}

	public void loadBeanDefinations(String configFile) {
		InputStream is = null;
		try {
			ClassLoader cl = ClassUtils.getDefaultClassLoader();
			is = cl.getResourceAsStream(configFile);
			SAXReader reader = new SAXReader();
			Document doc = reader.read(is);
			
			Element root = doc.getRootElement();	//beans
			Iterator<Element> iter = root.elementIterator();
			while(iter.hasNext()) {
				Element element = iter.next();	//bean
				String id = element.attributeValue(ID_ATTRIBUTE);
				String beanClassName = element.attributeValue(CLASS_ATTRIBUTE);
				BeanDefinition bd = new GenericBeanDefinition(id,beanClassName);
				register.registerBeanDefination(id, bd);
			}
		} catch (DocumentException e) {
			throw new BeanDefinitionStoreException("XML does not exist", e);
		} finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
