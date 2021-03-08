package com.oscar.challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringPermutations {

    /**
     * This method generates all possible permutations for the provided word
     *
     * @param word contains all characters to be used in resulting permutations
     * @return list of all possible permutations
     */
    public List<String> findAllPermutations(String word) {
        if (word == null) {
            return new ArrayList<>();
        }

        if (word.length() <= 1) {
            return Collections.singletonList(word);
        }

        return internalFindPermutations(word.charAt(0), word.substring(1));
    }

    private List<String> internalFindPermutations(char current, String rest) {
        // Base case
        if (rest.length() == 1) {
            return List.of(current + rest, rest + current);
        }

        List<String> permutations = new ArrayList<>();

        List<String> subPermutations = internalFindPermutations(rest.charAt(0), rest.substring(1));
        for (String subPermutation : subPermutations) {
            for (int i = 0; i < subPermutation.length() + 1; i++) {
                StringBuilder builder = new StringBuilder(subPermutation).insert(i, current);
                permutations.add(builder.toString());
            }
        }

        return permutations;
    }
}
