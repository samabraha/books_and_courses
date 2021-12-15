import java.util.*;

import javax.sound.sampled.SourceDataLine;

public class Absent {
    public static String absentLetters(String input) {
        Map<Character, Boolean> mappedInput = new HashMap<>();
        for (Character c : input.toCharArray()) {
            mappedInput.put(c, true);
        }

        // abcdefghijklmnopqrstuvwxyz
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        List<Character> absent = new ArrayList<>();
        for (Character ch : alphabet) {
            if (
                !(mappedInput.containsKey(Character.toLowerCase(ch)) || mappedInput.containsKey(Character.toUpperCase(ch)))
                ) {
                absent.add(ch);
            }
        }
        
        return String.join(((CharSequence[]) absent.toArray()));
    }

    public static void main(String[] args) {
        String input = args[0];
        
        var x = absentLetters(input);
        System.out.println(x);

    }
}