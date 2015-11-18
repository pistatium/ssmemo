package com.appspot.pistatium.ssmemo.models;

/**
 * Created by kimihiro on 15/11/17.
 */
enum Status {
    TMP_DELETED(0),
    ACTIVE(1),
    ;

    public int value;
    Status(int i) {
        this.value = i;
    }
}
