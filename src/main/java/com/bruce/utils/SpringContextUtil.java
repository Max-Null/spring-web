/**   
 * @Title: SpringContextUtil.java 
 * @Package com.xiaobai.adms.utils 
 * @Description: spring上下文工具类
 * @author mengqch  
 * @date 2015年9月21日
 * @version V1.0   
 */
package com.bruce.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/** 
 * @ClassName: SpringContextUtil 
 * @Description: spring上下文工具类
 * @author mengqch 
 * @date 2015年9月21日 上午10:02:22  
 */
public class SpringContextUtil implements ApplicationContextAware {
	
	/**
	 * Spring应用上下文环境
	 */
	public static ApplicationContext applicationContext;

	/**
	 * 实现ApplicationContextAware接口的回调方法，设置上下文环境
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}
	
	/**
	 * 
	 * @Title: getApplicationContext 
	 * @Description: 获取applicationContext
	 * @author mengqch  
	 * @date 2015年9月21日
	 * @param @return
	 * @return ApplicationContext
	 * @throws
	 */
	public static ApplicationContext getApplicationContext() {
		 return applicationContext;
	}

	/**
	 * 
	 * @Title: getBean 
	 * @Description: 获取Bean对象 
	 * @author mengqch  
	 * @date 2015年9月21日
	 * @param @param name
	 * @param @return
	 * @param @throws BeansException
	 * @return Object
	 * @throws
	 */
	public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }
 
	/**
	 * 
	 * @Title: getBean 
	 * @Description: 获取类型为requiredType的Bean对象
	 * @author mengqch  
	 * @date 2015年9月21日
	 * @param @param name
	 * @param @param requiredType
	 * @param @return
	 * @param @throws BeansException
	 * @return Object
	 */
    public static Object getBean(String name, Class requiredType)
            throws BeansException {
        return applicationContext.getBean(name, requiredType);
    }
 
    /**
     * 
     * @Title: containsBean 
     * @Description: 判断BeanFactory中是包含一个与所给名称匹配的bean定义
     * @author mengqch  
     * @date 2015年9月21日
     * @param @param name
     * @param @return
     * @return boolean
     * @throws
     */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }
 
    /**
     * 
     * @Title: isSingleton 
     * @Description: 判断以给定名字注册的bean定义是一个singleton还是一个prototype
     * @author mengqch  
     * @date 2015年9月21日
     * @param @param name
     * @param @return
     * @param @throws NoSuchBeanDefinitionException
     * @return boolean
     */
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }
 
    /**
     * 
     * @Title: getType 
     * @Description: 获取注册对象的类型 
     * @author mengqch  
     * @date 2015年9月21日
     * @param @param name
     * @param @return
     * @param @throws NoSuchBeanDefinitionException
     * @return Class
     * @throws
     */
    public static Class getType(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }
 
    /**
     * 
     * @Title: getAliases 
     * @Description: 如果给定的bean名字在bean定义中有别名，则返回这些别名
     * @author mengqch  
     * @date 2015年9月21日
     * @param @param name
     * @param @return
     * @param @throws NoSuchBeanDefinitionException
     * @return String[]
     */
    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getAliases(name);
    }
}
