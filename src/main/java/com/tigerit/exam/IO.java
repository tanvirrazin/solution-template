package com.tigerit.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Faisal Ahmed
 * This is a helper class for input/output in java.
 * Your don't need to use it if you want. This is
 * just for your convenience. Don't use Scanner or
 * System.console() stuff for input.
 */
public class IO {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private IO() {}

    public static String readLine() {
        String value;
        try {
            value = reader.readLine();
        } catch (IOException ex) {
            value = null;
        }
        return value;
    }

    public static String[] readStrings() {
        return readLine().split(" ");
    }

    public static Integer[] readInts() {
        String numberStrings[] = readLine().split(" ");
        Integer ints[] = new Integer[numberStrings.length];
        int i = 0;
        while (i < numberStrings.length) {
            ints[i] = Integer.parseInt(numberStrings[i]);
            i++;
        }
        return ints;
    }

    public static Float[] readFloats() {
        String floatStrings[] = readLine().split(" ");
        Float floats[] = new Float[floatStrings.length];
        int i = 0;
        while(i < floatStrings.length) {
            floats[i] = Float.parseFloat(floatStrings[i]);
            i++;
        }
        return floats;
    }

    public static Integer readLineAsInteger() {
        return Integer.parseInt(readLine());
    }

    public static void printLine(Object value) {
        System.out.println(value);
    }
}
