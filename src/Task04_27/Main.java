/* Найти n-ое по счету (начиная с 0) целое число, цифры которого монотонно возрастают или
    монотонно убывают. Будем считать, что все однозначные и двузначные числа под это требование
        подпадают. Для определения, подходит ли число под требования задачи, реализовать функцию.
            Также реализовать функцию для нахождения n-ого такого числа. Запрещено использовать строки
                и массивы.
 */


package Task04_27;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) { System.out.println( outputNumber() ); }


//    Сравниваем цифры чисел
    public static boolean isMore(int a, int b) { return a > b; }

//    Проверка чисел на монотонность цифр (проверка с конца)
    public static boolean isNumberFits(int number) {
        boolean check = true;
        int digitFirst;
        int digitSecond;
//      Перебор цифр числа
        while (number != 0) {

//          Запись цифр в переменные
            digitFirst = number % 10;
            digitSecond = number % 100 / 10;

//          Цифры числа монотонно возрастают
            if (digitFirst > digitSecond) {
                if (isMore(digitFirst, digitSecond)) {
                    number = number / 10;

                } else {
                    check = false;
                    break;
                }

//          Цифры числа монотонно убывают
            } else {
                if (isMore(digitSecond, digitFirst)) {
                    number = number / 10;

                } else {
                    check = false;
                    break;
                }

            }


        }
//      Возвращаем результат
        return check;
    }


//    Нахождение подходящего под условия числа с заданным порядковым номером
    public static int enumerations(int sequenceNumber) {
//      Место в памяти для искомого числа
        int desiredNumber;
//      Перебор всех чисел с 0 до бесконечности
        for (int num = 0; true; num++) {

//          Если число подходит, то мы приближаемся к искомого числу на один шаг
            if (isNumberFits(num)) {
                sequenceNumber--;

//              Если шагов не осталось, то мы уже подошли к искомому числу
                if (sequenceNumber < 0) {
                    desiredNumber = num;
                    break;
                }

            }


        }
            return desiredNumber;
    }


//    Ввод порядкового номера искомого числа
    public static int readInputInt() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите порядковый номер числа: ");
        return scanner.nextInt();
    }

//    Вывод искомого числа по порядковому номеру
    public static String outputNumber() {
        int sequenceNumber = readInputInt();

        return "Искомое число: " + enumerations(sequenceNumber);
    }
}
