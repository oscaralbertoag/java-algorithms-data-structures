package com.oscar.challenge


import spock.lang.Specification
import spock.lang.Unroll

import static com.oscar.challenge.IsBinaryTree.*

class IsBinaryTreeTest extends Specification {

    @Unroll
    def "solution identifies valid binary trees"() {
        given:
        IsBinaryTree solution = new IsBinaryTree()

        when: "a valid binary tree node is inspected"
        def result = solution.isBinaryTree(rootNode)

        then: "it is recognized as a binary tree"
        result

        where:
        rootNode                                                                      | _
        new Node(10)                                                                  | _
        new Node(3).setLeft(new Node(2)).setRight(new Node(4))                        | _
        new Node(3).setLeft(new Node(3)).setRight(new Node(4))                        | _
        new Node(3).setLeft(new Node(3)).setRight(new Node(4))                        | _
        new Node(3).setLeft(new Node(3)).setLeft(new Node(3))                         | _
        new Node(3).setLeft(new Node(2)).setLeft(new Node(1))                         | _
        new Node(3).setRight(new Node(4)).setRight(new Node(5))                       | _
        new Node(7).setLeft(new Node(5).setLeft(new Node(3)).setRight(new Node(6)))
                .setRight(new Node(9).setLeft(new Node(8)).setRight(new Node(10)))    | _
        new Node(7).setLeft(new Node(5).setLeft(new Node(3)).setRight(new Node(6)))   | _
        new Node(7).setRight(new Node(9).setLeft(new Node(8)).setRight(new Node(10))) | _
    }

    @Unroll
    def "solution identifies invalid binary search trees"() {
        given:
        IsBinaryTree solution = new IsBinaryTree()

        when: "an invalid binary search tree is inspected"
        def result = solution.isBinaryTree(rootNode)

        then: "it is correctly identified as invalid"
        !result

        where:
        rootNode                                                                                          | _
        new Node(7).setLeft(new Node(8))                                                                  | _
        new Node(7).setRight(new Node(3))                                                                 | _
        null                                                                                              | _
        new Node(7).setLeft(new Node(5).setLeft(new Node(4)).setRight(new Node(6))).setRight(new Node(6)) | _
        new Node(7).setRight(new Node(10).setLeft(new Node(9)).setRight(new Node(11))).
                setLeft(new Node(5).setRight(new Node(4)))                                                | _
    }
}
