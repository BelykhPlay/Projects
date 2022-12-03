package Task03_14;

public class ParabolaHorizontal {

    private double y0;
    private double a;
    private double c;

    public ParabolaHorizontal(double y0, double a, double c) {
        this.y0 = y0;
        this.a = a;
        this.c = c;
    }
//  Точка принадлежит параболе
    public boolean isPointOfParabola(double x, double y) {
        if(a > 0) {
            return x >= this.a * Math.pow(y - this.y0, 2) + this.c;
        } else return x <= this.a * Math.pow(y - this.y0, 2) + this.c;
    }

}