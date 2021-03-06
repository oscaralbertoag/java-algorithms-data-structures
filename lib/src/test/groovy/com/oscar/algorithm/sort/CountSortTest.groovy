package com.oscar.algorithm.sort

import spock.lang.Specification
import spock.lang.Unroll

class CountSortTest extends Specification {

    @Unroll
    def "sort method returns #expected for #original"() {
        given:
        CountSort countSort = new CountSort()

        when: "a list is sorted by using the count sort algorithm"
        def result = countSort.sort(original, rangeUpperLimit)

        then: "all list items are correctly sorted"
        expected == result

        where:
        expected                                      | original                                      | rangeUpperLimit
        []                                            | []                                            | 0
        [3]                                           | [3]                                           | 1
        [1, 2, 3, 4, 5]                               | [3, 4, 2, 1, 5]                               | 10
        [0, 2, 4, 6, 8, 10]                           | [10, 8, 6, 4, 2, 0]                           | 11
        [1, 35, 66, 120, 200]                         | [120, 1, 35, 200, 66]                         | 250
        [100, 200, 300, 400, 500, 600, 700, 800, 900] | [100, 200, 300, 400, 500, 600, 700, 800, 900] | 901
        [100, 200, 300, 400, 500, 600, 700, 800, 900] | [100, 200, 900, 400, 500, 600, 700, 800, 300] | 901
        [0, 0, 0, 0, 5, 7, 10]                        | [0, 5, 0, 7, 0, 10, 0]                        | 11
        [0, 0, 2, 36, 36, 70, 88, 90]                 | [36, 2, 36, 70, 88, 0, 90, 0]                 | 100
    }
}
