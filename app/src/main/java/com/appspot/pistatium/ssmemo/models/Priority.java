package com.appspot.pistatium.ssmemo.models;

/**
 * Created by kimihiro on 15/11/17.
 */
public enum Priority {
    LOW(0),
    HIGH(10),
    ;
    public final int value;

    Priority(int i) {
        this.value = i;
    }
}
