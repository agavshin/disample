package ru.vsu.computer.di.configuration;

import java.util.List;
import ru.vsu.computer.di.configuration.processor.InjectAnnotationPostProcessor;
import ru.vsu.computer.di.configuration.processor.LogAnnotationPostProcessor;
import ru.vsu.computer.di.configuration.processor.PostProcessor;

public class JavaBeanConfiguration implements BeanConfiguration {

    @Override
    public String getPackageToScan() {
        return "ru.vsu.computer";
    }

    @Override
    public List<PostProcessor> processors() {
        return List.of(new InjectAnnotationPostProcessor(), new LogAnnotationPostProcessor());
    }
}
