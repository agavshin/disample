package ru.vsu.computer;

import ru.vsu.computer.di.factory.BeanFactory;

public class Main {

    public static void main(String[] args) {
        System.out.println(" -- Started -- \n");

        Computer computer = BeanFactory.getInstance().getBean(Computer.class);
        computer.start();

//        new ItemRepository().list().forEach(System.out::println);
    }
}
