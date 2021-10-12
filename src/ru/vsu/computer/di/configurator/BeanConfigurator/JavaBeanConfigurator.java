package ru.vsu.computer.di.configurator.BeanConfigurator;

import static java.util.stream.Collectors.toSet;

import java.util.Set;
import org.reflections.Reflections;
import ru.vsu.computer.di.annotation.Injectable;

public class JavaBeanConfigurator implements BeanConfigurator {

    private Reflections scanner;

    public JavaBeanConfigurator(String packageToScan) {
        this.scanner = new Reflections(packageToScan);
    }

    @Override
    public <T> Set<Class<? extends T>> getClassImplementations(Class<T> tClass) {
        final Set<Class<? extends T>> implementations = scanner.getSubTypesOf(tClass).stream()
            .filter(classImpl -> classImpl.isAnnotationPresent(Injectable.class))
            .collect(toSet());
        return implementations;
    }
}
