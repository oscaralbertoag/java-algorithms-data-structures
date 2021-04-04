package com.oscar.datastructure.heap

import spock.lang.Specification
import spock.lang.Unroll

class MinHeapTest extends Specification {

    @Unroll
    def "min heap adds nodes successfully and builds a correct min heap tree"() {
        given:
        MinHeap minHeap = new MinHeap()

        when: "a series of insertions are performed"
        for (int n : toInsert) {
            minHeap.add(n)
        }
        def result = minHeap.getOrderedNodes()
        def resultSize = minHeap.getSize()

        then: "The resulting min heap tree has the correct structure"
        expected == result

        and: "The min heap tree is of the expected size"
        expectedSize == resultSize

        where:
        expected                | toInsert                | expectedSize
        [1]                     | [1]                     | 1
        [1, 2, 3, 4, 5, 6]      | [1, 2, 3, 4, 5, 6]      | 6
        [0, 4, 1, 10, 5, 11, 3] | [10, 4, 11, 3, 5, 1, 0] | 7
        [-2, 1, 5, 9, 4, 7, 6]  | [9, 4, 7, 1, -2, 6, 5]  | 7
        [-100, 2, 1, 4, 5, 3]   | [1, 2, 3, 4, 5, -100]   | 6
        [1, 1, 1, 1, 1]         | [1, 1, 1, 1, 1]         | 5
    }

    @Unroll
    def "min heap poll() works as expected and the tree preserves its min heap property"() {
        given: "an  existing min heap with elements"
        MinHeap minHeap = new MinHeap()
        for (int n : toInsert) {
            minHeap.add(n)
        }

        when: "a series of deletes are executed"
        def pollResults = []
        for (int i = 0; i < numberOfDeletes; i++) {
            pollResults.add(minHeap.poll())
        }

        then: "the resulting heap is of the expected size"
        expectedSize == minHeap.getSize()

        and: "all polled items are present and in the expected order"
        expectedPolled == pollResults

        and: "the remaining nodes are in the expected order"
        expectedRemaining == minHeap.getOrderedNodes()

        where:
        expectedPolled         | toInsert               | numberOfDeletes | expectedSize | expectedRemaining
        [1]                    | [1, 2, 3, 4]           | 1               | 3            | [2, 4, 3]
        [1, 2]                 | [1, 2, 3, 4]           | 2               | 2            | [3, 4]
        [1, 2, 3]              | [1, 2, 3, 4]           | 3               | 1            | [4]
        [1, 2, 3, 4]           | [1, 2, 3, 4]           | 4               | 0            | []
        [-2, 1, 4, 5, 6, 7, 9] | [9, 4, 7, 1, -2, 6, 5] | 7               | 0            | []
        [2, 2, 2, 2, 2, 2]     | [2, 2, 2, 2, 2, 2]     | 6               | 0            | []
        [2, 2, 2]              | [2, 2, 2, 2, 2, 2]     | 3               | 3            | [2, 2, 2]

    }

    def "peek() throws exception for empty tree"() {
        given: "an empty heap tree"
        MinHeap minHeap = new MinHeap()

        when: "peek is called"
        minHeap.peek()

        then: "an exception is thrown"
        IllegalStateException exception = thrown()
        exception.message == "Heap is currently empty"
    }

    def "peek() is idempotent"() {
        given: "a min heap tree with some elements"
        MinHeap minHeap = new MinHeap()
        for (int num : [9, 4, 7, 1, -2, 6, 5]) {
            minHeap.add(num)
        }

        when: "several peek invocations take place"
        def result
        for (int i = 0; i < 10; i++) {
            result = minHeap.peek()
        }

        then: "the result is the expected value"
        result == -2

        and: "the min heap maintains its structure"
        minHeap.getOrderedNodes() == [-2, 1, 5, 9, 4, 7, 6]

        and: "the size remains unchanged"
        minHeap.getSize() == 7
    }

    def "min heap capacity grows as needed"() {
        given: "a heap tree with an initial capacity"
        MinHeap minHeap = new MinHeap(initialCapacity)

        when: "several inserts take place (beyond initial capacity)"
        for (int num : [9, 4, 7, 1, -2, 6, 5]) {
            minHeap.add(num)
        }

        then: "the heap tree automatically augments its capacity"
        minHeap.getSize() == 7

        and: "the structure is as expected"
        minHeap.getOrderedNodes() == [-2, 1, 5, 9, 4, 7, 6]

        where:
        initialCapacity | _
        1               | _
        2               | _
        3               | _
        4               | _
        5               | _
        6               | _
        7               | _
    }
}
