package others;

import java.util.HashMap;
import java.util.Map;

public class Anagrams {

    private static boolean isAnagram(String string1, String string2) {
        Map<Character, Integer> charactersCount = new HashMap<>();
        char c1, c2;

        if (string1.length() != string2.length()) {
            return false;
        }

        for (int i = 0; i < string1.length(); i++) {
            c1 = string1.charAt(i);
            c2 = string2.charAt(i);
            if (charactersCount.containsKey(c1)) {
                charactersCount.put(c1, charactersCount.get(c1) + 1);
            } else {
                charactersCount.put(c1, 1);
            }
            if (charactersCount.containsKey(c2)) {
                charactersCount.put(c2, charactersCount.get(c2) - 1);
            } else {
                charactersCount.put(c2, -1);
            }
        }

        for (char key : charactersCount.keySet()) {
            if (charactersCount.get(key) != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String... args) {
        String string1 = "triangle";
        String string2 = "integral";
        System.out.println(isAnagram(string1, string2));
    }
}
