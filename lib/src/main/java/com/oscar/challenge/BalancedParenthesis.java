package com.oscar.challenge;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class BalancedParenthesis {

    private static final Map<Character, Character> elements = Map.of('{', '}', '(', ')', '[', ']');

    /**
     * Determines whether an expression with parenthesis, square and curly brackets is balanced.
     * A balanced expression has the same amount of opening and closing elements. This methods assumes that only valid
     * characters are provided, so it is not performing any validations on the expression.
     *
     * @param expression expression to process containing only parenthesis, square, and curly brackets
     * @return true if expression is correctly balanced; false otherwise
     */
    public boolean isBalanced(String expression) {
        if (expression == null || expression.equals("")) {
            return false;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);
            // If it is an opening character
            if (elements.containsKey(current)) {
                stack.add(current);
            } else if (stack.isEmpty() || elements.get(stack.removeLast()) != current) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
