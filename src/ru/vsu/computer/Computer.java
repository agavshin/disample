package ru.vsu.computer;

import ru.vsu.computer.di.annotation.Inject;
import ru.vsu.computer.di.annotation.Injectable;

@Injectable
public class Computer {

    @Inject
    private Diagnostician diagnostician;

    public void start() {
        diagnostician.diagnose();

        System.out.println("\n -- All components are working... --");
    }
}
