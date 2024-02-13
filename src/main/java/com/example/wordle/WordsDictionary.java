package com.example.wordle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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
            try (InputStream inputStream = resource.getInputStream()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                allWords = new ArrayList<>();
                while (reader.ready()) {
                    allWords.add(reader.readLine());
                }
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
