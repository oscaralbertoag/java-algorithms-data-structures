package com.oscar.challenge.linkedlistdups

import spock.lang.Specification
import spock.lang.Unroll

class RemoveLinkedListDuplicatesTest extends Specification {

    @Unroll
    def "removeLinkedListDuplicates() method correctly identifies and removes duplicates"() {
        given: "an existing linked list"
        RemoveLinkedListDuplicates solution = new RemoveLinkedListDuplicates()
        Node rootNode = solution.listAsNodes(initialNodesList)

        when: "duplicates are removed"
        def result = solution.removeLinkedListDuplicates(rootNode)

        then: "the resulting linked list looks as expected"
        expectedNodesList == solution.nodesAsList(result)

        where:
        initialNodesList                                                  | expectedNodesList
        ["a", "b", "c", "a", "a", "b", "c", "b"]                          | ["a", "b", "c"]
        ["a", "a"]                                                        | ["a"]
        ["b"]                                                             | ["b"]
        ["a", "b", "c"]                                                   | ["a", "b", "c"]
        ["a", "a", "a", "b", "a", "c", "a", "d", "d", "a", "a", "b"]      | ["a", "b", "c", "d"]
        ["a", "a", "a", "b", "a", "c", "a", "d", "d", "a", "a", "b", "e"] | ["a", "b", "c", "d", "e"]
        []                                                                | []
    }

    def "helper method nodesAsList() generates the expected nodes list"() {
        given: "a linked list"
        Node node1 = new Node("first")
        Node node2 = new Node("second")
        Node node3 = new Node("third")
        Node node4 = new Node("fourth")
        node1.setNext(node2)
        node2.setNext(node3)
        node3.setNext(node4)

        when: "a nodes list is generated"
        RemoveLinkedListDuplicates solution = new RemoveLinkedListDuplicates()
        def result = solution.nodesAsList(node1)

        then: "the list contains the expected nodes in the correct order"
        ["first", "second", "third", "fourth"] == result
    }

    def "helper method listAsNodes() generates the correct linked list"() {
        given: "a list of node values"
        def values = ["a", "b", "c"]

        when: "a link list is built from the list of values"
        RemoveLinkedListDuplicates solution = new RemoveLinkedListDuplicates()
        def result = solution.listAsNodes(values)

        then: "the resulting nodes contain the correct links and values"
        result.getData() == "a"
        result.getNext().getData() == "b"
        result.getNext().getNext().getData() == "c"
        result.getNext().getNext().getNext() == null
    }

}
