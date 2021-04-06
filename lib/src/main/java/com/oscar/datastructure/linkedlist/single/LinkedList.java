package com.oscar.datastructure.linkedlist.single;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LinkedList<T> {

    private Node<T> root;
    private Node<T> last;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public void add(T data) {
        size++;
        if (root == null) {
            root = new Node<>(data);
            last = root;
        } else {
            Node<T> newLast = new Node<>(data);
            last.setNext(newLast);
            last = newLast;
        }
    }

    public List<T> asList() {
        List<T> toReturn = new ArrayList<>();

        Node<T> current = root;
        while (current != null) {
            toReturn.add(current.getData());
            current = current.getNext();
        }

        return toReturn;
    }

    public Optional<Node<T>> find(T toFind) {
        if (root == null) return Optional.empty();

        Node<T> current = root;
        while (current != null) {
            if (current.getData().equals(toFind)) {
                return Optional.of(current);
            }
            current = current.getNext();
        }

        return Optional.empty();
    }

    public Optional<Node<T>> remove(T toRemove) {
        if (root == null) return Optional.empty();

        if (root.getData().equals(toRemove)) {
            Optional<Node<T>> toReturn = Optional.of(root);
            root = root.getNext();
            if (root == last) last = null;
            size--;
            return toReturn;
        }

        Node<T> current = root;
        while (current.getNext() != null) {
            if (toRemove.equals(current.getNext().getData())) {
                Node<T> next = current.getNext();
                Node<T> nextNext = next.getNext();
                current.setNext(nextNext);
                if (next == last) last = current;
                size--;
                return Optional.of(next);
            }
            current = current.getNext();
        }

        return Optional.empty();
    }

}
