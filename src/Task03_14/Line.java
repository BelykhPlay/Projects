package Task03_14;

public class Line {

    private double k;
    private double b;

    public Line(double k, double b) {
        this.k = k;
        this.b = b;
    }

//  Выше линии
    public boolean isPointAboveLine(double x, double y) {
        return y >= this.k*x + this.b;
    }
//  Правее линии. Только для функций вида x = -b
    public boolean isPointRightLine(double x) {
        return x > -this.b;
    }

}
