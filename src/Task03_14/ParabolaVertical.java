package Task03_14;

public class ParabolaVertical {

    private double x0;
    private double a;
    private double c;

    public ParabolaVertical(double x0, double a, double c) {
        this.x0 = x0;
        this.a = a;
        this.c = c;
    }

    public boolean isPointOfParabola(double x, double y) {
        return y <= this.a * Math.pow(x - this.x0, 2) + this.c;
    }

}
