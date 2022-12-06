package Task11_25;

import java.util.Arrays;
import java.util.List;

public class Logic {
    private Logic() {
        throw new IllegalStateException("Utility class");
    }
    public static List<String> getEmail(String text) {
        return Arrays.stream(text.split(". |, |! |/? |: |; |\"|.\"|\".|'|.'|'."))
                .filter(e -> e.split("@").length == 2)
                .distinct()
                .sorted()
                .toList();
    }

}