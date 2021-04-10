package com.oscar.challenge

import com.oscar.datastructure.linkedlist.single.Node
import spock.lang.Specification

class ReverseLinkedListTest extends Specification {

    def "reverseLinkedList() method returns null for null input"() {
        given: "a null linked list node"
        Node<Integer> root = null

        when: "the linked list is reversed"
        def result = ReverseLinkedList.reverseLinkedList(root)

        then: "no exceptions are thrown and a null reference is returned"
        result == null
    }

    def "reverseLinkedList() method returns a linked list that has been reverted correctly"() {
        given: "an existing linked list of integers"
        Node<Integer> root = new Node<>(1)
        root.setNext(new Node<>(2).setNext(new Node<>(3).setNext(new Node<>(4))))

        when: "the linked list is reversed"
        def result = ReverseLinkedList.reverseLinkedList(root)

        then: "the linked list has been reversed correctly"
        result != null
        result.getData() == 4
        result.getNext().getData() == 3
        result.getNext().getNext().getData() == 2
        result.getNext().getNext().getNext().getData() == 1
        result.getNext().getNext().getNext().getNext() == null
    }


    def "reverseLinkedList() method returns a linked list that has been reverted correctly for one node only"() {
        given: "an existing linked list of integers and size 1"
        Node<Integer> root = new Node<>(1)

        when: "the linked list is reversed"
        def result = ReverseLinkedList.reverseLinkedList(root)

        then: "the linked list has been reversed correctly"
        result != null
        result.getData() == 1
        result.getNext() == null
    }
}
