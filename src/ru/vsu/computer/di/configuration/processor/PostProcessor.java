package ru.vsu.computer.di.configuration.processor;

import ru.vsu.computer.di.factory.BeanFactory;

public interface PostProcessor {
    <T> T process(T bean, BeanFactory beanFactory);
}
