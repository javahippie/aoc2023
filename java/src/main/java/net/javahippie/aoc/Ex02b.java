package net.javahippie.aoc;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ex02b {

    public static void main(String... args) throws IOException {

        record DrawnSet(Integer red, Integer green, Integer blue) { };
        record Game(Integer number, List<DrawnSet> sets) { };
        final List<Function<DrawnSet, Integer>> colorFunctions = List.of(DrawnSet::red, DrawnSet::green, DrawnSet::blue);

        Function<String, Integer> parseGameNumber = line -> Integer.valueOf(line.split(":")[0].substring(5));

        Function<Integer, Integer> intValueOrZero = in -> (in == null) ? 0 : in;

        Function<String, Map<String, Integer>> parseCubes = line -> Arrays.stream(line.split(","))
                .map(s -> s.split(" "))
                .collect(Collectors.toMap(arr -> arr[2], arr2 -> Integer.valueOf(arr2[1]), Integer::sum));

        Function<String, List<DrawnSet>> parseSets = line -> Arrays
                .stream(line.substring(7).split(";"))
                .map(parseCubes)
                .map(c -> new DrawnSet(intValueOrZero.apply(c.get("red")), intValueOrZero.apply(c.get("green")), intValueOrZero.apply(c.get("blue"))))
                .toList();



        var result = FileReader.readLines("ex02/input.txt")
                .map(l -> new Game(parseGameNumber.apply(l), parseSets.apply(l)))
                .map(g -> colorFunctions.stream().map(f -> g.sets.stream().map(f).max(Integer::compareTo).orElse(0)).reduce(1, (n1, n2) -> n1 * n2))
                .reduce(0, Integer::sum);


        System.out.println(result);
    }
}
