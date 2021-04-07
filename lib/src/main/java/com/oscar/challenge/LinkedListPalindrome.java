package com.oscar.challenge;

import com.oscar.datastructure.linkedlist.single.LinkedList;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class LinkedListPalindrome {

    /**
     * This method receives a linked list containing character nodes that can potentially be a palindrome and
     * determines whether this is true. This method only supports word palindromes. If a sentence with spaces is
     * provided, it will not remove spaces or special characters before checking.
     * Therefore, “Madam, I'm Adam” wouldn't be identified as a palindromic sentence.
     *
     * @param linkedList singly-linked list containing character nodes
     * @return true if linked list represents a palindrome; false otherwise
     */
    public static boolean isPalindrome(LinkedList<Character> linkedList) {
        if (linkedList == null) return false;

        int size = linkedList.getSize();
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < size / 2; i++) {
            linkedList.removeFirst().ifPresent(node -> stack.add(node.getData()));
        }

        for (int i = size / 2; i < size; i++) {
            // If odd and first in the iteration
            if (size % 2 != 0 && i == size / 2) {
                // throw away middle node
                linkedList.removeFirst();
            } else {
                Character character = stack.removeLast();
                AtomicBoolean noMatch = new AtomicBoolean(false);
                linkedList.removeFirst().ifPresent(node -> {
                    if (character != node.getData()) {
                        noMatch.set(true);
                    }
                });

                if (noMatch.get()) return false;
            }
        }

        return true;

    }
}
