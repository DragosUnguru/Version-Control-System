package vcs;

import filesystem.FileSystemSnapshot;
import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

public class InvalidVcsOperation extends VcsOperation {

    public InvalidVcsOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /**
     * Execute the invalid file system operation.
     *
     * @param vcs the active version control system
     * @return return code
     */
    public int execute(Vcs vcs) {
        return ErrorCodeManager.VCS_BAD_CMD_CODE;
    }
}
