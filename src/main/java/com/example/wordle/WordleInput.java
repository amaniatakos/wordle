package com.example.wordle;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class WordleInput {
    List<Pair> exactMatch = new ArrayList<>();
    List<Character> notExist = new ArrayList<>();
    List<Pair> misplaced = new ArrayList<>();

    public List<Pair> getExactMatch() {
        return exactMatch;
    }

    public List<Pair> getMisplaced() {
        return misplaced;
    }

    public List<Character> getNotExist() {
        return notExist;
    }

    public void setExactMatch(List<Pair> exactMatch) {
        this.exactMatch = exactMatch;
    }

    public void setMisplaced(List<Pair> misplaced) {
        this.misplaced = misplaced;
    }

    public void setNotExist(List<Character> notExist) {
        this.notExist = notExist;
    }

}
