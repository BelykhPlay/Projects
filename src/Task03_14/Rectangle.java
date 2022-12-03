package Task03_14;

public class Rectangle {

    public boolean isPointOfRectangle(double x, double y) {
        if ((y > 0 && y < 9) && (x > -5 && x < -2)) {
            return true;
        } else return false;
    }

    public boolean isPointUnderLeftAndUpperBorders(double x, double y) {
        if (y <= 9 && x >= -5) {
            return true;
        } else return false;
    }
}
