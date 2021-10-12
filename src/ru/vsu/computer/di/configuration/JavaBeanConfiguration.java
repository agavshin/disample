package ru.vsu.computer.di.configuration;

public class JavaBeanConfiguration implements BeanConfiguration {

    @Override
    public String getPackageToScan() {
        return "ru.vsu.computer";
    }
}
