package ru.ifmo.cet.javabasics;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class WarAndPeaceExercise {

    public static String warAndPeace() {

        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        String warAndPeaceTxt = readAllLinesFromFile(tome12Path);
        warAndPeaceTxt += readAllLinesFromFile(tome34Path);

        Map<String, Integer> wordMap = new HashMap<>();
        Arrays.stream(warAndPeaceTxt.replaceAll("[^а-яА-Яa-zA-Z]", " ").split(" "))
                .map(String::toLowerCase)
                .filter(word -> !(word.length() < 4)).filter(word -> !word.isEmpty())
                .forEach(word ->
                        wordMap.put(word, wordMap.getOrDefault(word, 0) + 1));

        String result = wordMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue()
                        .reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .filter(e -> e.getValue() >= 10)
                .map(e -> e.getKey() + " - " + e.getValue())
                .map(Object::toString)
                .collect(Collectors.joining("\n"));

        return result;
    }

    private static String readAllLinesFromFile(Path filePath) {

        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(filePath, Charset.forName("windows-1251"))) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
        }

        return contentBuilder.toString();
    }

}

