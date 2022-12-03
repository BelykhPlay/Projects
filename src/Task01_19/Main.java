package Task01_19;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        double rMin = readDouble(scan, "r");
        double rMax = readDouble(scan, "R");

        double res = calculations(rMin, rMax);
        System.out.printf("Искомая площадь: %1$.3f", res);

    }

    public static double readDouble(Scanner scanner, String text){

        System.out.printf("Введите значение %s: ", text);
        return scanner.nextDouble();

    }

    public static double calculations(double small, double big){

        double sr8Min = (Math.PI * small * small) / 8;
        double sr8Max = (Math.PI * big * big) / 8;
        double doublePiece = big*big - 2*sr8Max;

        return 2*doublePiece + 3*sr8Min;
    }

}