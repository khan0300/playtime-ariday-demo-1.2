package com.algonquincollege.ari.playtimearidaydemo.data;

/**
 * Created by Cong on 8/14/2015.
 *
 * Object allowing JSON conversion and database Serialization.
 *
 * TODO
 * Not complete. Requires table
 */
public abstract class SerializableEntity implements JSONable {

    private int serialId;
    public int getSerialId() { return serialId; }

    /**
     * Constructor.
     */
    protected SerializableEntity() {
        serialId = -1;
    }

    public abstract String toJSONString();

}//end class SerializableEntity
