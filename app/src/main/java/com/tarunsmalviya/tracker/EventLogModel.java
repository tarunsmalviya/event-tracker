package com.tarunsmalviya.tracker;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class EventLogModel extends RealmObject {

    @PrimaryKey
    private long timestamp;

    private int type;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
