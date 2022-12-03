package Task03_14;

import java.util.Scanner;

public class Main {

//    Создание графиков
    public static final Line line = new Line(-4.0, -6.0);
    public static final Line topParVerParallelOx = new Line(0.0, 4.0);
    public static final Line topParVerParallelOy = new Line(1.0, -5.0);
    public static final Rectangle rectangle = new Rectangle();
    public static final ParabolaHorizontal parHorOne = new ParabolaHorizontal(5.0, 1.0, -1.0);
    public static final ParabolaHorizontal parHorTwo = new ParabolaHorizontal(-5.0, -(3.0/24.0), -2.0);
    public static final ParabolaVertical parVer = new ParabolaVertical(5.0, -(1.0/4.0), 4.0);

    public static void main(String[] args) {
//        Вывод цвета точек
        printAllColor();
    }


//  Чтение ввода
    public static double readInput(String text) {
        Scanner scan = new Scanner(System.in);
        System.out.printf("Введите координату %s: ", text);
        return scan.nextDouble();
    }

//  Определение цвета
    public static SimpleColor getColor(double x, double y) {

        if(parHorOne.isPointOfParabola(x, y)) {
            return SimpleColor.WHITE;

        } else if(rectangle.isPointOfRectangle(x, y)) {
            return SimpleColor.GRAY;

        } else if(parHorTwo.isPointOfParabola(x, y)) {
            return SimpleColor.GREEN;

        } else if(line.isPointAboveLine(x, y) && !parVer.isPointOfParabola(x, y) &&
                !(!topParVerParallelOx.isPointAboveLine(x, y) && topParVerParallelOy.isPointRightLine(x))) {
            return SimpleColor.GRAY;

        } else if(line.isPointAboveLine(x, y) && parVer.isPointOfParabola(x, y)) {
            return SimpleColor.GREEN;

        } else if(rectangle.isPointUnderLeftAndUpperBorders(x, y) && !line.isPointAboveLine(x, y)) {
            return SimpleColor.GRAY;

        } else if(!rectangle.isPointUnderLeftAndUpperBorders(x, y) && !line.isPointAboveLine(x, y)) {
            return SimpleColor.YELLOW;

        } else return SimpleColor.GREEN;
    }

//  Вывод цвета задаваемой точки
    public static void printAllColor() {
//      Вывод цвета заданных точек
        printColorSetPoints();
        double x = readInput("x");
        double y = readInput("y");
        System.out.println("(" + x + ", " + y + ") -> " + getColor(x, y));
    }

//  Вывод цвета заданных точки
    public static void printColorSetPoints() {
        System.out.println("(" +  0 + ", " + 0 + ") -> " + getColor(0, 0));
        System.out.println("(" + 5 + ", " + 5 + ") -> " + getColor(5, 5));
        System.out.println("(" + 0 + ", " + 10 + ") -> " + getColor(0, 10));
        System.out.println("(" + 5 + ", " + -5 + ") -> " + getColor(5, -5));
        System.out.println("(" + -1000 + ", " + 1 + ") -> " + getColor(-1000, 1));
        System.out.println("(" + -10 + ", " + 10 + ") -> " + getColor(-10, 10));
    }
}