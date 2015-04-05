package com.home;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Igor on 04.04.15.
 */
public class ProfilingPostProcessor implements BeanPostProcessor {
    private Map<String, Object> classWithProfiling = new HashMap<String, Object>();


    @Override
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {

        Profiling annotation  = bean.getClass().getAnnotation(Profiling.class);

        if (annotation != null) {
            classWithProfiling.put(bean.getClass().getName(), bean);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, String s) throws BeansException {

        if (classWithProfiling.get(bean.getClass().getName()) != null) {
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("Profiling....");
                    Object ret = method.invoke(bean, args);
                    System.out.println("Ending....");

                    return ret;
                }
            });
        }

        return bean;
    }
}
