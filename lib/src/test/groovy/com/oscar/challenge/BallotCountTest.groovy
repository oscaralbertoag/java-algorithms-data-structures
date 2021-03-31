package com.oscar.challenge

import spock.lang.Specification
import spock.lang.Unroll

class BallotCountTest extends Specification {

    @Unroll
    def "calculateMajorityWinner() correctly finds the majority winner in all ballots"() {
        given:
        BallotCount solution = new BallotCount()

        when:
        def result = solution.calculateMajorityWinner(ballots)

        then:
        result == expected

        where:
        expected                       | ballots
        "A"                            | [["A", "B", "C"]: 1L]
        "B"                            | [["C", "B", "A"]: 4L, ["B", "A", "C"]: 10L]
        "C"                            | [["C", "B", "A"]: 4L, ["B", "A", "C"]: 3L]
        BallotCount.NO_MAJORITY_WINNER | [["C", "B", "A"]: 20L, ["B", "A", "C"]: 20L]
        BallotCount.NO_MAJORITY_WINNER | [["C", "B", "A"]: 10L, ["B", "A", "C"]: 10L, ["A", "B", "C"]: 10L]
        BallotCount.NO_MAJORITY_WINNER | [["C", "B", "A"]: 10L, ["B", "A", "C"]: 10L, ["A", "B", "C"]: 5L]
    }

}
