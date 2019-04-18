package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

public class CheckoutOperation extends VcsOperation {

    public CheckoutOperation(final OperationType type, final ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the checkout operation.
     *
     * @param vcs the active version control system
     * @return return code
     */
    public int execute(final Vcs vcs) {
        if (!vcs.getCurrentBranch().getStaging().isStagingEmpty()) {
            return ErrorCodeManager.VCS_STAGED_OP_CODE;
        }

        if (this.getOperationArgs().get(1).equals("-c")) {
            return checkoutCommit(vcs);
        }

        return checkoutBranch(vcs);
    }

    //  checkout -c <commitID>:
    private int checkoutCommit(final Vcs vcs) {
        int commitID = Integer.parseInt(operationArgs.get(2));
        int rollbackIndex = 0;
        boolean foundID = false;

        for (Commit commit : vcs.getCurrentBranch().getCommits()) {
            if (commit.getId() == commitID) {
                vcs.setActiveSnapshot(commit.getSnapshot());
                foundID = true;
                break;
            }
            rollbackIndex++;
        }

        if (!foundID) {
            return ErrorCodeManager.VCS_BAD_PATH_CODE;
        }

        vcs.getCurrentBranch().getCommits().subList(rollbackIndex,
                vcs.getCurrentBranch().getCommits().size()).clear();

        return 0;
    }

    //  checkout <branchName>:
    private int checkoutBranch(final Vcs vcs) {

        String argsBranch = operationArgs.get(1);
        boolean foundBranch = false;

        for (Branch branch : vcs.getBranches()) {
            if (branch.getName().equals(argsBranch)) {
                vcs.setCurrentBranch(branch);
                foundBranch = true;
                break;
            }
        }

        if (!foundBranch) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }

        return 0;
    }
}
