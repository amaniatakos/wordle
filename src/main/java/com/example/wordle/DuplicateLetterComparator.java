package com.example.wordle;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicateLetterComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        Long a = calculateDuplicatesWeight(o1);
        Long b = calculateDuplicatesWeight(o2);
        return a.compareTo(b);
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    private Long calculateDuplicatesWeight(String word) {
        Map<Character, Long> m = word.chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(ch -> ch, Collectors.counting()));
        return m.values().stream().filter(x -> x.longValue() > 1).collect(Collectors.summingLong(Long::longValue));
    }
}
