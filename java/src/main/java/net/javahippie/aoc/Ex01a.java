package net.javahippie.aoc;

import java.io.IOException;

public class Ex01a {

    public static void main(String... args) throws IOException {
        var result = decodeAndSumInput("ex01/input.txt");
        System.out.println(result);
    }

    private static int decodeAndSumInput(String path) throws IOException {
        return FileReader.readLines(path)
                .map(Ex01a::removeNoise)
                .reduce(0, Integer::sum);
    }

    private static int removeNoise(String input) {

        int firstDigit = -1;
        int lastDigit = -1;

        for (var c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                if (firstDigit == -1) {
                    firstDigit = Character.getNumericValue(c);
                }
                lastDigit = Character.getNumericValue(c);
            }
        }

        return 10 * firstDigit + lastDigit;
    }
}
