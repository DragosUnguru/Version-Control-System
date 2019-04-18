package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

public class BranchOperation extends VcsOperation {

    public BranchOperation(final OperationType type, final ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the branch operation.
     *
     * @param vcs the active version control system
     * @return return code
     */
    public int execute(final Vcs vcs) {

        String branchName = this.operationArgs.get(1);

        if (vcs.getBranch(branchName) != null) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }

        vcs.getBranches().add(new Branch(branchName, vcs.getActiveSnapshot()));

        return 0;
    }
}
