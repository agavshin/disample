package ru.vsu.computer.di.configurator.BeanConfigurator;

import java.util.Set;

public interface BeanConfigurator {

    <T> Set<Class<? extends T>> getClassImplementations(Class<T> tClass);
}
