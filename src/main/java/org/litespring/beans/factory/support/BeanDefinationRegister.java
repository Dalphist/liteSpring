package org.litespring.beans.factory.support;

import org.litespring.beans.factory.BeanDefinition;

public interface BeanDefinationRegister {
	BeanDefinition getBeanDefinition(String beanId);
	void registerBeanDefination(String beanId,BeanDefinition bd);
}
