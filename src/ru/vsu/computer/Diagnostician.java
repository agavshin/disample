package ru.vsu.computer;

import java.util.List;
import java.util.stream.Collectors;
import ru.vsu.computer.di.annotation.Inject;
import ru.vsu.computer.di.annotation.Injectable;

@Injectable
public class Diagnostician {

    @Inject
    private List<Diagnosable> components;

    public String diagnose() {
        return components.stream()
            .map(Diagnosable::diagnose)
            .collect(Collectors.joining("\n"));
    }
}
