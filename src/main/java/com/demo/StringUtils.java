package com.demo;

public class StringUtils {

    public String reverse(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input must not be null");
        }
        return new StringBuilder(input).reverse().toString();
    }

    public boolean isPalindrome(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input must not be null");
        }
        String cleaned = input.toLowerCase().replaceAll("[^a-z0-9]", "");
        return cleaned.equals(new StringBuilder(cleaned).reverse().toString());
    }

    public int countOccurrences(String text, String target) {
        if (text == null || target == null) {
            throw new IllegalArgumentException("Arguments must not be null");
        }
        if (target.isEmpty()) return 0;
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(target, index)) != -1) {
            count++;
            index += target.length();
        }
        return count;
    }

    public String truncate(String input, int maxLength) {
        if (input == null) {
            throw new IllegalArgumentException("Input must not be null");
        }
        if (maxLength < 0) {
            throw new IllegalArgumentException("maxLength must be non-negative");
        }
        if (input.length() <= maxLength) return input;
        return input.substring(0, maxLength) + "...";
    }

    public String toTitleCase(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input must not be null");
        }
        if (input.isBlank()) return input;
        String[] words = input.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            result.append(Character.toUpperCase(word.charAt(0)))
                  .append(word.substring(1).toLowerCase())
                  .append(" ");
        }
        return result.toString().trim();
    }
}
