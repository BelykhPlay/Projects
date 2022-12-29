package Test;

import java.util.*;

public class Test {
    public static void main(String[] args) {

    }

    public static Map<Integer, Integer> solve(List<Integer> list) {
        Map<Integer, Integer> solved = new HashMap<>();

        Set<Integer> elements = getFrequentlyEncounteredItems(list);

        for (int e : elements) {
            solved.put(e, getIndexOfElement(list, e));
        }

        return solved;
    }

    public static Set<Integer> getFrequentlyEncounteredItems(List<Integer> list) {
        Set<Integer> elements = new HashSet<>();
        int maxNumberOfItem = getMaxNumberOfItem(list);

        for (int e : list) {
            if (Collections.frequency(list, e) == maxNumberOfItem) {
                elements.add(e);
            }
        }

        return elements;
    }

    public static int getMaxNumberOfItem(List<Integer> list) {
        int max = 0;

        for (int e : list) {
            max = Math.max(max, Collections.frequency(list, e));
        }

        return max;
    }

    public static int getIndexOfElement(List<Integer> list, int element) {
        int index = 0;

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) == element) {
                index = i;
                break;
            }
        }

        return index;
    }
}