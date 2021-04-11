package com.oscar.challenge.dependencygraph

import spock.lang.Specification

class DependencyGraphTest extends Specification {

    def "generateBuildOrder() throws an exception when it finds a cycle"() {
        given: "a list of projects"
        def projects = ["a", "b", "c", "d", "e", "f", "g"]

        and: "a list of dependencies"
        def dependencies = [["b", "a"], ["a", "b"], ["g", "c"], ["d", "c"], ["f", "c"], ["e", "d"], ["f", "e"]]

        and: "an instance of a dependency graph"
        DependencyGraph dependencyGraph = new DependencyGraph(projects, dependencies)

        when: "a build order list is generated"
        dependencyGraph.generateBuildOrder()

        then: "an exception is thrown"
        def exception = thrown(IllegalStateException)

        and: "the exception contains the expected message"
        exception.getMessage() == "Found a dependency cycle. Project can't be built"
    }

    def "generateBuildOrder() returns a valid dependency build order list"() {
        given: "a list of projects"
        def projects = ["a", "b", "c", "d", "e", "f"]

        and: "a list of dependencies"
        def dependencies = [["b", "a"], ["c", "a"], ["f", "b"], ["d", "c"], ["c", "f"]]

        and: "an instance of a dependency graph"
        DependencyGraph dependencyGraph = new DependencyGraph(projects, dependencies)

        when: "a build order list is generated"
        def result = dependencyGraph.generateBuildOrder()

        then: "the build order is the expected list"
        ["d", "e", "c", "f", "b", "a"] == result || ["e", "d", "c", "f", "b", "a"] == result || ["d", "c", "e", "f", "b", "a"]
    }
}
