/*
Ввести x, n и e.
1) Сумма n слагаемых
2) Сумма слагаемых, больших по величене e
3) Сумма слагаемых, больших по величене e/10
4) Рассчет через math
 */
package Task06_23;

import java.util.Scanner;

public class Main {
    public static double sumFirstTask = 0.0;
    public static double sumSecondTask = 0.0;
    public static double sumThirdTask = 0.0;
    public static double sumFourthTask = 0.0;

    public static void main(String[] args) {
        finalResult();
    }

    //  Нахождение факториала
    public static long factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    //  Нахождение суммы ряда длинной n чисел. Выражена зависимость an от n, a(n-1), x
    public static void sumSeriesNumber(double x, int n, double e) {
        double a;

        for (int i = 0; i <= n; i++) {
            a = (Math.pow(Math.log(2), i) * Math.pow(x, i)) / factorial(i);
            sumFirstTask += a; // 1)

            if (a > e) {
                sumSecondTask += a; // 2)

            }
            if (a > e / 10) {
                sumThirdTask += a;
            } // 3)

        }

        sumFourthTask = Math.pow(2, x); // 4)

    }

    //  Считывание с консоли
    public static double readDouble(Scanner scan, String text) {
        System.out.printf("Введите значение %s:", text);
        return scan.nextDouble();
    }

    public static int readInt(Scanner scan, String text) {
        System.out.printf("Введите значение %s:", text);
        return scan.nextInt();
    }

    //  Финальный результат
    public static void finalResult() {
        Scanner scanner = new Scanner(System.in);

        double x = readDouble(scanner, "x");
        int n = readInt(scanner, "n");
        double e = readDouble(scanner, "e");

//      Подсчет сумм для всех подзаданий и вывод результата
        sumSeriesNumber(x, n, e);

        System.out.printf("1) Сумма n слагаемых: %f; \n", sumFirstTask);
        System.out.printf("2) Сумма слагаемых, больших по величене e: %f; \n", sumSecondTask);
        System.out.printf("3) Сумма слагаемых, больших по величене e/10: %f; \n", sumThirdTask);
        System.out.printf("4) Точное значение(рассчет через Math): %f.", sumFourthTask);

    }
}
