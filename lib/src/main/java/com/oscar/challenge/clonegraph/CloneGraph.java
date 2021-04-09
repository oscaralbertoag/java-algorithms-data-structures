package com.oscar.challenge.clonegraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clone Graph problem from LeetCode
 */
public class CloneGraph {

    private Map<Integer, Node> adjacencyMap;

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        ArrayDeque<Node> queue = new ArrayDeque<>();

        adjacencyMap = new HashMap<>();
        Map<Integer, Boolean> visited = new HashMap<>();

        queue.add(node);
        visited.put(node.val, true);

        Node firstCopy = new Node(node.val);
        adjacencyMap.put(node.val, firstCopy);

        while (!queue.isEmpty()) {
            Node current = queue.removeFirst();
            List<Node> neighbors = current.neighbors;

            Node currentCopy = adjacencyMap.getOrDefault(current.val, new Node(current.val));
            List<Node> neighborsCopy = new ArrayList<>();

            for (Node neighbor : neighbors) {
                if (!visited.containsKey(neighbor.val)) {
                    queue.add(neighbor);
                    visited.put(neighbor.val, true);
                }
                Node neighborCopy = adjacencyMap.getOrDefault(neighbor.val, new Node(neighbor.val));
                neighborsCopy.add(neighborCopy);
                adjacencyMap.put(neighbor.val, neighborCopy);
            }
            currentCopy.neighbors = neighborsCopy;
            adjacencyMap.put(currentCopy.val, currentCopy);
        }

        return firstCopy;
    }

    public Map<Integer, Node> getAdjacencyMap() {
        return new HashMap<>(adjacencyMap);
    }
}
