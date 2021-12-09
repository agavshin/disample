package ru.vsu.computer.component;

import ru.vsu.computer.Diagnosable;
import ru.vsu.computer.di.annotation.Injectable;

@Injectable
public class Processor implements Diagnosable {

    @Override
    public String diagnose() {
        return "Processor is working...";
    }
}
