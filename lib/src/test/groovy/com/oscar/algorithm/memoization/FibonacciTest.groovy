package com.oscar.algorithm.memoization

import spock.lang.Specification
import spock.lang.Unroll

class FibonacciTest extends Specification {

    @Unroll
    def "fibonacci method returns #expected for #n"() {
        given:
        Fibonacci fibonacci = new Fibonacci()

        when: "a fibonacci number is calculated"
        def result = fibonacci.fibonacci(n)

        then: "the correct result is returned"
        result == expected

        where:
        expected | n
        0        | 0
        1        | 1
        1        | 2
        2        | 3
        3        | 4
        5        | 5
        8        | 6
        13       | 7
        21       | 8
        // Uncomment to see that this takes much longer to calculate without memoization: 12586269025 | 50
    }

    def "memoized fibonacci method returns #expected for #n"() {
        given:
        Fibonacci fibonacci = new Fibonacci()

        when: "a fibonacci number is calculated"
        def result = fibonacci.memoizedFibonacci(n, new HashMap<Integer, Integer>())

        then: "the correct result is returned"
        result == expected

        where:
        expected    | n
        0           | 0
        1           | 1
        1           | 2
        2           | 3
        3           | 4
        5           | 5
        8           | 6
        13          | 7
        21          | 8
        12586269025 | 50
    }
}
