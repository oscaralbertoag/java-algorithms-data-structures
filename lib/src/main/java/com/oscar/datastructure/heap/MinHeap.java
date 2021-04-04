package com.oscar.datastructure.heap;


import java.util.ArrayList;
import java.util.List;

public class MinHeap {

    private Integer[] data;
    private int size;

    /**
     * Builds a min heap tree. If a null initial capacity is provided, the tree will default to 20 and grow as needed
     *
     * @param initialCapacity initial capacity of the heap tree
     */
    public MinHeap(Integer initialCapacity) {
        this.data = new Integer[initialCapacity == null ? 20 : initialCapacity];
        this.size = 0;
    }

    /**
     * Adds an element to the min heap tree
     *
     * @param element new date for the node to insert
     */
    public void add(Integer element) {
        ensureCapacity();
        data[size++] = element;
        heapifyUp();
    }

    private void heapifyUp() {
        Integer current = data[size - 1];
        int currentIdx = size - 1;
        while (hasParent(currentIdx)) {
            if (getParent(currentIdx) > current) {
                swap(currentIdx, getParentIndex(currentIdx));
            }
            current = getParent(currentIdx);
            currentIdx = getParentIndex(currentIdx);
        }
    }

    private void ensureCapacity() {
        if (size == data.length) {
            Integer[] increasedCapacity = new Integer[data.length + 20];
            for (int i = 0; i < data.length; i++) {
                increasedCapacity[i] = data[i];
            }
            data = increasedCapacity;
        }
    }

    /**
     * Returns the root of the tree (i.e. the minimum value in the tree)
     *
     * @return minimum value stored in the tree. Throws an exception if the tree is empty
     */
    public Integer peek() {
        if (size == 0) {
            throw new IllegalStateException("Heap is currently empty");
        }

        return data[0];
    }

    /**
     * Removes the root of the tree and performs necessary operations to maintain the tree's min heap property
     *
     * @return value of the root node (i.e. minimum value in the tree)
     */
    public Integer poll() {
        Integer top = data[0];
        swap(0, size - 1);
        data[size - 1] = null;
        size--;
        heapifyDown();
        return top;
    }

    /**
     * Builds a list in order top to bottom, left to right of all nodes in the tree
     *
     * @return list of ordered nodes currently stored in the min heap tree
     */
    public List<Integer> getOrderedNodes() {
        List<Integer> nodes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            nodes.add(data[i]);
        }
        return nodes;
    }

    /**
     * Returns the size of the current min heap tree.
     *
     * @return size of the current min heap tree
     */
    public int getSize() {
        return size;
    }

    private void heapifyDown() {
        int currentIdx = 0;
        while (hasLeftChild(currentIdx)) {
            Integer smallestChildValue = getLeftChild(currentIdx);
            int smallestChildIdx = getLeftChildIndex(currentIdx);

            if (hasRightChild(currentIdx)) {
                Integer right = getRightChild(currentIdx);
                if (right < smallestChildValue) {
                    smallestChildValue = right;
                    smallestChildIdx = getRightChildIndex(currentIdx);
                }
            }

            Integer currentValue = data[currentIdx];
            if (currentValue > smallestChildValue) {
                swap(currentIdx, smallestChildIdx);
                currentIdx = smallestChildIdx;
            } else {
                break;
            }
        }
    }

    private void swap(int indexA, int indexB) {
        Integer temp = data[indexA];
        data[indexA] = data[indexB];
        data[indexB] = temp;
    }

    private Integer getLeftChild(int currentIndex) {
        return data[getLeftChildIndex(currentIndex)];
    }

    private int getLeftChildIndex(int currentIndex) {
        return currentIndex * 2 + 1;
    }

    private boolean hasLeftChild(int currentIndex) {
        return isValidIndex(getLeftChildIndex(currentIndex)) && getLeftChild(currentIndex) != null;
    }

    private boolean isValidIndex(int index) {
        return index < data.length && index >= 0;
    }

    private Integer getRightChild(int currentIndex) {
        return data[getRightChildIndex(currentIndex)];
    }

    private int getRightChildIndex(int currentIndex) {
        return currentIndex * 2 + 2;
    }

    private boolean hasRightChild(int currentIndex) {
        return isValidIndex(getRightChildIndex(currentIndex)) && getRightChild(currentIndex) != null;
    }

    private Integer getParent(int currentIndex) {
        return data[getParentIndex(currentIndex)];
    }

    private int getParentIndex(int currentIndex) {
        if (currentIndex == 0) {
            return -1;
        }
        return (currentIndex - 1) / 2;
    }

    private boolean hasParent(int currentIndex) {
        return isValidIndex(getParentIndex(currentIndex)) && getParent(currentIndex) != null;
    }
}
