package com.example.wordle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.NonNull;

public class WordsDictionary {

    List<String> allWords;

    public WordsDictionary(List<String> words) {
        allWords = words;
    }

    /**
     * @param filePath
     */
    public WordsDictionary(@NonNull String filePath) {
        try {
            final ClassPathResource resource = new ClassPathResource(filePath);
            try (BufferedReader buffer = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                allWords = new ArrayList<>();
                allWords.addAll(buffer.lines().toList());
            }
            allWords.replaceAll(x -> x.toUpperCase());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getAllWords() {
        return allWords;
    }

}
