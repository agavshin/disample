package ru.vsu.computer;

import java.util.List;
import ru.vsu.computer.di.annotation.Inject;
import ru.vsu.computer.di.annotation.Injectable;

@Injectable
public class Diagnostician {

    @Inject
    private List<Diagnosable> components;

    public void diagnose() {
        components.forEach(Diagnosable::diagnose);
    }
}
