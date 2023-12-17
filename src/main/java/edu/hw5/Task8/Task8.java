package edu.hw5.Task8;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Task8 {
    private static final String REGEX1 = "^[01]([01]{2})*$";
    private static final Pattern PATTERN1 = Pattern.compile(REGEX1);
    private static final String REGEX2 = "^0([01]{2})*|1[01]([01]{2})*$";
    private static final Pattern PATTERN2 = Pattern.compile(REGEX2);
    private static final String REGEX3 = "^(1*01*01*01*)+$";
    private static final Pattern PATTERN3 = Pattern.compile(REGEX3);
    private static final String REGEX4 = "^(?!11$)(?!111$)[01]+$";
    private static final Pattern PATTERN4 = Pattern.compile(REGEX4);
    private static final String REGEX5 = "^(1[01])*(1[01]?)$";
    private static final Pattern PATTERN5 = Pattern.compile(REGEX5);
    private static final String REGEX6 = "^10{2,}|010+|00+1?0*$";
    private static final Pattern PATTERN6 = Pattern.compile(REGEX6);
    private static final String REGEX7 = "^(0|10)+1?|1$";
    private static final Pattern PATTERN7 = Pattern.compile(REGEX7);

    private Task8() {
    }

    public static boolean isSatisfyFirstCondition(String binaryString) {
        Objects.requireNonNull(binaryString);

        return PATTERN1.matcher(binaryString).matches();
    }

    public static boolean isSatisfySecondCondition(String binaryString) {
        Objects.requireNonNull(binaryString);

        return PATTERN2.matcher(binaryString).matches();
    }

    public static boolean isSatisfyThirdCondition(String binaryString) {
        Objects.requireNonNull(binaryString);

        return PATTERN3.matcher(binaryString).matches();
    }

    public static boolean isSatisfyFourthCondition(String binaryString) {
        Objects.requireNonNull(binaryString);

        return PATTERN4.matcher(binaryString).matches();
    }

    public static boolean isSatisfyFifthCondition(String binaryString) {
        Objects.requireNonNull(binaryString);

        return PATTERN5.matcher(binaryString).matches();
    }

    public static boolean isSatisfySixthCondition(String binaryString) {
        Objects.requireNonNull(binaryString);

        return PATTERN6.matcher(binaryString).matches();
    }

    public static boolean isSatisfySeventhCondition(String binaryString) {
        Objects.requireNonNull(binaryString);

        return PATTERN7.matcher(binaryString).matches();
    }
}
