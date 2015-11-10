package com.algonquincollege.ari.playtimearidaydemo.data;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

import com.algonquincollege.ari.playtimearidaydemo.data.TPApp.Category;

/**
 * Created by Cong Tran on 8/14/2015.
 */
public class UserTPApp {

    private User user;
    public User getUser() { return user; }
    protected void setUser(User user) { this.user = user; }

    private TPApp tpApp;
    public TPApp getTpApp() { return tpApp; }
    protected void setTPApp(TPApp tpApp) { this.tpApp = tpApp; }

    private boolean isTimeWaster;
    public boolean isTimeWaster() { return isTimeWaster; }
    protected void isTimeWaster(boolean isTimeWaster) { this.isTimeWaster = isTimeWaster; }

    /**C.Tran:
     * TEMP ** FOR DEMO AND TESTING PURPOSES.
     * The goal implementation for checking what category is considered a TimeWaster should be
     * handled by another class (I suggest making a Controller) that also has the ability to
     * allow Moderator users to toggle whether or not an app is a time waster.
     * @param tpApp
     * @return
     */
    private boolean isTimeWaster(TPApp tpApp) {
        // If the app is another of the listed categories,
        switch (tpApp.getCategory()) {
            case Category.GAME:
            case Category.SOCIAL_NETWORKING:
            case Category.VIDEO:
            case Category.ENTERTAINMENT:
            case Category.MESSAGING:
            case Category.PERSONALIZATION:
                isTimeWaster(true);
                return isTimeWaster;
        }
        isTimeWaster(false);
        return isTimeWaster;
    }

    private boolean isLocked;
    public boolean isLocked() { return isLocked; }
    protected void isLocked(boolean isLocked) { this.isLocked = isLocked; }

    private DateTime lastOpenedDateTime;
    public DateTime getLastOpenedDateTime() { return lastOpenedDateTime; }
    protected void setLastOpenedDateTime(DateTime lastOpenedDateTime) { this.lastOpenedDateTime = lastOpenedDateTime; }

    private DateTime lastClosedDateTime;
    public DateTime getLastClosedDateTime() { return lastClosedDateTime; }
    protected void setLastClosedDateTime(DateTime lastClosedDateTime) { this.lastClosedDateTime = lastClosedDateTime; }

    private DateTime expirationDateTime;
    public DateTime getExpirationDateTime() { return expirationDateTime; }
    protected void setExpirationDateTime(DateTime expiraDateTime) { this.expirationDateTime = expiraDateTime; }

    /**
     * Calculate the difference of last opened datetime and expiration datetime.
     * NOTE:  This should consider the use of an application alarm timer to check the countdown each second.
     * @return
     */
    public int getRemainingTimeInSeconds() {
        // Get a delta-time/period between now and the expiration datetime
        Period deltaNowToExpiration = new Period(DateTime.now(), expirationDateTime);
        // Get the total length of time in seconds of the delta-time
        int remainingTimeInSeconds = deltaNowToExpiration.toStandardSeconds().getSeconds();
        // If remaining time is greater than 0 seoncds, return the delta in seconds. Else, return 0.
        return (remainingTimeInSeconds > 0) ? remainingTimeInSeconds : 0;
    }

    /**
     * Constructor.
     * @param user
     * @param tpApp
     */
    protected UserTPApp(User user, TPApp tpApp) {
        setUser(user);
        setTPApp(tpApp);
        isTimeWaster(tpApp);

        if (!isTimeWaster)
            isLocked = false;
    }
}//end class UserTPApp
