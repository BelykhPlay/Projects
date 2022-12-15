package Task12;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] text = {"первая", "вторая"};

        System.out.println(Arrays.toString(text));


        check(text);

        System.out.println(Arrays.toString(text));
    }

    public static void check(String[] arr) {
        arr[0] = "вторая";
    }
}