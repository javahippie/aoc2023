package net.javahippie.aoc;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Ex02a {

    public static void main(String... args) throws IOException {

        record DrawnSet(Integer red, Integer green, Integer blue) { };
        record Game(Integer number, List<DrawnSet> sets) { };

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
                .filter(g -> g.sets.stream().map(DrawnSet::red).allMatch(i -> i <= 12))
                .filter(g -> g.sets.stream().map(DrawnSet::green).allMatch(i -> i <= 13))
                .filter(g -> g.sets.stream().map(DrawnSet::blue).allMatch(i -> i <= 14))
                .map(Game::number)
                .reduce(0, Integer::sum);

        System.out.println(result);
    }
}
