package vcs;

import utils.OperationType;
import java.util.ArrayList;

public class StatusOperation extends VcsOperation {

    public StatusOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Executes the status operation.
     *
     * @param vcs the active version control system
     * @return return code
     */
    public int execute(Vcs vcs) {

        vcs.getOutputWriter().write("On branch: " + vcs.getCurrentBranch().getName() + "\n");
        vcs.getOutputWriter().write("Staged changes:\n");
        for (String st : vcs.getCurrentBranch().getStaging().getStatus()) {
            vcs.getOutputWriter().write(st);
        }

        return 0;
    }

}
