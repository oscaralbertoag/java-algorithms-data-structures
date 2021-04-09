package com.oscar.challenge.clonegraph

import spock.lang.Specification

import java.util.stream.Collectors

class CloneGraphTest extends Specification {

    def 'cloneGraph() method successfully clones provided graph'() {
        given: "an existing graph"
        Node node1 = new Node(1)
        Node node2 = new Node(2)
        Node node3 = new Node(3)
        Node node4 = new Node(4)
        List<Node> neighbors1 = [node2, node4]
        List<Node> neighbors2 = [node1, node3]
        List<Node> neighbors3 = [node2, node4]
        List<Node> neighbors4 = [node1, node3]
        node1.neighbors = neighbors1
        node2.neighbors = neighbors2
        node3.neighbors = neighbors3
        node4.neighbors = neighbors4

        and: "a solution class"
        CloneGraph solution = new CloneGraph()

        when: "an existing graph is deep-cloned"
        def result = solution.cloneGraph(node1)

        then: "the returned root node contains the expected value and neighbors"
        result.val == 1
        result.neighbors.size() == 2

        and: "the adjacency map contains the expected entries"
        def adjacencyMap = solution.getAdjacencyMap()
        adjacencyMap.size() == 4
        adjacencyMap.containsKey(1)
        adjacencyMap.get(1).neighbors.size() == 2
        checkNeighbors(adjacencyMap.get(1).neighbors, [2, 4])
        adjacencyMap.containsKey(2)
        adjacencyMap.get(2).neighbors.size() == 2
        checkNeighbors(adjacencyMap.get(2).neighbors, [1, 3])
        adjacencyMap.containsKey(3)
        adjacencyMap.get(3).neighbors.size() == 2
        checkNeighbors(adjacencyMap.get(3).neighbors, [2, 4])
        adjacencyMap.containsKey(4)
        adjacencyMap.get(4).neighbors.size() == 2
        checkNeighbors(adjacencyMap.get(4).neighbors, [1, 3])
    }

    private boolean checkNeighbors(List<Node> neighbors, List<Integer> expectedList) {
        return neighbors.stream().map({ node -> node.val }).collect(Collectors.toList()).containsAll(expectedList)
    }
}
