package vcs;

import filesystem.FileSystemSnapshot;

public class Commit {

    private int id;
    private String tag;
    private FileSystemSnapshot snapshot;

    Commit(final int id, final String tag, final FileSystemSnapshot snapshot) {
        this.id = id;
        this.tag = tag;
        this.snapshot = snapshot;
    }

    public final int getId() {
        return id;
    }

    public final String getTag() {
        return tag;
    }

    public final void setSnapshot(final FileSystemSnapshot snapshot) {
        this.snapshot = snapshot;
    }

    public final FileSystemSnapshot getSnapshot() {
        return snapshot;
    }

}
