package com.example.wordle;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.function.Predicate;

/**
 * 
 */
public class Solver {

    WordsDictionary dictionary;

    public Solver(WordsDictionary dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> findWords(WordleInput input) {
        Set<Predicate<String>> predicates = new HashSet<Predicate<String>>();
        for (Pair pair : input.getExactMatch()) {
            predicates.add(string -> string.charAt(pair.getPosition() - 1) == Character.toUpperCase(pair.getLetter()));
        }
        for (Pair pair : input.getMisplaced()) {
            predicates.add(string -> string.charAt(pair.getPosition() - 1) != Character.toUpperCase(pair.getLetter()));
            predicates.add(string -> string.contains(String.valueOf(Character.toUpperCase(pair.getLetter()))));
        }
        for (Character letter : input.getNotExist()) {
            predicates.add(string -> !string.contains(String.valueOf(Character.toUpperCase(letter))));
        }

        return dictionary.getAllWords().stream()
                .filter(predicates.stream().reduce(x -> true, Predicate::and))
                .collect(Collectors.toList());

    }

}
