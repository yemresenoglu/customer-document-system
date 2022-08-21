package com.example.customerdocumentsystem.generator;

import java.util.Random;

public class CustomerNumberGenerator {


    /**
     * It is a bad method to generate random numbers, it would be more appropriate to do this in the database.
     * I designed model to data entry is more easy.
     */

    public static Long customerNumberGenerator(int customerNumberSize) {

        String customerNumber = "";

        Random random = new Random();

        for (int i = 0; i < customerNumberSize; i++) {
            int n = random.nextInt(10);
            customerNumber += Integer.toString(n);

        }

        return Long.parseLong(customerNumber);
    }
}
