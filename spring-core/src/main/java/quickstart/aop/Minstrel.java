package quickstart.aop;

import java.io.PrintStream;

/**
 * 吟游诗人，一个 POJO
 *
 * DI 能够让相互协作的软件组件保持松散耦合，而面向切面编程（AOP）允许把遍布应用各处的功能分离出来形成可重用的组件。
 */
public class Minstrel {

    private PrintStream printer;

    public Minstrel(PrintStream printer) {
        this.printer = printer;
    }

    public void singBeforeQuest(){
        printer.println("Quest starts...");
    }

    public void singAfterQuest(){
        printer.println("Quest finished...");
    }
}
