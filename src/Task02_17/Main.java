package Task02_17;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double x1 = readInput(scan, "x1");
        double y1 = readInput(scan, "y1");
        double r1 = readInput(scan, "r1");

        double x2 = readInput(scan, "x2");
        double y2 = readInput(scan, "y2");
        double r2 = readInput(scan, "r2");

        Circle circle1 = new Circle(x1, y1, r1);
        Circle circle2 = new Circle(x2, y2, r2);

        System.out.println(circle1.calcAndCheck(circle2));
    }

    public static double readInput(Scanner scanner, String text){
        System.out.printf("Введите значение %s:", text);
        return scanner.nextDouble();
    }

}
