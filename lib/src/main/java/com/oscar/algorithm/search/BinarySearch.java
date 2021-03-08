package com.oscar.algorithm.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BinarySearch {

    /**
     * Performs binary search on the provided list of numbers. It does not mutate the list.
     *
     * @param numbers contains all the numbers to look through
     * @param toFind  number to find
     * @return number matched; empty optional otherwise
     */
    public Optional<Integer> find(List<Integer> numbers, int toFind) {
        if (numbers == null || numbers.isEmpty()) {
            return Optional.empty();
        }

        List<Integer> nCopy = new ArrayList<>(numbers);
        Collections.sort(nCopy);

        int rangeStart = 0;
        int rangeEnd = nCopy.size();
        while (rangeEnd - rangeStart > 1) {
            int middleIndex = rangeStart + (rangeEnd - rangeStart) / 2;
            Integer middleValue = nCopy.get(middleIndex);
            if (middleValue == toFind) {
                return Optional.of(nCopy.get(middleIndex));
            } else if (middleValue > toFind) {
                // Search to the left
                rangeEnd = middleIndex;
            } else {
                // Search to the right
                rangeStart = middleIndex;
            }
        }

        if (nCopy.get(rangeStart) == toFind) {
            return Optional.of(nCopy.get(rangeStart));
        }

        if (nCopy.get(rangeEnd - 1) == toFind) {
            return Optional.of(nCopy.get(rangeEnd - 1));
        }

        return Optional.empty();
    }
}
