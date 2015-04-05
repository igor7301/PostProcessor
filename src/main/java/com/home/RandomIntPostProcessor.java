package com.home;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * Created by Igor on 03.04.15.
 */
public class RandomIntPostProcessor implements BeanPostProcessor{

    @Override
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            RandomInt annotation = field.getAnnotation(RandomInt.class);
            if (annotation != null) {
                field.setAccessible(true);
                Random random = new Random();
                int min = annotation.min();
                int max = annotation.max();

                int rand = random.nextInt(max - min);
//                ReflectionUtils.setField(field, bean, rand);
                try {
                    field.setInt(bean, rand);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
        return bean;
    }
}
