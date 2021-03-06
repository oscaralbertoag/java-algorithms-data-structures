package com.oscar.algorithm.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountSort {

    /**
     * Takes the list provided and returns a sorted copy of the original list. Sorting is done by using the Count sorting algorithm.
     * This sorting method only sorts elements in the range 0 - rangeLimit
     *
     * @param toSort     list of integers to sort
     * @param rangeLimit upper limit (exclusive) of the range 0 - rangeLimit
     * @return sorted copy of the original list
     */
    public List<Integer> sort(List<Integer> toSort, int rangeLimit) {
        if (toSort == null || toSort.isEmpty()) {
            return new ArrayList<>();
        }

        if (toSort.size() == 1) {
            return new ArrayList<>(toSort);
        }

        List<Integer> counts = new ArrayList<>(Collections.nCopies(rangeLimit, 0));

        // Step 1: count all occurrences of the element
        for (int n : toSort) {
            counts.set(n, counts.get(n) + 1);
        }

        // Step 2: do a pass through the counts list and modify counts by adding previous + current for each value
        for (int i = 1; i < counts.size(); i++) {
            int previous = counts.get(i - 1);
            int current = counts.get(i);
            counts.set(i, previous + current);
        }

        // Step 3: create the sorted array by reading through the original and the modified counts
        List<Integer> sorted = new ArrayList<>(Collections.nCopies(toSort.size(), 0));
        for (int i = 0; i < toSort.size(); i++) {
            int originalValue = toSort.get(i);
            int indexForSortedList = counts.get(originalValue) - 1;
            sorted.set(indexForSortedList, originalValue);
            // Subtract 1 from the counts array
            counts.set(originalValue, counts.get(originalValue) - 1);
        }

        return sorted;
    }
}
