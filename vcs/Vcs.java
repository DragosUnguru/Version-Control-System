package vcs;

import filesystem.FileSystemOperation;
import filesystem.FileSystemSnapshot;
import utils.IDGenerator;
import utils.OutputWriter;
import utils.Visitor;

import java.util.ArrayList;
import java.util.List;

public final class Vcs implements Visitor {
    private final OutputWriter outputWriter;
    private FileSystemSnapshot activeSnapshot;
    private List<Branch> branches;
    private Branch currentBranch;

    /**
     * Vcs constructor.
     *this.name + "\n"
     * @param outputWriter the output writer
     */
    public Vcs(OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
        branches = new ArrayList<>();
    }

    /**
     * Does initialisations.
     */
    public void init() {
        this.activeSnapshot = new FileSystemSnapshot(outputWriter);

        // Initialise "master" branch and first commit:
        Branch masterBranch = new Branch("master", new FileSystemSnapshot(outputWriter));
        this.branches.add(masterBranch);
        this.currentBranch = masterBranch;
        this.getCurrentBranch().getCommits().add(new Commit(IDGenerator.generateCommitID(),
                                            "First commit", new FileSystemSnapshot(outputWriter)));
    }

    /**
     * Visits a file system operation.
     *
     * @param fileSystemOperation the file system operation
     * @return the return code
     */
    public int visit(FileSystemOperation fileSystemOperation) {
        return fileSystemOperation.execute(this.activeSnapshot);
    }

    /**
     * Visits a vcs operation.
     *
     * @param vcsOperation the vcs operation
     * @return return code
     */
    @Override
    public int visit(VcsOperation vcsOperation) {
        return  vcsOperation.execute(this);
    }

    public FileSystemSnapshot getActiveSnapshot() {
        return activeSnapshot;
    }

    public void setActiveSnapshot(FileSystemSnapshot activeSnapshot) {
        this.activeSnapshot = activeSnapshot;
    }

    public OutputWriter getOutputWriter() {
        return outputWriter;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public Branch getCurrentBranch() {
        return currentBranch;
    }

    public void setCurrentBranch(Branch currentBranch) {
        this.currentBranch = currentBranch;
    }

    public Branch getBranch(String branchName) {
        for (Branch branch : this.branches) {
            if (branch.getName().equals(branchName)) {
                return branch;
            }
        }
        return null;
    }
}
