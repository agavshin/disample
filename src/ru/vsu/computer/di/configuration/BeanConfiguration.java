package ru.vsu.computer.di.configuration;

import java.util.List;
import ru.vsu.computer.di.configuration.processor.PostProcessor;

public interface BeanConfiguration {
    String getPackageToScan();
    List<PostProcessor> processors();
}
