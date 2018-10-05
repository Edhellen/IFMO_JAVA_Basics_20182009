package ru.ifmo.cet.javabasics;

import java.io.*;
import java.util.Map;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Comparator;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Map.Entry;

public class WarAndPeaceExercise {

    public static String warAndPeace() {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        HashMap<String, Integer> wordsOccur = new HashMap<>();

        SortedSet<Map.Entry<String, Integer>> descSet = new TreeSet<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
                int res = e1.getValue().compareTo(e2.getValue());
                if (res == 0)
                    return e1.getKey().compareTo(e2.getKey());
                return res * -1;
            }
        });

        int count = 0;
        try {
            for (String current : Files.readAllLines(tome12Path, Charset.forName("windows-1251"))) {
                current = current.replaceAll("[^а-яА-Яa-zA-Z]", " ");
                for (String word : current.split(" ")) {
                    word = word.toLowerCase();
                    if (wordsOccur.containsKey(word)) {
                        count = wordsOccur.get(word);
                        wordsOccur.put(word, count + 1);
                    } else {
                        wordsOccur.put(word, 1);
                    }
                }
            }
        } catch (IOException e) {
        }

        count = 0;
        try {
            for (String current : Files.readAllLines(tome34Path, Charset.forName("windows-1251"))) {
                current = current.replaceAll("[^а-яА-Яa-zA-Z]", " ");
                for (String word : current.split(" ")) {
                    word = word.toLowerCase();
                    if (wordsOccur.containsKey(word)) {
                        count = wordsOccur.get(word);
                        wordsOccur.put(word, count + 1);
                    } else {
                        wordsOccur.put(word, 1);
                    }
                }
            }
        } catch (IOException e) {
        }

        for (Entry entry : wordsOccur.entrySet()) {
            descSet.add(entry);
        }

        String result = "";

        for (Map.Entry<String, Integer> en : descSet) {
            if (en.getKey().length() >= 4 && en.getValue() >= 10)
                result += en.getKey() + " - " + en.getValue() + "\n";
        }

        return result.substring(0, result.length() - 1);
    }

}

