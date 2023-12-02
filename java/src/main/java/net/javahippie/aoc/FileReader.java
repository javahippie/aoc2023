package net.javahippie.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class FileReader {

    public static Stream<String> readLines(String path) throws IOException {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(path);
        return new BufferedReader(new InputStreamReader(is)).lines();
    }
}
