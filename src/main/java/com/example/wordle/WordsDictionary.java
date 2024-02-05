package com.example.wordle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.springframework.util.ResourceUtils;

public class WordsDictionary {

    List<String> allWords;

    public WordsDictionary(List<String> words) {
        allWords = words;
    }

    public WordsDictionary(String filePath) {
        File file;
        try {
            file = ResourceUtils.getFile("classpath:static/words.properties");            
            allWords = Files.readAllLines(file.toPath());
            allWords.replaceAll(x -> x.toUpperCase());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }		
    }

    public List<String> getAllWords() {
        return allWords;
    }


    
    
}
