package vcs;

import filesystem.FileSystemSnapshot;
import utils.AbstractOperation;

import java.util.LinkedList;
import java.util.List;

public class Staging {

    private List<String> status;

    public Staging() {
        status = new LinkedList<>();
    }


    public final List<String> getStatus() {
        return status;
    }

    public final void clearStatus() {
        this.status.clear();
    }

    public final boolean isStagingEmpty() {
        return this.status.isEmpty();
    }

    public final void addStatus(AbstractOperation abstractOperation) {
        String toBeAdded = "\t";
        switch (abstractOperation.getType()) {
            case TOUCH:
                toBeAdded += "Created file "
                        + abstractOperation.getOperationArgs().get(1) + "\n";
                break;
            case MAKEDIR:
                toBeAdded += "Created directory "
                        + abstractOperation.getOperationArgs().get(1) + "\n";
                break;
            case REMOVE:
                toBeAdded += "Removed "
                        + abstractOperation.getOperationArgs().get(1) + "\n";
                break;
            case WRITETOFILE:
                toBeAdded += "Added \""
                        + abstractOperation.getOperationArgs().get(1)
                        + "\" to file " + abstractOperation.getOperationArgs().get(0) + "\n";
                break;
             default:
                 toBeAdded += "Changed directory to "
                         + abstractOperation.getOperationArgs().get(0) + "\n";
        }

        this.status.add(toBeAdded);
    }
}
