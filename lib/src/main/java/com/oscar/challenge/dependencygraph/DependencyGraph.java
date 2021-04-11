package com.oscar.challenge.dependencygraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DependencyGraph {

    public static final int EMPTY = -1;
    private final Map<String, List<String>> projectToDependencies;
    private final List<Project> projects = new ArrayList<>();

    public DependencyGraph(List<String> projects, List<List<String>> dependencyPairs) {
        projectToDependencies = new HashMap<>();
        projects.forEach(project -> {
            projectToDependencies.put(project, new ArrayList<>());
        });

        dependencyPairs.forEach(dependencyPair -> {
            String project = dependencyPair.get(1);
            String dependency = dependencyPair.get(0);

            List<String> currentProjectDependencies = projectToDependencies.get(project);
            currentProjectDependencies.add(dependency);
        });

        projectToDependencies.forEach((key, value) -> this.projects.add(new Project(key, value)));
    }

    /**
     * This method will generate a valid build order for all provided project dependencies
     *
     * @return a list containing a valid build order of all dependencies. Note that there can be many valid orders for
     * certain dependency graphs
     */
    public List<String> generateBuildOrder() {

        Map<String, Boolean> built = new HashMap<>();
        List<String> buildOrder = new ArrayList<>();

        int indexToRemove = EMPTY;
        while (!projects.isEmpty()) {

            for (int i = 0; i < projects.size(); i++) {
                Project project = projects.get(i);
                List<String> dependencies = project.getDependencies();
                // If current project has no dependencies or all dependencies have already been built
                if (dependencies.isEmpty() || dependencies.stream().allMatch(built::containsKey)) {
                    built.put(project.getProject(), true);
                    buildOrder.add(project.getProject());
                    indexToRemove = i;
                    break;
                }
            }

            if (indexToRemove != EMPTY) {
                projects.remove(indexToRemove);
                indexToRemove = EMPTY;
            } else {
                throw new IllegalStateException("Found a dependency cycle. Project can't be built");
            }

        }

        return buildOrder;
    }
}
