package ru.vsu.computer;

import ru.vsu.computer.di.annotation.Inject;
import ru.vsu.computer.di.annotation.Injectable;

@Injectable
public class Computer {

    @Inject
    private Diagnostician diagnostician;

    public String start() {
        StringBuilder builder = new StringBuilder();
        builder.append(diagnostician.diagnose());

        builder.append("\n -- All components are working... --");

        return builder.toString();
    }
}
