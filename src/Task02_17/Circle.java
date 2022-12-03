package Task02_17;

public class Circle {

    private double x;
    private double y;
    private double r;

    public Circle(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getR() {
        return r;
    }

    public String calcAndCheck(Circle circle) {

        double deltaX = Math.abs(this.x - circle.getX());
        double deltaY = Math.abs(this.y - circle.getY());
        double deltaR = Math.abs(this.r - circle.getR());

        double sumR = this.r + circle.getR();
        double deltaCenters = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        String str = "";

        if (deltaCenters > sumR) {
            str = "Окружности не пересекаются (не вписаны одна в другую)";

        } else if (deltaCenters < deltaR) {
            str = "Окружности не пересекаются (одна окружность вписана в другую)";

        } else if (deltaCenters > deltaR && deltaCenters < sumR) {
            str = "Окружности пересекаются в двух точках";

        } else  if (Math.abs(deltaCenters - sumR) < 1e-4) {
            str = "Окружности касаются с внешней стороны";

        } else str = "Окружности касаются с внутренней стороны";

        return str;
    }

}
