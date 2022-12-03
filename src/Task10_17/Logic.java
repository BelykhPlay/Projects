package Task10_17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;

public class Logic {
    public static double[][] sortCoordinates(double[] data) {
        double[][] coordinates = new double[3][2];
        double[] sortedDataYCoordinates = DoubleStream.of(data[1], data[3], data[5]).sorted().toArray();

        List<Double> dataList = new ArrayList<>();
        Arrays.stream(data).forEach(dataList::add);

        int index = 2;
        for (int i = 0; true; i = i + 2) {

            if (dataList.size() == 2) {
                coordinates[1][0] = dataList.get(0);
                coordinates[1][1] = dataList.get(1);

                break;
            }

            for (int j = 1; j < dataList.size(); j = j + 2) {
                if (sortedDataYCoordinates[i] == dataList.get(j)) {
                    coordinates[index][0] = dataList.get(j - 1);
                    coordinates[index][1] = dataList.get(j);

                    dataList.remove(j);
                    dataList.remove(j - 1);

                    j = 1;
                    index = 0;

                }

            }

        }

        return determiningPositionOfBCoordinate(coordinates);

    }

    public static double[][] determiningPositionOfBCoordinate(double[][] coordinates) {
        // Чтобы точка A была всегда левее B при условии, что их координаты по y совпадают
        if (coordinates[0][0] > coordinates[1][0] && coordinates[0][1] == coordinates[1][1]) {
            double[] storage = coordinates[1];

            coordinates[1] = coordinates[0];
            coordinates[0] = storage;

            // Чтобы точка B была всегда под точкой A, при условии что координаты точек B и C совпадают по y
        } else if (coordinates[1][0] > coordinates[2][0] && coordinates[1][1] == coordinates[2][1]) {
            double[] storage = coordinates[2];

            coordinates[2] = coordinates[1];
            coordinates[1] = storage;

        }

        return coordinates;
    }

}