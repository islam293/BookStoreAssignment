package utils;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataRandomGenerator {
    public static final String ALPHABETIC_UPPERCASE_SYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALPHABETIC_LOWERCASE_SYMBOLS = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMERIC_SYMBOLS = "0123456789";
    public static final String SPECIAL_SYMBOLS = "*$&@?<>!%#";

    public static final String ALPHANUMERIC_AND_SPECIAL_SYMBOLS =
            ALPHABETIC_UPPERCASE_SYMBOLS +
                    ALPHABETIC_LOWERCASE_SYMBOLS +
                    NUMERIC_SYMBOLS +
                    SPECIAL_SYMBOLS;


    public static String generateRandomString(int length) {

        List<Character> chars = new ArrayList<>(length);
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        for (int i = 0; i < length; i++) {
            // Make sure we have at least one upper, lower, number and special character.
            // Then, fill randomly from set containing all characters.
            if (!hasUpper) {
                chars.add(ALPHABETIC_UPPERCASE_SYMBOLS.charAt(RandomUtils.nextInt(0, ALPHABETIC_UPPERCASE_SYMBOLS.length())));
                hasUpper = true;
            } else if (!hasLower) {
                chars.add(ALPHABETIC_LOWERCASE_SYMBOLS.charAt(RandomUtils.nextInt(0, ALPHABETIC_LOWERCASE_SYMBOLS.length())));
                hasLower = true;
            } else if (!hasNumber) {
                chars.add(NUMERIC_SYMBOLS.charAt(RandomUtils.nextInt(0, NUMERIC_SYMBOLS.length())));
                hasNumber = true;
            } else if (!hasSpecial) {
                chars.add(SPECIAL_SYMBOLS.charAt(RandomUtils.nextInt(0, SPECIAL_SYMBOLS.length())));
                hasSpecial = true;
            } else {
                chars.add(ALPHANUMERIC_AND_SPECIAL_SYMBOLS.charAt(RandomUtils.nextInt(0, ALPHANUMERIC_AND_SPECIAL_SYMBOLS.length())));
            }
        }

        // Shuffle characters to mix up the first 4 characters
        Collections.shuffle(chars);

        return StringUtils.join(chars, "");
    }

}
