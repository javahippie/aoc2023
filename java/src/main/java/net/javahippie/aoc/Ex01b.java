package net.javahippie.aoc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ex01b {

    private static Map<String, Integer> TEXT_OCCURRENCES = new HashMap<>();

    public static void main(String... args) throws IOException {
        TEXT_OCCURRENCES.put("one", 1);
        TEXT_OCCURRENCES.put("1", 1);
        TEXT_OCCURRENCES.put("two", 2);
        TEXT_OCCURRENCES.put("2", 2);
        TEXT_OCCURRENCES.put("three", 3);
        TEXT_OCCURRENCES.put("3", 3);
        TEXT_OCCURRENCES.put("four", 4);
        TEXT_OCCURRENCES.put("4", 4);
        TEXT_OCCURRENCES.put("five", 5);
        TEXT_OCCURRENCES.put("5", 5);
        TEXT_OCCURRENCES.put("six", 6);
        TEXT_OCCURRENCES.put("6", 6);
        TEXT_OCCURRENCES.put("seven", 7);
        TEXT_OCCURRENCES.put("7", 7);
        TEXT_OCCURRENCES.put("eight", 8);
        TEXT_OCCURRENCES.put("8", 8);
        TEXT_OCCURRENCES.put("nine", 9);
        TEXT_OCCURRENCES.put("9", 9);

        var result = decodeAndSumInput("ex01/input.txt");
        System.out.println(result);
    }

    private static int decodeAndSumInput(String path) throws IOException {

        return FileReader.readLines(path)
                    .map(Ex01b::replaceTextOccurrences)
                    .reduce(0, Integer::sum);
    }

    private static Integer replaceTextOccurrences(String input) {
        Map<Integer, Integer> occurrences = new HashMap<>();
        for (var occurrence : TEXT_OCCURRENCES.entrySet()) {
            String sub = occurrence.getKey();
            var firstOccurrence = input.indexOf(sub);
            var lastOccurrence = input.lastIndexOf(sub);
            occurrences.put(firstOccurrence, occurrence.getValue());
            occurrences.put(lastOccurrence, occurrence.getValue());
        }

        // The minus one is always "not found"
        occurrences.remove(-1);

        List<Integer> indexList = new ArrayList<>(occurrences.keySet());
        indexList.sort(Integer::compare);

        Integer firstNumber = occurrences.get(indexList.getFirst());
        Integer lastNumber = occurrences.get(indexList.getLast());

        System.out.println(firstNumber * 10 + lastNumber + " - " + input);

        return firstNumber * 10 + lastNumber;
    }

}
