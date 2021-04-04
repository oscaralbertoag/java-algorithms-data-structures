package com.oscar.challenge;

public class IsBinaryTree {

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public Node setData(int data) {
            this.data = data;
            return this;
        }

        public Node getLeft() {
            return left;
        }

        public Node setLeft(Node left) {
            this.left = left;
            return this;
        }

        public Node getRight() {
            return right;
        }

        public Node setRight(Node right) {
            this.right = right;
            return this;
        }
    }

    /**
     * This method will determine whether the provided binary search tree is valid or not. A few clarifications:
     * - a null root node is considered invalid
     * - items to the left of the root are allowed to be equal or of less value than the root.
     * - items to the right of the root must be greater than the root value only.
     *
     * @param root root node of the binary tree to inspect
     * @return true if binary search tree is valid; false otherwise
     */
    public boolean isBinaryTree(Node root) {
        if (root == null) return false;

        boolean left = true;
        boolean right = true;

        int currentData = root.getData();
        if (root.getLeft() != null) {
            left = root.getLeft().getData() <= currentData && isBinaryTree(root.getLeft());
        }

        if (root.getRight() != null) {
            right = root.getRight().getData() > currentData && isBinaryTree(root.getRight());
        }
        return left && right;
    }
}
