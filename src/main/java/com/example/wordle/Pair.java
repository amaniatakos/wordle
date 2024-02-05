package com.example.wordle;

public class Pair {

    char letter;
    int position;
    
    public Pair(char letter, int position) {
        this.letter = letter;
        this.position = position;
    }
    
    public char getLetter() {return letter;}
    
    public int getPosition() {return position;}

    public static Pair of(char letter, int position) {
        return new Pair(letter, position);
    }
    
}
