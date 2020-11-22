package com.codewindy.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

/**
 *  操作spring bean的工具类，主要用于想通过代码来获取bean，bean必须是spring 容器管理的bean
 * @desc 在多线程Runnable资源抢占无法注入bean的场景中使用
 * @author jkwindy@126.com
 * @date 2019-03-27 21:08
 */
public class SpringBeanUtils implements ApplicationContextAware {


    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
      SpringBeanUtils.applicationContext = ctx;
    }

    /**
     * 通过bean name获取bean
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过bean name还有bean 构造函数参数获取bean
     *
     * @param name
     * @return
     */
    public static Object getBean(String name, Object... args) {
        return getApplicationContext().getBean(name, args);
    }

    /**
     * 通过指定具体的class来获取bean实例
     *
     * @param classType
     * @return
     */
    public static <T> T getBean(Class<T> classType) {
        return getApplicationContext().getBean(classType);
    }


    /**
     * 通过获取具体的class还有bean的构造函数参数来获取bean
     *
     * @param classType
     * @param args
     * @return
     */
    public static <T> T getBean(Class<T> classType, Object... args) {
        return getApplicationContext().getBean(classType, args);
    }

    /**
     * 获取application
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {

        if (applicationContext == null) {
            applicationContext = getWebApplicationContext();
        }

        return applicationContext;
    }

    /**
     * 获取webapplication
     *
     * @return
     */
    public static WebApplicationContext getWebApplicationContext() {
        return ContextLoader.getCurrentWebApplicationContext();
    }

    /**
     * 获取servletcontext
     *
     * @return
     */
    public static ServletContext getServletContext() {
        return getWebApplicationContext().getServletContext();
    }

}
