package com.oscar.challenge.runningmedian

import spock.lang.Specification
import spock.lang.Unroll

class RunningMedianTest extends Specification {

    @Unroll
    def "getRunningMedian() method returns the correct median at all times"() {
        given: "an instance of the solution class"
        RunningMedian runningMedian = new RunningMedian()

        when: "all expected medians are calculated correctly"
        runningMedian.getRunningMedian() == 0.0d

        then:
        for (int i = 0; i < expected.size(); i++) {
            runningMedian.add(numbers[i])
            assert runningMedian.getRunningMedian() == expected[i]
        }

        where:
        expected                                         | numbers
        [4.0d, 5.0d, 4.0d, 3.0d, 4.0d]                   | [4, 6, 1, 2, 7]
        [7.0d, 9.5d, 7.0d, 5.0d, 4.0d, 5.5d, 7.0d, 9.5d] | [7, 12, 1, 3, 4, 20, 15, 16]
        [6.0d, 9.0d, 6.0d, 5.5d, 5.0d, 5.5d, 6.0d]       | [6, 12, 4, 5, 3, 8, 7]
    }
}
