package com.example.wordle;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Order words based on uniqueness of theire characters.<br>
 * For example the words [LALA, LORD] will be sorted as [LORD, LALA] because
 * LALA contains two L's.
 */
public class DuplicateLetterComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        Long a = calculateDuplicatesWeight(o1);
        Long b = calculateDuplicatesWeight(o2);
        return a.compareTo(b);
    }

    /**
     * Count how many duplicate letters exist in the word and return the weight.
     * 
     * @param word
     * @return the weight of the word based on how many dupliacte letters exist.
     */
    private Long calculateDuplicatesWeight(String word) {
        Map<Character, Long> m = word.chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(ch -> ch, Collectors.counting()));
        return m.values().stream().filter(x -> x.longValue() > 1).collect(Collectors.summingLong(Long::longValue));
    }
}
