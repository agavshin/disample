package ru.vsu.computer.di.configuration.processor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import ru.vsu.computer.di.annotation.Log;
import ru.vsu.computer.di.factory.BeanFactory;

public class LogAnnotationPostProcessor implements PostProcessor {

    @Override
    public <T> T process(T bean, BeanFactory beanFactory) {
        final Map<String, Method> methods = Arrays.stream(bean.getClass().getDeclaredMethods())
            .filter(m -> m.isAnnotationPresent(Log.class))
            .collect(Collectors.toMap(Method::getName, Function.identity()));
        if (!methods.isEmpty()) {
            return (T) Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(),
                (proxy, method, args) -> {
                        System.out.println(String.format(" ------ LOGGED START: %s ------ ", method.getName()));
                        final Object o = methods.get(method.getName()).invoke(bean, args);
                        System.out.println(" ------ LOGGED END ------");
                        return o;
                });
        }
        return bean;
    }
}
