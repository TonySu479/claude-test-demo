package com.demo;

public class OrderProcessor {

    private static final double TAX_RATE = 0.10;
    private static final double FREE_SHIPPING_THRESHOLD = 50.0;
    private static final double SHIPPING_COST = 5.99;

    public enum DiscountTier {
        NONE, SILVER, GOLD, PLATINUM
    }

    public double calculateSubtotal(double unitPrice, int quantity) {
        if (unitPrice < 0) {
            throw new IllegalArgumentException("Unit price must be non-negative");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        return unitPrice * quantity;
    }

    public double applyDiscount(double subtotal, DiscountTier tier) {
        return switch (tier) {
            case SILVER   -> subtotal * 0.95;
            case GOLD     -> subtotal * 0.90;
            case PLATINUM -> subtotal * 0.80;
            default       -> subtotal;
        };
    }

    public double calculateShipping(double subtotal) {
        return subtotal >= FREE_SHIPPING_THRESHOLD ? 0.0 : SHIPPING_COST;
    }

    public double calculateTax(double amount) {
        return amount * TAX_RATE;
    }

    public double calculateTotal(double unitPrice, int quantity, DiscountTier tier) {
        double subtotal  = calculateSubtotal(unitPrice, quantity);
        double discounted = applyDiscount(subtotal, tier);
        double shipping  = calculateShipping(discounted);
        double tax       = calculateTax(discounted + shipping);
        return discounted + shipping + tax;
    }
}
