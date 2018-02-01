package com.test.exercise.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by renu on 31/01/18.
 */

public class HexConverterUtils {
    public static final String RESULT_KEY = "result";
    public static final String PREFERNCE_KEY = "value";
    public static final String PREFERNCE_INDEX = "index";
    public static final String PREFERNCE_NAME = "settings";

    private String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private String alphanumeric = "0123456789ABCDEF";
    private Pattern pattern = Pattern.compile(".{1,2}");

    public String generateRandomString(int length) {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while (sb.length() < length) {
            sb.append(alphabet.charAt((r.nextInt(26))));
        }
        return sb.toString().substring(0, length);
    }


    public String getHexString(String input) {
        byte[] bytes = input.getBytes();
        char[] charArray = alphanumeric.toCharArray();
        char[] chars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int value = bytes[j] & 0xFF;
            chars[j * 2] = charArray[value >>> 4];
            chars[j * 2 + 1] = charArray[value & 0x0F];
        }
        return new String(chars);
    }

    public int sum(String hexInput) {
        int result = 0;
        if (hexInput != null && hexInput.length() > 0) {
            Matcher matcher = pattern.matcher(hexInput);
            while (matcher.find()) {
                String chars = hexInput.substring(matcher.start(), matcher.end());
                int value = Integer.parseInt(chars, 16);
                result = result + value;
            }
        }
        return result;

    }

    public int multiply(String hexInput) {

        if (hexInput != null && hexInput.length() > 0) {
            int result = 1;
            Matcher matcher = pattern.matcher(hexInput);
            while (matcher.find()) {
                String chars = hexInput.substring(matcher.start(), matcher.end());
                int value = Integer.parseInt(chars, 16);
                result = result * value;
            }

            return result;
        }
        return 0;

    }


}
