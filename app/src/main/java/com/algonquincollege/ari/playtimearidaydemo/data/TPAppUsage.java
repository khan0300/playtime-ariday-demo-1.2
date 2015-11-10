package com.algonquincollege.ari.playtimearidaydemo.data;

import org.joda.time.Period;

/**
 * Created by Cong on 8/14/2015.
 */
public class TPAppUsage extends SerializableEntity {

    private UserTPApp userTPApp;
    public UserTPApp getUserTPApp() { return userTPApp; }
    protected void setUserTPApp(UserTPApp userTPApp) { this.userTPApp = userTPApp; }

    private int timeSpentSeconds;
    public int getTimeSpentSeconds() { return timeSpentSeconds; }
    protected void setTimeSpentSeconds(int timeSpentSeconds) { this.timeSpentSeconds = timeSpentSeconds; }

    private Period timeSpentPeriod;
    public Period getTimeSpentPeriod() { return timeSpentPeriod; }
    protected void setTimeSpentPeriod(Period timeSpentPeriod) { this.timeSpentPeriod = timeSpentPeriod; }

    public TPAppUsage(UserTPApp userTPApp, int timeSpentSeconds) {
        setUserTPApp(userTPApp);
        setTimeSpentSeconds(timeSpentSeconds);
        setTimeSpentPeriod(new Period(timeSpentSeconds/3600, timeSpentSeconds/60, timeSpentSeconds%60, 0));
    }

    @Override
    public String toJSONString() {
        return null; //TODO
    }

}//end class TPAppUsage
