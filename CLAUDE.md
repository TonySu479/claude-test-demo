# Claude Instructions for claude-test-demo

## Project Overview
This is a Java 17 Maven project. Source classes live in `src/main/java/com/demo/`.
Tests live in `src/test/java/com/demo/`.

## Test Conventions

- Use **JUnit 5** (`org.junit.jupiter.api.*`)
- One test class per source class, named `<ClassName>Test.java`
- Place test files in `src/test/java/com/demo/`
- Use `@DisplayName` on each test method with a plain-English description
- Group related tests using `@Nested` inner classes (e.g. one nested class per method under test)
- Test both **happy path** and **edge cases** (null inputs, zero, negative numbers, boundaries)
- Test that exceptions are thrown with the correct message using `assertThrows`
- Use `assertEquals`, `assertTrue`, `assertFalse`, `assertThrows` from JUnit 5 — no third-party assertion libraries

## Running Tests
```
mvn test
```

## Example Test Structure
```java
package com.demo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class ExampleTest {

    private Example example;

    @BeforeEach
    void setUp() {
        example = new Example();
    }

    @Nested
    @DisplayName("someMethod()")
    class SomeMethod {

        @Test
        @DisplayName("returns expected value for valid input")
        void happyPath() {
            assertEquals(expected, example.someMethod(input));
        }

        @Test
        @DisplayName("throws IllegalArgumentException for null input")
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> example.someMethod(null));
        }
    }
}
```
