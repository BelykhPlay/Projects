package Task08_25;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // GUI
        SimpleGUI gui = new SimpleGUI();
        gui.setVisible(true);

        // Console
        Console console = new Console();
        console.start();
    }

}