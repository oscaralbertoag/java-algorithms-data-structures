package com.oscar.challenge

import spock.lang.Specification
import spock.lang.Unroll

class StringPermutationsTest extends Specification {

    @Unroll
    def "findAllPermutations() generates the correct and complete list of permutations for #word"() {
        given:
        StringPermutations stringPermutations = new StringPermutations()

        when: "a seed word is provided"
        def results = stringPermutations.findAllPermutations(word)

        then: "all correct permutations are generated"
        expected.size() == results.size()
        results.containsAll(expected)

        where:
        expected                                                                 | word
        []                                                                       | null
        [""]                                                                     | ""
        ["a"]                                                                    | "a"
        ["ab", "ba"]                                                             | "ab"
        ["abc", "bac", "cab", "acb", "bca", "cba"]                               | "abc"
        ["ABCDE", "BACDE", "CABDE", "ACBDE", "BCADE", "CBADE", "DBACE",
         "BDACE", "ADBCE", "DABCE", "BADCE", "ABDCE", "ACDBE", "CADBE",
         "DACBE", "ADCBE", "CDABE", "DCABE", "DCBAE", "CDBAE", "BDCAE",
         "DBCAE", "CBDAE", "BCDAE", "ECDAB", "CEDAB", "DECAB", "EDCAB",
         "CDEAB", "DCEAB", "ACEDB", "CAEDB", "EACDB", "AECDB", "CEADB",
         "ECADB", "EDACB", "DEACB", "AEDCB", "EADCB", "DAECB", "ADECB",
         "ADCEB", "DACEB", "CADEB", "ACDEB", "DCAEB", "CDAEB", "BDAEC",
         "DBAEC", "ABDEC", "BADEC", "DABEC", "ADBEC", "EDBAC", "DEBAC",
         "BEDAC", "EBDAC", "DBEAC", "BDEAC", "BAEDC", "ABEDC", "EBADC",
         "BEADC", "AEBDC", "EABDC", "EADBC", "AEDBC", "DEABC", "EDABC",
         "ADEBC", "DAEBC", "CAEBD", "ACEBD", "ECABD", "CEABD", "AECBD",
         "EACBD", "BACED", "ABCED", "CBAED", "BCAED", "ACBED", "CABED",
         "CEBAD", "ECBAD", "BCEAD", "CBEAD", "EBCAD", "BECAD", "BEACD",
         "EBACD", "ABECD", "BAECD", "EABCD", "AEBCD", "DEBCA", "EDBCA",
         "BDECA", "DBECA", "EBDCA", "BEDCA", "CEDBA", "ECDBA", "DCEBA",
         "CDEBA", "EDCBA", "DECBA", "DBCEA", "BDCEA", "CDBEA", "DCBEA",
         "BCDEA", "CBDEA", "CBEDA", "BCEDA", "ECBDA", "CEBDA", "BECDA", "EBCDA"] | "ABCDE"
    }
}
