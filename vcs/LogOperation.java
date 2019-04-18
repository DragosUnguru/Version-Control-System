package vcs;

import utils.OperationType;

import java.util.ArrayList;

public class LogOperation extends VcsOperation {

    public LogOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the log operation.
     *
     * @param vcs the active version control system
     * @return return code
     */
    public int execute(Vcs vcs) {

        for (Commit commit : vcs.getCurrentBranch().getCommits()) {
            vcs.getOutputWriter().write("Commit id: " + commit.getId() + "\n");

            // Parse last newline for output file:
            Commit lastCommit;
            lastCommit = vcs.getCurrentBranch().getCommits().get
                    (vcs.getCurrentBranch().getCommits().size() - 1);

            if (commit.getId() == lastCommit.getId()) {
                vcs.getOutputWriter().write("Message: " + commit.getTag() + "\n");
            } else {
                vcs.getOutputWriter().write("Message: " + commit.getTag() + "\n\n");
            }
        }

        return 0;
    }
}
