package com.oscar.datastructure.linkedlist.single

import spock.lang.Specification
import spock.lang.Unroll

class LinkedListTest extends Specification {

    @Unroll
    def "linked list is built correctly"() {
        when: "a linked list is built"
        LinkedList<?> linkedList = buildLinkedList(sourceList)
        and: "its nodes are converted to a list of strings"
        List<?> result = linkedList.asList()

        then: "the resulting list matches the expected values"
        sourceList == result

        where:
        sourceList                                  | _
        ["Hi", "Hello", "hola", "Bonjour", "Hallo"] | _
        ["One", "Two"]                              | _
        ["First"]                                   | _
        [1, 2, 3, 4]                                | _
        []                                          | _
    }

    @Unroll
    def "find() method returns expected result - value is found"() {
        given: "an existing linked list"
        def linkedList = buildLinkedList(sourceList)

        when: "a search is performed"
        Optional result = linkedList.find(toFind)

        then: "the expected result is returned"
        result.isPresent()
        expectedData == result.get().getData()

        where:
        sourceList      | toFind | expectedData
        [1, 2, 3, 4]    | 3      | 3
        ["a", "b", "c"] | "c"    | "c"
        [1.2, 3.4, 5.6] | 1.2    | 1.2
        ["HI"]          | "HI"   | "HI"
    }

    @Unroll
    def "find() method returns empty optional when value is not present"() {
        given: "an existing linked list"
        def linkedList = buildLinkedList(sourceList)

        when: "a search is performed for an item that isn't present in the linked list"
        Optional result = linkedList.find(toFind)

        then: "an empty optional is returned"
        result.isEmpty()

        where:
        sourceList      | toFind
        [1, 2, 3, 4]    | 5
        []              | 1
        ["A", "B", "C"] | "c"
    }

    @Unroll
    def "remove() method returns expected node and removes it from the list"() {
        given: "an existing linked list"
        def linkedList = buildLinkedList(sourceList)

        when: "a node is removed"
        Optional<?> result = linkedList.remove(toRemove)

        then: "the expected node is returned"
        result.isPresent()
        result.get().getData() == expected

        and: "the list doesn't contain the removed node anymore"
        expectedRemainingList == linkedList.asList()

        where:
        sourceList   | toRemove | expected | expectedRemainingList
        [1, 2, 3, 4] | 3        | 3        | [1, 2, 4]
        [1, 2, 3, 4] | 2        | 2        | [1, 3, 4]
        [1, 2, 3, 4] | 1        | 1        | [2, 3, 4]
        [1, 2, 3, 4] | 4        | 4        | [1, 2, 3]
        [1]          | 1        | 1        | []
    }

    @Unroll
    def "remove() method returns empty optional when value is not in linked list"() {
        given: "an existing linked list"
        def linkedList = buildLinkedList(sourceList)

        when: "a node removal is attempted for a node that is NOT in the linked list"
        Optional<?> result = linkedList.remove(toRemove)

        then: "an empty optional is returned"
        result.isEmpty()

        and: "the linked list remains unchanged"
        linkedList.asList() == sourceList

        where:
        sourceList   | toRemove
        [1, 2, 3, 4] | 5
        []           | 1
    }

    def "remove() and add() operations don't affect each other"() {
        given: "an existing linked list"
        def linkedList = buildLinkedList([1, 2, 3, 4, 5])

        when: "the last node is removed"
        linkedList.remove(5)

        and: "new nodes are added"
        linkedList.add(7)
        linkedList.add(8)

        and: "more middle nodes are removed"
        linkedList.remove(3)
        linkedList.remove(7)

        and: "more nodes are appended"
        linkedList.add(9)

        and: "the root node is removed"
        linkedList.remove(1)

        then: "the resulting list contains the expected elements"
        linkedList.asList() == [2, 4, 8, 9]
    }

    def "remove() second to last repeatedly has the expected outcome"() {
        given: "an existing linked list"
        def linkedList = buildLinkedList([1, 2, 3, 4, 5, 6, 7])

        when: "several nodes are removed (second to last)"
        linkedList.remove(6)
        linkedList.remove(5)
        linkedList.remove(4)
        linkedList.add(10)

        then: "the resulting list contains the expected elements"
        linkedList.asList() == [1, 2, 3, 7, 10]
    }

    def "remove() last repeatedly hast the expected outcome"() {
        given: "an existing linked list"
        def linkedList = buildLinkedList([1, 2, 3, 4, 5, 6, 7])

        when: "several nodes are removed from the tail of the list"
        linkedList.remove(7)
        linkedList.remove(6)
        linkedList.remove(5)
        linkedList.add(10)

        then: "the resulting list contains the expected elements"
        linkedList.asList() == [1, 2, 3, 4, 10]
    }

    def "remove() until empty has the expected outcome"() {
        given: "an existing linked list"
        def linkedList = buildLinkedList([1, 2, 3])

        when: "all nodes are removed"
        linkedList.remove(1)
        linkedList.remove(2)
        linkedList.remove(3)
        linkedList.add(10)

        then: "the expected nodes are in the list"
        linkedList.asList() == [10]
    }

    static LinkedList<?> buildLinkedList(List<?> list) {
        LinkedList<?> linkedList = new LinkedList<>()
        list.forEach({ word -> linkedList.add(word) })
        return linkedList
    }
}
