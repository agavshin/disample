package ru.vsu.computer.component;

import ru.vsu.computer.Diagnosable;
import ru.vsu.computer.di.annotation.Injectable;
import ru.vsu.computer.di.annotation.Log;

@Injectable
public class Motherboard implements Diagnosable {

    @Override
    @Log
    public void diagnose() {
        System.out.println("Motherboard is working...");
    }
}
