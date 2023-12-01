package net.javahippie.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Ex01 {

    public static void main(String... args) throws IOException {
        var result = decodeAndSumInput("ex01/input.txt");
        System.out.println(result);
    }

    private static int decodeAndSumInput(String path) throws IOException {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream(path)) {
            return new BufferedReader(new InputStreamReader(is))
                    .lines()
                    .map(Ex01::removeNoise)
                    .reduce(0, Integer::sum);

        }
    }

    private static int removeNoise(String input) {

        int firstDigit = -1;
        int lastDigit = -1;

        for(var c : input.toCharArray()) {
            if(Character.isDigit(c)) {
                if(firstDigit == -1) {
                    firstDigit = Character.getNumericValue(c);
                }
                lastDigit = Character.getNumericValue(c);
            }
        }

        return 10 * firstDigit + lastDigit;
    }
}
