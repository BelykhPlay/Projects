package Task10_17;

import java.util.Arrays;

public class Triangle {
    private double[] topA; // The highest; [xA, yA]
    private double[] topB; // [xB, yB]
    private double[] topC; // The lowest; [xC, yC]
    
    private Line firstLine;
    private Line secondLine;
    private Line thirdLine;

    public Triangle(double[][] coordinates) {
        this.topA = coordinates[0];
        this.topB = coordinates[1];
        this.topC = coordinates[2];


        firstLine = createFirstLine();
        secondLine = createSecondLine();
        thirdLine = createThirdLine();

    }

    public boolean isItInAllQuarters() {
        String typeOfTriangle = determiningTypeOfTriangle();

        return switch (typeOfTriangle) {
            case("AB.AC") -> isFirstTypePerpendicularTriangleSuitable();
            case("AB.BC") -> isSecondTypePerpendicularTriangleSuitable();
            case("AC.BC") -> isThirdTypePerpendicularTriangleSuitable();

            default -> isDefaultTypeTriangleSuitable();

        };

    }

    
    // AB
    private Line createFirstLine() {
        if (this.topB[0] > this.topA[0]) {
            double k = (this.topA[1] - this.topB[1]) / (this.topA[0] - this.topB[0]);
            double b = this.topB[1] - k * this.topB[0];

            return new Line(k, b);

        } else {
            double k = (this.topB[1] - this.topA[1]) / (this.topB[0] - this.topA[0]);
            double b = this.topA[1] - k * this.topA[0];

            return new Line(k, b);

        }
    }

    // BC
    private Line createSecondLine() {
        if (this.topC[0] > this.topB[0]) {
            double k = (this.topB[1] - this.topC[1]) / (this.topB[0] - this.topC[0]);
            double b = this.topC[1] - k * this.topC[0];

            return new Line(k, b);

        } else {

            double k = (this.topC[1] - this.topB[1]) / (this.topC[0] - this.topB[0]);
            double b = this.topB[1] - k * this.topB[0];

            return new Line(k, b);

        }
    }

    // AC
    private Line createThirdLine() {
        if (this.topA[0] > this.topC[0]) {
            double k = (this.topC[1] - this.topA[1]) / (this.topC[0] - this.topA[0]);
            double b = this.topA[1] - k * this.topA[0];

            return new Line(k, b);

        } else {

            double k = (this.topA[1] - this.topC[1]) / (this.topA[0] - this.topC[0]);
            double b = this.topC[1] - k * this.topC[0];

            return new Line(k, b);

        }
    }


    private String determiningTypeOfTriangle() {
        // AB & AC 1 вариант
        if (firstLine.getK() == 0 && thirdLine.getK() == 1) {
            return String.valueOf(Type.AB_AC);

        }
        // AB & BC 2 и 3 варианты
        else if ((firstLine.getK() == 1 && secondLine.getK() == 0) || (firstLine.getK() == 0 && secondLine.getK() == 1)) {
            return String.valueOf(Type.AB_BC);

        }
        // AC & BC
        else if (thirdLine.getK() == 1 && secondLine.getK() == 0) {
            return String.valueOf(Type.AC_BC);
        // Default
        } else {
            return String.valueOf(Type.DEFAULT);

        }

    }


    private boolean isDefaultTypeTriangleSuitable() {
        boolean isConditionForFirstLineMet = firstLine.isCenterBelowOrRightOfLine();
        boolean isConditionForSecondLineMet = secondLine.isCenterBelowOrRightOfLine();
        boolean isConditionForThirdLineMet = thirdLine.isCenterBelowOrRightOfLine();

        if ((this.topA[0] <= this.topB[0] && this.topC[0] <= this.topA[0]) || (this.topA[0] >= this.topB[0] && this.topC[0] >= this.topA[0])) {
            System.out.println("d1");
            return isConditionForFirstLineMet && !isConditionForSecondLineMet && isConditionForThirdLineMet;

        } else if ((this.topA[0] <= this.topC[0] && this.topC[0] <= this.topB[0]) || (this.topA[0] >= this.topC[0] && this.topC[0] >= this.topB[0])) {
            System.out.println("d2");
            return isConditionForFirstLineMet && !isConditionForSecondLineMet && !isConditionForThirdLineMet;

        } else {
            System.out.println("d3");
            return isConditionForFirstLineMet && isConditionForSecondLineMet && !isConditionForThirdLineMet;
        }

    }

    // AB & AC
    private boolean isFirstTypePerpendicularTriangleSuitable() {
        return firstLine.isCenterBelowOrRightOfLine() && !secondLine.isCenterBelowOrRightOfLine() && thirdLine.isCenterBelowOrRightOfLine();
    }

    // AB & BC
    private boolean isSecondTypePerpendicularTriangleSuitable() {
        if(firstLine.getK() == 1 && secondLine.getK() == 0) {
            return firstLine.isCenterBelowOrRightOfLine() && !secondLine.isCenterBelowOrRightOfLine() && thirdLine.isCenterBelowOrRightOfLine();

        } else {
            return firstLine.isCenterBelowOrRightOfLine() && !secondLine.isCenterBelowOrRightOfLine() && !thirdLine.isCenterBelowOrRightOfLine();

        }

    }

    // AC & BC
    private boolean isThirdTypePerpendicularTriangleSuitable() {
        return firstLine.isCenterBelowOrRightOfLine() && !secondLine.isCenterBelowOrRightOfLine() && !thirdLine.isCenterBelowOrRightOfLine();
    }

}