package ru.vsu.computer.di.factory;

import static java.util.stream.Collectors.toList;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import ru.vsu.computer.di.annotation.Inject;
import ru.vsu.computer.di.configuration.BeanConfiguration;
import ru.vsu.computer.di.configuration.JavaBeanConfiguration;
import ru.vsu.computer.di.configuration.processor.PostProcessor;
import ru.vsu.computer.di.configurator.BeanConfigurator.BeanConfigurator;
import ru.vsu.computer.di.configurator.BeanConfigurator.JavaBeanConfigurator;

public class BeanFactory {

    private static BeanFactory INSTANCE;

    private BeanFactory() {
        beanConfiguration = new JavaBeanConfiguration();
        beanConfigurator = new JavaBeanConfigurator(beanConfiguration.getPackageToScan());
    }

    public static BeanFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BeanFactory();
        }
        return INSTANCE;
    }

    private final BeanConfiguration beanConfiguration;
    private final BeanConfigurator beanConfigurator;

    public <T> T getBean(Class<T> tClass) {
        Class<? extends T> implClass = tClass;
        if (implClass.isInterface()) {
            final Set<Class<? extends T>> classImplementations = beanConfigurator.getClassImplementations(tClass);
            if (classImplementations.size() != 1) {
                throw new RuntimeException("Interface has 0 or more than 1 implementation");
            }
            implClass = classImplementations.stream().findFirst().get();
        }
        return initBean(implClass);
    }

    public <T> List<T> getBeans(Class<T> tClass) {
        Class<? extends T> implClass = tClass;
        if (tClass.isInterface()) {
            final Set<Class<? extends T>> classImplementations = beanConfigurator.getClassImplementations(tClass);
            return classImplementations.stream()
                .map(this::initBean)
                .collect(toList());
        }
        return Collections.singletonList(initBean(implClass));
    }

    private <T> T initBean(Class<T> implClass) {
        try {
            T bean = implClass.getDeclaredConstructor().newInstance();

            for (PostProcessor p : beanConfiguration.processors()) {
                bean = p.process(bean, INSTANCE);
            }

            return bean;
        } catch (Exception e) {
            throw new RuntimeException("Exception while bean initialization");
        }
    }
}
