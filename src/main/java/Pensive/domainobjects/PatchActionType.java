package Pensive.domainobjects;

import lombok.Getter;

public enum PatchActionType {
    ADD("add"),
    REMOVE("remove");

    @Getter
    private final String actionKey;

    private PatchActionType(String actionKey) {
        this.actionKey = actionKey;
    }
}
