package com.oscar.algorithm.search

import spock.lang.Specification
import spock.lang.Unroll

class ShortestReachBFSTest extends Specification {

    @Unroll
    def "shortest reach is found by performing BFS"() {
        given:
        ShortestReachBFS solution = new ShortestReachBFS()

        when:
        def result = solution.bfs(numOfNodes, numOfEdges, edges, startNode)

        then:
        result == expected

        where:
        numOfNodes | numOfEdges | edges                               | startNode | expected
        4          | 2          | [[1, 2], [1, 3]] as int[][]         | 1         | [6, 6, -1] as int[]
        3          | 1          | [[2, 3]] as int[][]                 | 2         | [-1, 6] as int[]
        5          | 3          | [[1, 2], [1, 3], [3, 4]] as int[][] | 1         | [6, 6, 12, -1] as int[]
    }
}
