package com.algonquincollege.ari.playtimearidaydemo.data;

import org.joda.time.DateTime;

/**
 * Created by Cong on 8/14/2015.
 */
public class UserAccount extends SerializableEntity {

    public static class Status extends StateProperty<Character> {
        public static final char ACTIVE = 'a';
        public static final char INACTIVE = 'i';
        public static final char PENDING = 'p';
        public static final char TESTING = 't';

        private static final Character[] states = {ACTIVE, INACTIVE, PENDING, TESTING};

        protected Status(Character initialState) {
            super(states, initialState);
        }
    }

    private User user;
    public User getUser() { return user; }

    private String username;
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    private String emailAddress;
    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }


    private DateTime creationDate;
    public DateTime getCreationDate() { return creationDate; }
    protected void setCreationDate(DateTime creationDate) { this.creationDate = creationDate; }

    private Status status;
    public Status getStatus() { return status; }
    protected void setStatus(Status status) {
        this.status = status;
    }

    private DateTime lastActiveDate;
    public DateTime getLastActiveDate() { return lastActiveDate; }
    protected void setLastActiveDate(DateTime lastActiveDate) { this.lastActiveDate = lastActiveDate; }
    public void updateLastActiveDate() {
        // Get system timezone, then update to 'now'
        setLastActiveDate(DateTime.now());
    }

    /// NOTE: These social network properties will have to be migrated to seperate entity
    private String facebookToken;
    public String getFacebookToken() { return facebookToken; }
    public void setFacebookToken(String facebookToken) { this.facebookToken = facebookToken; }

    private String googlePlusToken;
    public String getGooglePlusToken() { return googlePlusToken; }
    public void setGooglePlusToken(String googlePlusToken) { this.googlePlusToken = googlePlusToken; }

    public UserAccount(String username, String emailAddress, DateTime creationDate, Status status, DateTime lastActiveDate) {
        super();
        setUsername(username);
        setEmailAddress(emailAddress);
        setCreationDate(creationDate);
        setStatus(status);
        setLastActiveDate(lastActiveDate);
    }

    @Override
    public String toJSONString() {
        return null; //TODO
    }

}//end class UserAccount
