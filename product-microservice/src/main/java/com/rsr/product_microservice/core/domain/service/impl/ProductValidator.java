package com.rsr.product_microservice.core.domain.service.impl;

import com.rsr.product_microservice.core.domain.model.Product;

public class ProductValidator {

    public static void validate(Product product) throws IllegalArgumentException {

        //needs to be done first, or else NullPointerException will be thrown
        validateNotNull(product);

        validateNumberInStock(product);
        validatePrice(product);
        validateName(product);
    }

    private static void validateNotNull(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product must not be null");
        }
    }

    private static void validateNumberInStock(Product product) {
        if (product.getNumberInStock() < 0) {
            throw new IllegalArgumentException("Product-Amount must be positive");
        }
    }

    private static void validatePrice(Product product) {
        if (product.getPriceInEuro() <= 0.0) {
            throw new IllegalArgumentException("Product Price must be positive");
        }
        if (countDecimalPlaces(product.getPriceInEuro()) > 2) {
            throw new IllegalArgumentException("Price must be realistic");
        }
    }

    private static void validateName(Product product) {
        if (product.getName() == null || product.getName().equals("")) {
            throw new IllegalArgumentException("Product must have name");
        }
    }

    private static int countDecimalPlaces(double number) {
        String numberString = Double.toString(number);

        if (numberString.contains(".")) {
            String[] parts = numberString.split("\\.");
            return parts[1].length();
        } else {
            return 0;
        }
    }

}
