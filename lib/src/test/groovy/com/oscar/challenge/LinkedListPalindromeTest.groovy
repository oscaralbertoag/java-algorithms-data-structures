package com.oscar.challenge

import com.oscar.datastructure.linkedlist.single.LinkedList
import spock.lang.Specification
import spock.lang.Unroll

class LinkedListPalindromeTest extends Specification {

    @Unroll
    def "isPalindrome() correctly differentiates between valid and invalid palindromes"() {
        given: "an existing linked list"
        LinkedList<Character> linkedList = buildLinkedList(sourceList as List<Character>)

        when: "the linked list is checked to verify as a palindrome"
        def result = LinkedListPalindrome.isPalindrome(linkedList)

        then: 'the expected result is returned'
        expected == result

        where:
        expected | sourceList
        true     | ['a' as char, 'b' as char, 'b' as char, 'a' as char]
        true     | ['a' as char, 'b' as char, 'c' as char, 'b' as char, 'a' as char]
        true     | ['a' as char]
        true     | ['a' as char, 'a' as char]
        false    | ['a' as char, 'b' as char]
        false    | ['a' as char, 'a' as char, 'c' as char, 'a' as char, 'a' as char, 'a' as char]
        false    | ['a' as char, 'a' as char, 'b' as char, 'c' as char, 'a' as char]
    }

    static LinkedList<Character> buildLinkedList(List<Character> list) {
        LinkedList<Character> linkedList = new LinkedList<>()
        list.forEach({ element -> linkedList.add(element) })
        return linkedList
    }
}
