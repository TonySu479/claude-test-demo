package com.demo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    private StringUtils stringUtils;

    @BeforeEach
    void setUp() {
        stringUtils = new StringUtils();
    }

    @Nested
    @DisplayName("reverse()")
    class Reverse {

        @Test
        @DisplayName("returns reversed string for normal input")
        void normalInput() {
            assertEquals("olleh", stringUtils.reverse("hello"));
        }

        @Test
        @DisplayName("returns empty string for empty input")
        void emptyInput() {
            assertEquals("", stringUtils.reverse(""));
        }

        @Test
        @DisplayName("returns single character for single character input")
        void singleCharacter() {
            assertEquals("a", stringUtils.reverse("a"));
        }

        @Test
        @DisplayName("handles strings with spaces correctly")
        void withSpaces() {
            assertEquals("dlrow olleh", stringUtils.reverse("hello world"));
        }

        @Test
        @DisplayName("handles strings with special characters correctly")
        void specialCharacters() {
            assertEquals("!@#", stringUtils.reverse("#@!"));
        }

        @Test
        @DisplayName("throws IllegalArgumentException for null input")
        void nullInput() {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> stringUtils.reverse(null)
            );
            assertEquals("Input must not be null", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("isPalindrome()")
    class IsPalindrome {

        @Test
        @DisplayName("returns true for simple palindrome")
        void simplePalindrome() {
            assertTrue(stringUtils.isPalindrome("racecar"));
        }

        @Test
        @DisplayName("returns false for non-palindrome")
        void nonPalindrome() {
            assertFalse(stringUtils.isPalindrome("hello"));
        }

        @Test
        @DisplayName("returns true for empty string")
        void emptyString() {
            assertTrue(stringUtils.isPalindrome(""));
        }

        @Test
        @DisplayName("returns true for single character")
        void singleCharacter() {
            assertTrue(stringUtils.isPalindrome("a"));
        }

        @Test
        @DisplayName("ignores case when checking palindrome")
        void ignoresCase() {
            assertTrue(stringUtils.isPalindrome("RaceCar"));
        }

        @Test
        @DisplayName("ignores spaces and punctuation")
        void ignoresSpacesAndPunctuation() {
            assertTrue(stringUtils.isPalindrome("A man, a plan, a canal: Panama"));
        }

        @Test
        @DisplayName("handles numeric palindromes")
        void numericPalindrome() {
            assertTrue(stringUtils.isPalindrome("12321"));
        }

        @Test
        @DisplayName("throws IllegalArgumentException for null input")
        void nullInput() {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> stringUtils.isPalindrome(null)
            );
            assertEquals("Input must not be null", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("countOccurrences()")
    class CountOccurrences {

        @Test
        @DisplayName("returns correct count for multiple occurrences")
        void multipleOccurrences() {
            assertEquals(3, stringUtils.countOccurrences("hello hello hello", "hello"));
        }

        @Test
        @DisplayName("returns 0 for no occurrences")
        void noOccurrences() {
            assertEquals(0, stringUtils.countOccurrences("hello world", "xyz"));
        }

        @Test
        @DisplayName("returns 1 for single occurrence")
        void singleOccurrence() {
            assertEquals(1, stringUtils.countOccurrences("hello world", "world"));
        }

        @Test
        @DisplayName("returns 0 for empty target")
        void emptyTarget() {
            assertEquals(0, stringUtils.countOccurrences("hello", ""));
        }

        @Test
        @DisplayName("returns 0 for empty text")
        void emptyText() {
            assertEquals(0, stringUtils.countOccurrences("", "hello"));
        }

        @Test
        @DisplayName("handles overlapping patterns correctly")
        void overlappingPatterns() {
            assertEquals(2, stringUtils.countOccurrences("aaaa", "aa"));
        }

        @Test
        @DisplayName("is case-sensitive")
        void caseSensitive() {
            assertEquals(1, stringUtils.countOccurrences("Hello hello", "hello"));
        }

        @Test
        @DisplayName("throws IllegalArgumentException for null text")
        void nullText() {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> stringUtils.countOccurrences(null, "hello")
            );
            assertEquals("Arguments must not be null", exception.getMessage());
        }

        @Test
        @DisplayName("throws IllegalArgumentException for null target")
        void nullTarget() {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> stringUtils.countOccurrences("hello", null)
            );
            assertEquals("Arguments must not be null", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("truncate()")
    class Truncate {

        @Test
        @DisplayName("truncates string longer than maxLength")
        void truncatesLongString() {
            assertEquals("hello...", stringUtils.truncate("hello world", 5));
        }

        @Test
        @DisplayName("returns original string when shorter than maxLength")
        void shorterThanMax() {
            assertEquals("hi", stringUtils.truncate("hi", 5));
        }

        @Test
        @DisplayName("returns original string when equal to maxLength")
        void equalToMax() {
            assertEquals("hello", stringUtils.truncate("hello", 5));
        }

        @Test
        @DisplayName("handles empty string correctly")
        void emptyString() {
            assertEquals("", stringUtils.truncate("", 5));
        }

        @Test
        @DisplayName("handles maxLength of 0")
        void maxLengthZero() {
            assertEquals("...", stringUtils.truncate("hello", 0));
        }

        @Test
        @DisplayName("throws IllegalArgumentException for null input")
        void nullInput() {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> stringUtils.truncate(null, 5)
            );
            assertEquals("Input must not be null", exception.getMessage());
        }

        @Test
        @DisplayName("throws IllegalArgumentException for negative maxLength")
        void negativeMaxLength() {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> stringUtils.truncate("hello", -1)
            );
            assertEquals("maxLength must be non-negative", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("toTitleCase()")
    class ToTitleCase {

        @Test
        @DisplayName("converts lowercase string to title case")
        void lowercaseString() {
            assertEquals("Hello World", stringUtils.toTitleCase("hello world"));
        }

        @Test
        @DisplayName("converts uppercase string to title case")
        void uppercaseString() {
            assertEquals("Hello World", stringUtils.toTitleCase("HELLO WORLD"));
        }

        @Test
        @DisplayName("converts mixed case string to title case")
        void mixedCaseString() {
            assertEquals("Hello World", stringUtils.toTitleCase("hELLo WoRLd"));
        }

        @Test
        @DisplayName("handles single word correctly")
        void singleWord() {
            assertEquals("Hello", stringUtils.toTitleCase("hello"));
        }

        @Test
        @DisplayName("handles multiple spaces between words")
        void multipleSpaces() {
            assertEquals("Hello World", stringUtils.toTitleCase("hello  world"));
        }

        @Test
        @DisplayName("handles leading and trailing spaces")
        void leadingAndTrailingSpaces() {
            assertEquals("Hello World", stringUtils.toTitleCase("  hello world  "));
        }

        @Test
        @DisplayName("returns empty string for blank input")
        void blankInput() {
            assertEquals("", stringUtils.toTitleCase(""));
        }

        @Test
        @DisplayName("returns whitespace for whitespace-only input")
        void whitespaceOnly() {
            assertEquals("   ", stringUtils.toTitleCase("   "));
        }

        @Test
        @DisplayName("throws IllegalArgumentException for null input")
        void nullInput() {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> stringUtils.toTitleCase(null)
            );
            assertEquals("Input must not be null", exception.getMessage());
        }
    }
}
