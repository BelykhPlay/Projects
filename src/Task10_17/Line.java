package Task10_17;

public class Line {
    private double k;
    private double b;

    public Line(double k, double b) {
        this.k = k;
        this.b = b;
    }

    public double getK() {
        return k;
    }

    public double getB() {
        return b;
    }

    public boolean isCenterBelowOrRightOfLine() {
        return this.b > 0;
    }

}
