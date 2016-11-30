package quickstart;

import java.io.PrintStream;

public class SlayDragonQuest implements Quest {

    private PrintStream printer;

    public SlayDragonQuest(PrintStream printer) {
        this.printer = printer;
    }

    @Override
    public void embark() {
        printer.println("Embarking on quest to slay the dragon!");
    }
}
