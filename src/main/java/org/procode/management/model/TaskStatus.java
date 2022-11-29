package org.procode.management.model;

public enum TaskStatus {

    NEW_TASK("new task"), BUG("bug"), IN_PROCESS("in process"),
    RE_OPEN("reopen") , RESOLVED("resolved") , DONE("done");

    private final String displayName;

    TaskStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
