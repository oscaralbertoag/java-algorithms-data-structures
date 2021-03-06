package com.oscar.challenge

import spock.lang.Specification
import spock.lang.Unroll

class BalancedParenthesisTest extends Specification {

    @Unroll
    def "isBalanced returns '#expected' for #expression"() {
        given:
        BalancedParenthesis balancedParenthesis = new BalancedParenthesis()

        when: "an expression is processed"
        def result = balancedParenthesis.isBalanced(expression)

        then: "the result matches the expected behavior"
        result == expected

        where:
        expression                                               | expected
        ""                                                       | false
        null                                                     | false
        "()"                                                     | true
        "[]"                                                     | true
        "{}"                                                     | true
        "("                                                      | false
        ")"                                                      | false
        "["                                                      | false
        "]"                                                      | false
        "{"                                                      | false
        "}"                                                      | false
        "{[()]}"                                                 | true
        "({[]})"                                                 | true
        "[({})]"                                                 | true
        "(()()()())"                                             | true
        "[()()(){}]"                                             | true
        "{{}()[]{}(()){{}}}"                                     | true
        "{{}()[]{}(()){{}}"                                      | false
        "((((()))))"                                             | true
        "{{{{{}}}}}"                                             | true
        "[[[[[]]]]]"                                             | true
        "[[[]]"                                                  | false
        "(()))"                                                  | false
        "(()()(()"                                               | false
        "{}}}}"                                                  | false
        "{}()}}{{()}{()}}{("                                     | false
        "[[[[[]]]]](()()()()){{}()[]{}(()){{}}}({[{{{{{}}}}}]})" | true
    }
}
