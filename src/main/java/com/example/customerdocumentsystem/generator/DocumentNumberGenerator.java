package com.example.customerdocumentsystem.generator;

import java.util.Random;

public class DocumentNumberGenerator {


    /**
     * It is a bad method to generate random numbers, it would be more appropriate to do this in the database.
     * I designed model to data entry is more easy.
     */
    public static String documentNumberGenerator(int documentNumberSize) {

        String documentNumber = "";

        Random random = new Random();

        for (int i = 0; i < documentNumberSize; i++) {
            int n = random.nextInt(10);
            documentNumber += Integer.toString(n);

        }

        return documentNumber;
    }
}
