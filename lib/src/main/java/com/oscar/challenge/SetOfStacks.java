package com.oscar.challenge;

import java.util.ArrayDeque;
import java.util.Optional;

/**
 * Challenge taken from 'Cracking the coding interview 6th edition (page 99, problem 3.3). This class maintains two
 * common stack APIs: push() and pop()
 * However, instead of using a normal stack, the underlying implementation should use several stacks of a certain size
 * each (determined by the client during stack creation). This class should create a new stack once the previous one
 * exceeds the determined capacity.
 */
public class SetOfStacks {

    private ArrayDeque<ArrayDeque<Integer>> stackOfStacks;
    private int threshold;
    private int size;

    /**
     * This method returns the size of the stack.
     *
     * @return size of current stack.
     */
    public int getSize() {
        return size;
    }

    public SetOfStacks(int threshold) {
        stackOfStacks = new ArrayDeque<>();
        this.threshold = threshold;
        size = 0;
    }

    /**
     * This method removes the top element from the stack.
     *
     * @return An empty optional if the stack is empty; an optional of the top element otherwise.
     */
    public Optional<Integer> pop() {

        if (stackOfStacks.isEmpty()) {
            return Optional.empty();
        }

        size--;

        ArrayDeque<Integer> topStack = stackOfStacks.pop();
        Integer toReturn = topStack.pop();
        if (!topStack.isEmpty()) {
            stackOfStacks.push(topStack);
        }
        return Optional.of(toReturn);
    }

    /**
     * This method pushes a new element onto the stack.
     *
     * @param item element to be pushed onto this stack.
     */
    public void push(Integer item) {
        size++;
        ArrayDeque<Integer> currentTopStack;
        if (stackOfStacks.isEmpty() || stackOfStacks.peekLast().size() >= threshold) {
            currentTopStack = new ArrayDeque<>();
        } else {
            currentTopStack = stackOfStacks.pop();
        }
        currentTopStack.push(item);
        stackOfStacks.push(currentTopStack);
    }


}
