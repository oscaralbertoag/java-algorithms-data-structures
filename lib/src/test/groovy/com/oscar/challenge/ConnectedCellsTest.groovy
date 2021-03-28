package com.oscar.challenge

import spock.lang.Specification

import java.util.function.Function
import java.util.stream.Collectors

class ConnectedCellsTest extends Specification {

    def "finds largest connected group of cells"() {
        given: "A grid with values"
        def grid = [["A", "B", "C", "A", "A"],
                    ["B", "D", "C", "Z", "E"],
                    ["A", "Z", "C", "Z", "D"],
                    ["H", "Z", "Z", "Z", "D"],
                    ["H", "H", "H", "D", "D"]]
        ConnectedCells solution = new ConnectedCells(grid as String[][])

        when: "The largest section is searched"
        def result = solution.findLargestSection()

        then: "The result contains the largest possible group of connected cells"
        result.size() == 6
        and: "The cells are in the expected coordinates"
        def expected = [new ConnectedCells.Coordinate(1, 3), new ConnectedCells.Coordinate(2, 3), new ConnectedCells.Coordinate(3, 3),
                        new ConnectedCells.Coordinate(3, 2), new ConnectedCells.Coordinate(3, 1), new ConnectedCells.Coordinate(2, 1)]
        def resultsMap = result.stream().collect(Collectors.toMap(Function.identity(), Function.identity()))
        for (ConnectedCells.Coordinate coordinate : expected) {
            assert resultsMap.containsKey(coordinate)
        }
    }

    def "finds largest connected group of cells (outer frame cells)"() {
        given: "A grid with values"
        def grid = [["A", "A", "A", "A", "A"],
                    ["A", "D", "C", "Z", "A"],
                    ["A", "Z", "C", "Z", "A"],
                    ["A", "Z", "Z", "Z", "A"],
                    ["A", "A", "A", "A", "A"]]
        ConnectedCells solution = new ConnectedCells(grid as String[][])

        when: "The largest section is searched"
        def result = solution.findLargestSection()

        then: "The result contains the largest possible group of connected cells"
        result.size() == 16
        and: "The cells are in the expected coordinates"
        def expected = [new ConnectedCells.Coordinate(0, 0), new ConnectedCells.Coordinate(0, 1), new ConnectedCells.Coordinate(0, 2), new ConnectedCells.Coordinate(0, 3),
                        new ConnectedCells.Coordinate(0, 4), new ConnectedCells.Coordinate(1, 0), new ConnectedCells.Coordinate(1, 4), new ConnectedCells.Coordinate(2, 0),
                        new ConnectedCells.Coordinate(2, 4), new ConnectedCells.Coordinate(3, 0), new ConnectedCells.Coordinate(4, 3), new ConnectedCells.Coordinate(3, 4),
                        new ConnectedCells.Coordinate(4, 0), new ConnectedCells.Coordinate(4, 1), new ConnectedCells.Coordinate(4, 2), new ConnectedCells.Coordinate(4, 4)]
        def resultsMap = result.stream().collect(Collectors.toMap(Function.identity(), Function.identity()))
        for (ConnectedCells.Coordinate coordinate : expected) {
            assert resultsMap.containsKey(coordinate)
        }
    }

    def "finds largest connected group of cells (2 groups of same size)"() {
        given: "A grid with values"
        def grid = [["A", "A", "C", "A", "A"],
                    ["A", "A", "C", "Z", "E"],
                    ["U", "Z", "C", "Z", "D"],
                    ["U", "T", "H", "T", "D"],
                    ["H", "H", "H", "J", "D"]]
        ConnectedCells solution = new ConnectedCells(grid as String[][])

        when: "The largest section is searched"
        def result = solution.findLargestSection()

        then: "The result contains the largest possible group of connected cells (first one found)"
        result.size() == 4
        and: "The cells are in the expected coordinates"
        def expected = [new ConnectedCells.Coordinate(0, 0), new ConnectedCells.Coordinate(0, 1),
                        new ConnectedCells.Coordinate(1, 0), new ConnectedCells.Coordinate(1, 1)]
        def resultsMap = result.stream().collect(Collectors.toMap(Function.identity(), Function.identity()))
        for (ConnectedCells.Coordinate coordinate : expected) {
            assert resultsMap.containsKey(coordinate)
        }
    }

    def "finds largest connected group of cells (all groups of 1)"() {
        given: "A grid with values"
        def grid = [["A", "B", "C"],
                    ["D", "E", "F"],
                    ["G", "H", "I"]]
        ConnectedCells solution = new ConnectedCells(grid as String[][])

        when: "The largest section is searched"
        def result = solution.findLargestSection()

        then: "The result contains the largest possible group of connected cells (first one found)"
        result.size() == 1
        and: "The cells are in the expected coordinates"
        def expected = [new ConnectedCells.Coordinate(0, 0)]
        def resultsMap = result.stream().collect(Collectors.toMap(Function.identity(), Function.identity()))
        for (ConnectedCells.Coordinate coordinate : expected) {
            assert resultsMap.containsKey(coordinate)
        }
    }

    def "finds largest connected group of cells (rectangle grid)"() {
        given: "A grid with values"
        def grid = [["A", "A", "C", "A", "A"],
                    ["A", "A", "C", "Z", "E"],
                    ["Z", "Z", "Z", "Z", "Z"]]
        ConnectedCells solution = new ConnectedCells(grid as String[][])

        when: "The largest section is searched"
        def result = solution.findLargestSection()

        then: "The result contains the largest possible group of connected cells"
        result.size() == 6
        and: "The cells are in the expected coordinates"
        def expected = [new ConnectedCells.Coordinate(2, 0), new ConnectedCells.Coordinate(2, 1),
                        new ConnectedCells.Coordinate(2, 2), new ConnectedCells.Coordinate(2, 3),
                        new ConnectedCells.Coordinate(2, 4), new ConnectedCells.Coordinate(1, 3)]
        def resultsMap = result.stream().collect(Collectors.toMap(Function.identity(), Function.identity()))
        for (ConnectedCells.Coordinate coordinate : expected) {
            assert resultsMap.containsKey(coordinate)
        }
    }

    def "finds largest connected group of cells (diagonal)"() {
        given: "A grid with values"
        def grid = [["K", "A", "C", "A", "A"],
                    ["A", "K", "C", "Z", "E"],
                    ["O", "O", "K", "Z", "J"],
                    ["O", "H", "Z", "K", "Z"]]
        ConnectedCells solution = new ConnectedCells(grid as String[][])

        when: "The largest section is searched"
        def result = solution.findLargestSection()

        then: "The result contains the largest possible group of connected cells"
        result.size() == 4
        and: "The cells are in the expected coordinates"
        def expected = [new ConnectedCells.Coordinate(0, 0), new ConnectedCells.Coordinate(1, 1),
                        new ConnectedCells.Coordinate(2, 2), new ConnectedCells.Coordinate(3, 3)]
        def resultsMap = result.stream().collect(Collectors.toMap(Function.identity(), Function.identity()))
        for (ConnectedCells.Coordinate coordinate : expected) {
            assert resultsMap.containsKey(coordinate)
        }
    }
}
