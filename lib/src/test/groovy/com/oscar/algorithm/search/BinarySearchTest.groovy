package com.oscar.algorithm.search

import spock.lang.Specification
import spock.lang.Unroll

class BinarySearchTest extends Specification {

    @Unroll
    def "binary search finds the expected index of a match (to find:#toFind) when available"() {
        given:
        BinarySearch binarySearch = new BinarySearch()

        when: "a search is performed"
        def result = binarySearch.find(numbers, toFind)

        then: "the match index is returned"
        result == expected

        where:
        expected         | numbers                   | toFind
        Optional.empty() | [1, 43, 2, 5, 32, 6]      | 10
        Optional.empty() | []                        | 10
        Optional.empty() | null                      | 10
        Optional.of(7)   | [7]                       | 7
        Optional.of(15)  | [7, 15]                   | 15
        Optional.of(1)   | [1, 43, 2, 5, 32, 6, 100] | 1
        Optional.of(43)  | [1, 43, 2, 5, 32, 6]      | 43
        Optional.of(2)   | [1, 43, 2, 5, 32, 6, 45]  | 2
        Optional.of(5)   | [1, 43, 2, 5, 32, 6, 56]  | 5
        Optional.of(32)  | [1, 43, 2, 5, 32, 6]      | 32
        Optional.of(6)   | [1, 43, 2, 5, 32, 6]      | 6
        Optional.of(5)   | [1, 43, 2, 5, 32, 6, 5]   | 5
        Optional.of(5)   | [5, 5, 5, 1, 5]           | 5
    }
}
