package Task07_30;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        printSolutionArraysInFile();

        System.out.println(solution(inputArray()));
    }


    public static List<Integer> solution(int[] arr) {
        List<Integer> sequence = new ArrayList<>(); // Текущая последовательность
        List<Integer> storage = new ArrayList<>(); // Хранилише для самой длинной последовательности

        boolean isCheckEnds = false; // Если проверка закончилась
        boolean isCheckStart = true; // Если проверка начинается
        boolean isSequenceSimple = true; // Если на входе массив только с убыванием-возрастанием/убыванием/возрастанием

        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                sequence.add(arr[i]);
                continue;
            }

            //   Убывание
            if (arr[i - 1] >= arr[i] && isCheckStart) {
                sequence.add(arr[i]);

                //   Возрастание
            } else {
                if (isCheckEnds) { // Если текущая проверка закончилась, то проверяем последовательность

                    if (sequence.size() >= storage.size()) {
                        storage.clear();

                        storage.addAll(sequence);
                    }

                    sequence.clear();
                    sequence.add(arr[i--]); // arr[i--], тк одно i потребовалось на перезапись storage

                    // Обнуление флагов
                    isCheckEnds = false;
                    isCheckStart = true;

                    isSequenceSimple = false;


                } else if (arr[i - 1] <= arr[i]) {
                    sequence.add(arr[i]);
                    isCheckStart = false; // Не дает выполняться проверке убывания

                } else {
                    isCheckEnds = true; // Дает возможность проверить на длину имеющуюся последовательность
                    i--; // Так как одно i нужно для смены флага isCheckEnds
                }

            }


        }

        if (isSequenceSimple) {
            storage = sequence;
        }

        return storage;
    }

    public static List<int[]> readArraysFromFile() throws FileNotFoundException {
        File inputFile = new File("InputTask07_30.txt");
        Scanner readFile = new Scanner(inputFile);

        List<int[]> arrays = new ArrayList<>();

        while (readFile.hasNextLine()) {
            System.out.println(1);
//            Создание массивов
            String[] arrInFileString = readFile.nextLine().split(" ");
            int[] arrInFileInt = new int[arrInFileString.length];
//            Массив String в int
            int i = 0;
            for (String elements : arrInFileString) {
                arrInFileInt[i++] = Integer.parseInt(elements);
            }
            arrays.add(arrInFileInt);
        }
        readFile.close();
        return arrays;
    }


    public static void printSolutionArraysInFile() throws FileNotFoundException {
        PrintWriter printer = new PrintWriter("OutputTask07_30.txt");

        List<int[]> arraysInFile = readArraysFromFile();

        for(int[] array: arraysInFile) {
            printer.println(solution(array));

        }

        printer.close();
    }

    public static int readInt(String text) {
        Scanner scan = new Scanner(System.in);
        System.out.print(text);
        return scan.nextInt();
    }

    public static int[] inputArray() {
        int arrLength = readInt("Введите длину массива: ");

        int[] arr = new int[arrLength];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = readInt("  + ");
        }
        return arr;
    }

}