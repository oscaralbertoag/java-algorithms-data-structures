package com.oscar.algorithm.search;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ShortestReachBFS {

    /**
     * Performs Breadth-First search to find the shortest reach to every other node in a graph. If a node is not
     * reachable, -1 will be returned. Every edge is assumed to have a weight of 6.
     *
     * @param numOfNodes number of nodes (consecutive numbers; if n=3, nodes will be 1, 2, and 3)
     * @param numOfEdges number of undirected edges in the graph
     * @param edges      list of edges in the shape [(n1, n2), (n3, n4)]
     * @param startNode  starting node for this search
     * @return list of shortest reach to every other node in a graph in increasing node number order (not including the
     * start node)
     */
    public int[] bfs(int numOfNodes, int numOfEdges, int[][] edges, int startNode) {

        Map<Integer, Map<Integer, Boolean>> adjacencyMap = buildAdjacencyMap(numOfNodes, numOfEdges, edges);
        Map<Integer, Boolean> visited = new HashMap<>();

        ArrayDeque<Integer> currentLevel = new ArrayDeque<>();
        ArrayDeque<Integer> nextLevel = new ArrayDeque<>();
        currentLevel.add(startNode);
        visited.put(startNode, true);

        Map<Integer, Integer> distances = new TreeMap<>();

        int currentDistance = 6;
        while (!currentLevel.isEmpty()) {
            int current = currentLevel.removeFirst();

            if (adjacencyMap.containsKey(current)) {
                Map<Integer, Boolean> currentNeighbors = adjacencyMap.get(current);
                for (Integer neighbor : currentNeighbors.keySet()) {
                    if (!visited.containsKey(neighbor)) {
                        nextLevel.add(neighbor);
                        distances.put(neighbor, currentDistance);
                        visited.put(neighbor, true);
                    }
                }
            } else {
                distances.put(current, -1);
            }

            if (currentLevel.isEmpty()) {
                currentLevel = nextLevel;
                nextLevel = new ArrayDeque<>();
                currentDistance += 6;
            }
        }

        int[] results = new int[numOfNodes - 1];
        for (int i = 1, j = 0; i <= numOfNodes; i++, j++) {
            if (i != startNode) {
                results[j] = distances.getOrDefault(i, -1);
            } else {
                j--;
            }
        }

        return results;
    }

    private Map<Integer, Map<Integer, Boolean>> buildAdjacencyMap(int nodesN, int edgesN, int[][] edges) {
        Map<Integer, Map<Integer, Boolean>> adjacencyMap = new HashMap<>();

        for (int i = 0; i < edgesN; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            Map<Integer, Boolean> fromMap = adjacencyMap.getOrDefault(from, new HashMap<>());
            fromMap.put(to, true);

            adjacencyMap.put(from, fromMap);
            Map<Integer, Boolean> toMap = adjacencyMap.getOrDefault(to, new HashMap<>());
            toMap.put(from, true);
            adjacencyMap.put(to, toMap);
        }

        return adjacencyMap;
    }


}
