package Task09_14;

import java.util.ArrayList;
import java.util.List;

public class Logic {
    public List<Integer> solve(List<Integer> list) {
        int lastMinIndex = lastIndexOf(list, findMin(list));
        int firstMaxIndex = firstIndexOf(list, findMax(list));

        int indexToCheck = lastMinIndex - 1;
        int currentIndex = -1;

        boolean isCheckHasOccurred = false;

        List<Integer> result = new ArrayList<>();

        for (int element : list) {
            currentIndex++;

            if (currentIndex > firstMaxIndex && currentIndex < lastMinIndex) {

                if (!isCheckHasOccurred) {
                    while (indexToCheck > firstMaxIndex) {
                        result.add(getElement(list, indexToCheck));

                        indexToCheck--;
                        isCheckHasOccurred = true;
                    }
                }

            } else {
                result.add(element);

            }
        }

        return result;
    }

    private int findMax(List<Integer> list) {
        int maxElement = 0;

        for (int element : list) {
            maxElement = Math.max(maxElement, element);

        }

        return maxElement;
    }

    private int findMin(List<Integer> list) {
        int minElement = Integer.MAX_VALUE;

        for (int element : list) {
            minElement = Math.min(minElement, element);

        }

        return minElement;
    }

    // First index of max element
    private int firstIndexOf(List<Integer> list, int value) {
        int indexMaxElement = 0;

        for (int element : list) {
            if (element == value) {
                break;
            }

            indexMaxElement++;

        }

        return indexMaxElement;
    }

    // Last index of min element
    private int lastIndexOf(List<Integer> list, int value) {
        int indexMinElement = 0;
        int currentIndex = 0;

        for (int element : list) {
            if (element == value) {
                indexMinElement = currentIndex;
            }

            currentIndex++;

        }

        return indexMinElement;
    }

    private int getElement(List<Integer> list, int index) {
        int elementByIndex = 0;
        int count = -1;

        for (int element : list) {
            count++;

            if (count == index) {
                elementByIndex = element;
                break;
            }
        }

        return elementByIndex;
    }
}
