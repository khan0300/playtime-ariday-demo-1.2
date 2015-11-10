package com.algonquincollege.ari.playtimearidaydemo.data;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.sql.Date;

/**
 * Created by Cong on 8/16/2015.
 */
public class Challenge extends SerializableEntity {

    public static final Type TYPE_MIND      = new Type(Type.MIND);
    public static final Type TYPE_BODY      = new Type(Type.BODY);
    public static final Type TYPE_SPIRIT    = new Type(Type.SPIRIT);
    public static final Type TYPE_CUSTOM    = new Type(Type.CUSTOM);

    public static class Type extends StateProperty<Character> {
        public static final char MIND = 'm';
        public static final char BODY = 'b';
        public static final char SPIRIT = 's';
        public static final char CUSTOM = 'c';

        private static final Character[] states = {MIND, BODY, SPIRIT, CUSTOM};

        public Type(char initialState) {
            super(states, initialState);
        }
    }

    private User issuer;
    public User getIssuer() { return issuer; }
    protected void setIssuer(User issuer) { this.issuer = issuer; }

    private Type type;
    public Type getType() { return type; }
    protected void setType(Type type) { this.type = type; }

    private int rewardPoints;
    public int getRewardPoints() { return rewardPoints; }
    protected void setRewardPoints(int rewardPoints) { this.rewardPoints = rewardPoints; }

    private String title;
    public String getTitle() { return title; }
    protected void setTitle(String title) { this.title = title; }

    private String description;
    public String getDescription() { return description; }
    protected void setDescription(String description) { this.description = description; }

    private DateTime deadline;
    public DateTime getDeadline() { return deadline; }
    protected void setDeadline(DateTime deadline) { this.deadline = deadline; }
    public boolean hasDeadline() { return deadline != null; }

    private Period timeLimit;
    public Period getTimeLimit() { return timeLimit; }
    protected void setTimeLimit(Period timeLimit) { this.timeLimit = timeLimit; }
    public boolean hasTimeLimit() { return timeLimit != null; }

    private boolean isInProgress;
    public boolean isInProgress() { return isInProgress; }
    protected void isInProgress(boolean isInProgress) { this.isInProgress = isInProgress; }

    /**
     * Calculate the difference of last opened datetime and deadline datetime.
     * NOTE:  This should consider the use of an application alarm timer to check the countdown of each second.
     * @return
     */
    public int getRemainingTimeInSeconds() {
        // Get a delta-time/period between now and the deadline datetime
        Period deltaNowToDeadline = new Period(DateTime.now(), deadline);
        // Get the total length of time in seconds of the delta-time
        int remainingTimeInSeconds = deltaNowToDeadline.toStandardSeconds().getSeconds();
        // If remaining time is greater than 0 seoncds, return the delta in seconds. Else, return 0.
        return (remainingTimeInSeconds > 0) ? remainingTimeInSeconds : 0;
    }

    public Challenge(User issuer, Type type, int rewardPoints, String title, String description) {
        super();
        setIssuer(issuer);
        setType(type);
        setRewardPoints(rewardPoints);
        setTitle(title);
        setDescription(description);
    }

    public Challenge(User issuer, Type type, int rewardPoints, String title, String description, DateTime deadline) {
        this(issuer, type, rewardPoints, title, description);
        setDeadline(deadline);
    }

    public Challenge(User issuer, Type type, int rewardPoints, String title, String description, Period timeLimit) {
        this(issuer, type, rewardPoints, title, description);
        setTimeLimit(timeLimit);
    }

//    public Challenge(PresetChallenge preset) {
//        //TODO
//    }

    @Override
    public String toJSONString() {
        return null; //TODO
    }

}//end class Challenge
