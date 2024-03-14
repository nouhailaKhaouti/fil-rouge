package com.example.filRouge.helpers.password;
import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {

    // Define sets of characters to use in generating the password
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*_=+-/";

    // Define the length of the password
    private static final int PASSWORD_LENGTH = 12; // You can adjust the length as needed

    // Random object for generating random characters
    private static final Random random = new SecureRandom();

    public static String generatePassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        // Add at least one character from each character set
        password.append(chooseRandomChar(UPPER));
        password.append(chooseRandomChar(LOWER));
        password.append(chooseRandomChar(DIGITS));
        password.append(chooseRandomChar(SPECIAL));

        // Add remaining characters randomly
        for (int i = 4; i < PASSWORD_LENGTH; i++) {
            String charSet = getRandomCharSet();
            password.append(chooseRandomChar(charSet));
        }

        // Shuffle the password characters for better randomness
        return shuffle(password.toString());
    }

    private static char chooseRandomChar(String charSet) {
        int randomIndex = random.nextInt(charSet.length());
        return charSet.charAt(randomIndex);
    }

    private static String getRandomCharSet() {
        String[] charSets = {UPPER, LOWER, DIGITS, SPECIAL};
        int randomIndex = random.nextInt(charSets.length);
        return charSets[randomIndex];
    }

    private static String shuffle(String input) {
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }

}