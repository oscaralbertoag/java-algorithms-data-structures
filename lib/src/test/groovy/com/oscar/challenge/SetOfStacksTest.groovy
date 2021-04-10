package com.oscar.challenge

import spock.lang.Specification

class SetOfStacksTest extends Specification {

    def "push() operation appends an item onto the stack and pop() operation removes them in the expected order"() {
        given: "an a list of integers to push onto a stack"
        def toPush = [1, 2, 3, 4, 5]

        and: "an instance of the solution class"
        SetOfStacks stack = new SetOfStacks(2)

        when: "several push() operations are performed"
        for (int integer : toPush) {
            stack.push(integer)
            assert stack.getSize() == integer
        }

        then: "the correct numbers are retrieved when popped off the stack"
        def resultsList = []
        for (int i = 0; i < 5; i++) {
            assert stack.getSize() == 5 - i
            def maybeCurrent = stack.pop()
            assert maybeCurrent.isPresent()
            resultsList.add(maybeCurrent.get())
        }
        resultsList == [5, 4, 3, 2, 1]

        and: "the 6th pop() operation returns an empty optional"
        assert stack.pop().isEmpty()
        stack.getSize() == 0
    }

    def "pop() operation on an empty stack returns an empty optional"() {
        given: "an empty stack"
        SetOfStacks stack = new SetOfStacks(3)

        when: "a pop() operation is performed"
        Optional result = stack.pop()

        then: "the resulting optional is empty"
        result.isEmpty()

        and: "the size remains 0"
        stack.getSize() == 0
    }
}
