package ru.vsu.computer.component;

import ru.vsu.computer.Diagnosable;
import ru.vsu.computer.di.annotation.Injectable;

@Injectable
public class Motherboard implements Diagnosable {

    @Override
    public void diagnose() {
        System.out.println("Motherboard is working...");
    }
}
