package Task09_14;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Window window = new Window();
        window.setVisible(true);

        ConsoleApplication console = new ConsoleApplication();
        console.start();
    }

}