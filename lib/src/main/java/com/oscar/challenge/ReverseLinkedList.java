package com.oscar.challenge;

import com.oscar.datastructure.linkedlist.single.Node;

public class ReverseLinkedList {

    /**
     * Given a node that points to the root of a linked list, this method will mutate and reverse the provided linked list.
     *
     * @param root node pointing to the root of a linked list
     * @return root to the reversed linked list (mutates list)
     */
    public static Node<Integer> reverseLinkedList(Node<Integer> root) {
        if (root == null) return null;

        Node<Integer> current = root;
        Node<Integer> previous = null;

        while (current != null) {
            Node<Integer> oldNext = current.getNext();
            Node<Integer> newNext = previous;
            current.setNext(newNext);
            previous = current;
            current = oldNext;
            if (oldNext != null) {
                root = oldNext;
            }

        }
        return root;
    }
}
