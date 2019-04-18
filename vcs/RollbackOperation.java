package vcs;

import utils.OperationType;

import java.util.ArrayList;

public class RollbackOperation extends VcsOperation {

    public RollbackOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the rollback operation.
     *
     * @param vcs the active version control system
     * @return return code
     */
    public int execute(Vcs vcs) {

        int index = vcs.getCurrentBranch().getCommits().size() - 1;

        // Get back to last commit:
        vcs.setActiveSnapshot(vcs.getCurrentBranch().getCommits().get(index).getSnapshot());

        // Clear staging:
        vcs.getCurrentBranch().getStaging().clearStatus();

        return 0;
    }
}
