package Task05_14;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        buildingPyramid(readConsole());
    }

    //    Подсчет кол-ва слешев для каждой ступени
    public static int maxNumberSlash(int num) {
        if (num % 2 == 0) {
            num = num / 2;
        } else num = (num + 1) / 2;
        return num;
    }


    //    Вывод пирамиды на экран
    public static void buildingPyramid(int h) {
        for (int i = h; i > 0; i--) {
            writingSpaces(i, h);
            writingSlashesAndSpaces(i);
        }
    }


    //    Вывод на экран пробелов в самом начале начиная с нуля до h - 1 штук
    public static void writingSpaces(int numberSpaces, int h) {
        while (numberSpaces < h) {
            System.out.print(" ");
            numberSpaces++;

        }
    }

    //    Вывод слеш + пробел в кол-ве maxNumberSlash
    public static void writingSlashesAndSpaces(int h) {
        int stop = maxNumberSlash(h);
        while (stop > 0) {
            System.out.print("\\ ");
            stop--;
        }
        System.out.print("\n");
    }


    //    Ввод с консоли
    public static int readConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите высоту пирамиды: ");
        int h = scanner.nextInt();
        return h;
    }

}

