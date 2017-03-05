package com.bruce.listener;

import com.bruce.utils.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.UrlResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 自定义监听器，容器加载时查询相关数据
 *
 * @author Bruce_Q
 * @create 2017-03-03 23:48
 **/
public class InitServletContextListener extends ContextLoaderListener implements ServletContextListener {
    protected Logger loggerInitServletContextListener = LoggerFactory.getLogger(getClass());

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("init");
        WebApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) appContext
                .getAutowireCapableBeanFactory();

        loadBeanFromXml(beanFactory, "bruce.xml");
        Object object = SpringContextUtil.getBean("baseCrawler");
        try {
            tryInvoke(object,"startAll");

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public void contextDestroyed(ServletContextEvent sce) {
    }

    private void loadBeanFromXml(DefaultListableBeanFactory beanFactory, String fileName) {
        String path = this.getClass().getResource("").getPath();
        int lastPath = path.lastIndexOf("com/");
        String classPath = path.substring(0, lastPath);
        String configurationFilePath = "file:" + classPath + fileName;
        URL url = null;
        try {
            url = new URL(configurationFilePath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        UrlResource urlResource = new UrlResource(url);
        XmlBeanFactory xbf = new XmlBeanFactory(urlResource);
        String[] beanIds = xbf.getBeanDefinitionNames();
        for (String beanId : beanIds) {
            BeanDefinition bd = xbf.getMergedBeanDefinition(beanId);
            beanFactory.registerBeanDefinition(beanId, bd);
        }
    }

    private void loadDeltaScheduler(DefaultListableBeanFactory beanFactory, String fileName, String schedule) {
        loadBeanFromXml(beanFactory, "delta/" + fileName);
        Object kkSchedule = beanFactory.getBean(schedule);
        try {
            tryInvoke(kkSchedule, "start");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    private void tryInvoke(Object bean, String methodName, Object... args) throws SecurityException,
            NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {

        Class<?> paramTypes[] = new Class[0];
        Method method = bean.getClass()
                .getMethod(methodName, paramTypes);
        method.invoke(bean, args);

    }

}
