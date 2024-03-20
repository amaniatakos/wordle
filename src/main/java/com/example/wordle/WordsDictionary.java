package com.example.wordle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.NonNull;

/**
 * This dictionary holds all the candidate words as Wordle answers.<br/>
 * You can pass a list of words or a filePath to load the words from there.
 */
public class WordsDictionary {

    /**
     * The list that contains all the words.
     */
    List<String> allWords;
   
    /**
     * Text file that contains one word per line.
     * 
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

    /**
     * Get the dictionary.
     * 
     * @return dictionary
     */
    public List<String> getAllWords() {
        return allWords;
    }

}
