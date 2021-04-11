package com.oscar.challenge.dependencygraph;

import java.util.List;

public class Project {

    private String project;
    private List<String> dependencies;

    public Project(String project, List<String> dependencies) {
        this.project = project;
        this.dependencies = dependencies;
    }

    public String getProject() {
        return project;
    }

    public Project setProject(String project) {
        this.project = project;
        return this;
    }

    public List<String> getDependencies() {
        return dependencies;
    }

    public Project setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
        return this;
    }
}

