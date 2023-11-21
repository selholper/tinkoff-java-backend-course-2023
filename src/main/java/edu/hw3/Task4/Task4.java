package edu.hw3.Task4;

public final class Task4 {
    private static final int MIN_ARABIC_NUMBER = 1;
    private static final int MAX_ARABIC_NUMBER = 3999;
    private static final int[] KEY_ARABIC_NUMBERS = new int[]{
        1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
    };
    private static final String[] KEY_ROMAN_NUMBERS = new String[] {
        "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
    };

    private Task4() {
    }

    public static String convertToRoman(int number) {
        if (number < MIN_ARABIC_NUMBER || number > MAX_ARABIC_NUMBER) {
            throw new IllegalArgumentException("The number must be an integer in the range from 1 to 3999");
        }

        int arabicNumber = number;
        StringBuilder romanNumber = new StringBuilder();
        for (int i = 0; i < KEY_ARABIC_NUMBERS.length; ++i) {
            romanNumber.append(KEY_ROMAN_NUMBERS[i].repeat(arabicNumber / KEY_ARABIC_NUMBERS[i]));
            arabicNumber %= KEY_ARABIC_NUMBERS[i];
        }

        return romanNumber.toString();
    }
}
