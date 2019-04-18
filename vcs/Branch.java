package vcs;

import filesystem.FileSystemSnapshot;

import java.util.LinkedList;
import java.util.List;

public class Branch {

    private String name;
    private List<Commit> commits;
    private Staging staging;

    Branch(final String name, final FileSystemSnapshot snapshot) {
        this.name = name;
        this.commits = new LinkedList<>();
        this.staging = new Staging();
    }

    public final Staging getStaging() {
        return staging;
    }

    public final String getName() {
        return name;
    }

    public final List<Commit> getCommits() {
        return commits;
    }

}
