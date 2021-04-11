package com.oscar.challenge.linkedlistdups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveLinkedListDuplicates {

    /**
     * Given a linked list in no particular order, this method will remove any duplicates and mutate the provided
     * linked list with duplicate nodes removed
     *
     * @param root root node of the linked list to process and have its duplicates removed
     * @return the root node to the provided linked list with all duplicate nodes removed
     */
    public Node removeLinkedListDuplicates(Node root) {
        if (root == null || root.getNext() == null) return root;

        Map<String, Boolean> uniqueValues = new HashMap<>();

        Node previous = null;
        Node current = root;

        while (current != null) {
            if (!uniqueValues.containsKey(current.getData())) {
                uniqueValues.put(current.getData(), true);
                previous = current;
                current = current.getNext();
            } else {
                Node oldNext = current.getNext();
                previous.setNext(oldNext);
                current = oldNext;
            }
        }

        return root;
    }

    /**
     * Utility method used during validation testing. This method will build a list of node values given the root to a
     * singly-linked list
     *
     * @param root root node of a linked list
     * @return a list of values representing the node values contained in the provided linked list.
     */
    public List<String> nodesAsList(Node root) {
        List<String> nodeValues = new ArrayList<>();
        Node current = root;
        while (current != null) {
            nodeValues.add(current.getData());
            current = current.getNext();
        }
        return nodeValues;
    }

    /**
     * Utility method that helps with validation tests. This method will take a list of node values and build a linked
     * list from it.
     *
     * @param nodeValues list of values from which a linked list will be built
     * @return root node of a singly-linked list
     */
    public Node listAsNodes(List<String> nodeValues) {
        if (nodeValues == null || nodeValues.isEmpty()) return null;

        Node root = new Node(nodeValues.get(0));
        Node current = root;

        for (int i = 1; i < nodeValues.size(); i++) {
            Node newNode = new Node(nodeValues.get(i));
            current.setNext(newNode);
            current = newNode;
        }

        return root;
    }
}
