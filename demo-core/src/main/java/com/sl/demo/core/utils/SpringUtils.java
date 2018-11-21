package com.sl.demo.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public final class SpringUtils implements BeanFactoryPostProcessor {

    //Spring 应用上下文环境
    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringUtils.beanFactory = beanFactory;
    }

    public static <T> T getBean(String name) throws BeansException {
        return (T) beanFactory.getBean(name);
    }
}
