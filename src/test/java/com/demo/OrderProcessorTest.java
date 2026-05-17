package com.demo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderProcessorTest {

    private OrderProcessor orderProcessor;

    @BeforeEach
    void setUp() {
        orderProcessor = new OrderProcessor();
    }

    @Nested
    @DisplayName("calculateSubtotal()")
    class CalculateSubtotal {

        @Test
        @DisplayName("returns correct subtotal for valid inputs")
        void validInputs() {
            assertEquals(50.0, orderProcessor.calculateSubtotal(10.0, 5));
        }

        @Test
        @DisplayName("handles decimal unit price correctly")
        void decimalUnitPrice() {
            assertEquals(33.33, orderProcessor.calculateSubtotal(11.11, 3), 0.01);
        }

        @Test
        @DisplayName("handles quantity of 1 correctly")
        void quantityOne() {
            assertEquals(10.0, orderProcessor.calculateSubtotal(10.0, 1));
        }

        @Test
        @DisplayName("throws IllegalArgumentException for negative unit price")
        void negativeUnitPrice() {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> orderProcessor.calculateSubtotal(-10.0, 5)
            );
            assertEquals("Unit price must be non-negative", exception.getMessage());
        }

        @Test
        @DisplayName("throws IllegalArgumentException for zero quantity")
        void zeroQuantity() {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> orderProcessor.calculateSubtotal(10.0, 0)
            );
            assertEquals("Quantity must be greater than zero", exception.getMessage());
        }

        @Test
        @DisplayName("throws IllegalArgumentException for negative quantity")
        void negativeQuantity() {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> orderProcessor.calculateSubtotal(10.0, -5)
            );
            assertEquals("Quantity must be greater than zero", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("applyDiscount()")
    class ApplyDiscount {

        @Test
        @DisplayName("returns original subtotal for NONE tier")
        void noneTier() {
            assertEquals(100.0, orderProcessor.applyDiscount(100.0, OrderProcessor.DiscountTier.NONE));
        }

        @Test
        @DisplayName("applies 5% discount for SILVER tier")
        void silverTier() {
            assertEquals(95.0, orderProcessor.applyDiscount(100.0, OrderProcessor.DiscountTier.SILVER));
        }

        @Test
        @DisplayName("applies 10% discount for GOLD tier")
        void goldTier() {
            assertEquals(90.0, orderProcessor.applyDiscount(100.0, OrderProcessor.DiscountTier.GOLD));
        }

        @Test
        @DisplayName("applies 20% discount for PLATINUM tier")
        void platinumTier() {
            assertEquals(80.0, orderProcessor.applyDiscount(100.0, OrderProcessor.DiscountTier.PLATINUM));
        }

        @Test
        @DisplayName("handles decimal subtotals correctly")
        void decimalSubtotal() {
            assertEquals(47.5, orderProcessor.applyDiscount(50.0, OrderProcessor.DiscountTier.SILVER), 0.01);
        }
    }

    @Nested
    @DisplayName("calculateShipping()")
    class CalculateShipping {

        @Test
        @DisplayName("returns 0.0 for subtotal equal to free shipping threshold")
        void atThreshold() {
            assertEquals(0.0, orderProcessor.calculateShipping(50.0));
        }

        @Test
        @DisplayName("returns 0.0 for subtotal above free shipping threshold")
        void aboveThreshold() {
            assertEquals(0.0, orderProcessor.calculateShipping(100.0));
        }

        @Test
        @DisplayName("returns shipping cost for subtotal below threshold")
        void belowThreshold() {
            assertEquals(5.99, orderProcessor.calculateShipping(49.99));
        }

        @Test
        @DisplayName("returns shipping cost for very small subtotal")
        void smallSubtotal() {
            assertEquals(5.99, orderProcessor.calculateShipping(1.0));
        }

        @Test
        @DisplayName("returns shipping cost for zero subtotal")
        void zeroSubtotal() {
            assertEquals(5.99, orderProcessor.calculateShipping(0.0));
        }
    }

    @Nested
    @DisplayName("calculateTax()")
    class CalculateTax {

        @Test
        @DisplayName("returns correct 10% tax for positive amount")
        void positiveAmount() {
            assertEquals(10.0, orderProcessor.calculateTax(100.0));
        }

        @Test
        @DisplayName("returns zero tax for zero amount")
        void zeroAmount() {
            assertEquals(0.0, orderProcessor.calculateTax(0.0));
        }

        @Test
        @DisplayName("handles decimal amounts correctly")
        void decimalAmount() {
            assertEquals(5.0, orderProcessor.calculateTax(50.0), 0.01);
        }
    }

    @Nested
    @DisplayName("calculateTotal()")
    class CalculateTotal {

        @Test
        @DisplayName("calculates correct total for order without discount below shipping threshold")
        void noneDiscountBelowThreshold() {
            double total = orderProcessor.calculateTotal(10.0, 3, OrderProcessor.DiscountTier.NONE);
            assertEquals(39.59, total, 0.01);
        }

        @Test
        @DisplayName("calculates correct total for order without discount above shipping threshold")
        void noneDiscountAboveThreshold() {
            double total = orderProcessor.calculateTotal(10.0, 6, OrderProcessor.DiscountTier.NONE);
            assertEquals(66.0, total, 0.01);
        }

        @Test
        @DisplayName("calculates correct total with SILVER discount")
        void silverDiscount() {
            double total = orderProcessor.calculateTotal(10.0, 6, OrderProcessor.DiscountTier.SILVER);
            assertEquals(62.7, total, 0.01);
        }

        @Test
        @DisplayName("calculates correct total with GOLD discount")
        void goldDiscount() {
            double total = orderProcessor.calculateTotal(10.0, 6, OrderProcessor.DiscountTier.GOLD);
            assertEquals(59.4, total, 0.01);
        }

        @Test
        @DisplayName("calculates correct total with PLATINUM discount")
        void platinumDiscount() {
            double total = orderProcessor.calculateTotal(10.0, 6, OrderProcessor.DiscountTier.PLATINUM);
            assertEquals(59.39, total, 0.01);
        }

        @Test
        @DisplayName("includes shipping when discounted amount falls below threshold")
        void discountedBelowThreshold() {
            double total = orderProcessor.calculateTotal(10.0, 5, OrderProcessor.DiscountTier.PLATINUM);
            assertEquals(50.59, total, 0.01);
        }
    }
}
