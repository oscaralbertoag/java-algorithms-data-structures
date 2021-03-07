package com.oscar.algorithm.memoization

import spock.lang.Specification
import spock.lang.Unroll

import static com.oscar.algorithm.memoization.UniquePaths.OBSTACLE
import static com.oscar.algorithm.memoization.UniquePaths.VALID

class UniquePathsTest extends Specification {

    @Unroll
    def "unique paths method finds all expected paths in 2D grid"() {
        given:
        UniquePaths uniquePaths = new UniquePaths()

        when: "The grid is inspected for unique paths"
        def result = uniquePaths.findUniquePaths(grid, 0, 0)

        then: "The correct number of possible paths has been found"
        expected == result

        where:
        grid                                  | expected
        [[VALID, VALID, VALID],
         [VALID, VALID, VALID]] as String[][] | 3
        [[VALID, VALID, VALID],
         [VALID, OBSTACLE, VALID],
         [VALID, VALID, VALID]] as String[][] | 2
        [[VALID, VALID, VALID],
         [OBSTACLE, OBSTACLE, VALID],
         [VALID, VALID, VALID]] as String[][] | 1
        [[VALID, VALID, VALID],
         [OBSTACLE, OBSTACLE, OBSTACLE],
         [VALID, VALID, VALID]] as String[][] | 0
        [[VALID, VALID, VALID],
         [OBSTACLE, VALID, VALID],
         [VALID, VALID, VALID]] as String[][] | 3

    }

    @Unroll
    def "unique paths (memoized) method finds all expected paths in 2D grid"() {
        given:
        UniquePaths uniquePaths = new UniquePaths()

        when: "The grid is inspected for unique paths"
        Integer[][] resultsCache = new Integer[grid.length][grid[0].length]
        def result = uniquePaths.findUniquePathsMemoized(grid, 0, 0, resultsCache)

        then: "The correct number of possible paths has been found"
        expected == result

        where:
        grid                                  | expected
        [[VALID, VALID, VALID],
         [VALID, VALID, VALID]] as String[][] | 3
        [[VALID, VALID, VALID],
         [VALID, OBSTACLE, VALID],
         [VALID, VALID, VALID]] as String[][] | 2
        [[VALID, VALID, VALID],
         [OBSTACLE, OBSTACLE, VALID],
         [VALID, VALID, VALID]] as String[][] | 1
        [[VALID, VALID, VALID],
         [OBSTACLE, OBSTACLE, OBSTACLE],
         [VALID, VALID, VALID]] as String[][] | 0
        [[VALID, VALID, VALID],
         [OBSTACLE, VALID, VALID],
         [VALID, VALID, VALID]] as String[][] | 3
        [[VALID, VALID, OBSTACLE],
         [VALID, VALID, VALID],
         [VALID, VALID, VALID]] as String[][] | 5
        [[VALID, VALID, VALID],
         [OBSTACLE, VALID, OBSTACLE],
         [VALID, VALID, VALID]] as String[][] | 1
        [[VALID, VALID, VALID],
         [VALID, VALID, VALID],
         [VALID, VALID, VALID],
         [VALID, VALID, VALID],
         [VALID, VALID, VALID],
         [VALID, VALID, VALID],
         [VALID, VALID, VALID]] as String[][] | 28
    }
}
