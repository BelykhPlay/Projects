package Task08_25;

public class Logic {
    public boolean checkOrderedSequence(int[][] arr) {
        // Rows and columns
        int rows = arr.length;
        int columns = arr[0].length;

        // Array for sequence
        int[] sequence = new int[rows*columns];
        int count = 0;

        // Flags
        boolean isNeedCheckUp = true;
        boolean isArrayOrdered = true;
        boolean isIncreasing = false;
        boolean isDecreasing = false;

        // Building a sequence
        for (int j = 0; j < columns; j++) { // By columns
            int start = changeVariable(isNeedCheckUp, rows); // Changing the direction of verification

            for (int i = 0; i < rows; i++) { // By rows
                int index = Math.abs(start - i); // Index of the element in the array

                sequence[count++] = arr[index][j];
            }

            // true -> false or false -> true
            isNeedCheckUp = isCheckPassed(isNeedCheckUp);
        }

//        System.out.println(Arrays.toString(sequence));
        // Checking the sequence array for ordering
        for(int i = 1; i < sequence.length; i++) {
            if(sequence[i-1] < sequence[i]) {
                isIncreasing = true;

            } else if(sequence[i-1] > sequence[i]) {
                isDecreasing = true;

            } else {
                continue;
            }

            if(isIncreasing == isDecreasing) {
                isArrayOrdered = false;
                break;
            }
        }

        return isArrayOrdered;
    }

    private static int changeVariable(boolean flag, int n1) {
        // Если нужно проверять вверх
        if (flag) {
            return n1 - 1;
            // Если нужно проверять вниз
        } else {
            return 0;

        }
    }

    private static boolean isCheckPassed(boolean flag) {
        return !flag;

    }

}