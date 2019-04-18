package vcs;

import utils.ErrorCodeManager;
import utils.IDGenerator;
import utils.OperationType;

import java.util.ArrayList;

public class CommitOperation extends VcsOperation {

    public CommitOperation(final OperationType type, final ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the commit operation.
     *
     * @param vcs the active version control system
     * @return return code
     */
    public int execute(final Vcs vcs) {

        if (vcs.getCurrentBranch().getStaging().isStagingEmpty()) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }

        // Parse commit comment:
        int i = 0;
        StringBuilder tag = new StringBuilder();
        for (String st : operationArgs) {
            i++;
            if (i <= 2) {
                continue;
            }
            tag.append(st).append(" ");
        }

        // Remove last space:
        tag = new StringBuilder(tag.substring(0, tag.length() - 1));

        vcs.getCurrentBranch().getCommits().add(new Commit(IDGenerator.generateCommitID(),
                tag.toString(), vcs.getActiveSnapshot().cloneFileSystem()));
        vcs.getCurrentBranch().getStaging().clearStatus();

        return 0;
    }
}
