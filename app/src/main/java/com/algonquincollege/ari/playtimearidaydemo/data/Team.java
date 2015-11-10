package com.algonquincollege.ari.playtimearidaydemo.data;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * Created by Cong Tran on 8/16/2015.
 */
public class Team extends SerializableEntity {

    public static final Type TYPE_ENFORCED = new Team.Type(Type.ENFORCED);
    public static final Type TYPE_PRIVATE = new Team.Type(Type.PRIVATE);
    public static final Type TYPE_PUBLIC = new Team.Type(Type.PUBLIC);
    public static final Type TYPE_ABANDONED = new Team.Type(Type.ABANDONED);

    protected static class Type extends StateProperty<Character> {
        public static final char ENFORCED = 'e';
        public static final char PRIVATE = 'r';
        public static final char PUBLIC = 'u';
        public static final char ABANDONED = 'x';

        private static final Character[] states = {PUBLIC, PRIVATE, ENFORCED, ABANDONED};

        private Type(char initialState) {
            super(states, initialState);
        }
    }

    ////////////////////////
    /// begin PROPERTIES ///

    private PinCode pinCode;
    public PinCode getPinCode() { return pinCode; }
    protected void setPinCode(PinCode pinCode) { this.pinCode = pinCode; }
    public boolean hasPinCode() {
        return (pinCode != null);
    }

    private String name;
    public String getName() { return name; }
    protected void setName(String name) { this.name = name; }

    private Type type;
    public Type getType() { return type; }
    protected void setType(Type type) { this.type = type; }

    private String description;
    public String getDescription() { return description; }
    protected void setDescription(String description) { this.description = description; }

    private User creationUser;
    public User getCreationUser() { return creationUser; }
    protected void setCreationUser(User creationUser) { this.creationUser = creationUser; }

    private DateTime creationDate;
    public DateTime getCreationDate() { return creationDate; }
    protected void setCreationDate(DateTime creationDate) { this.creationDate = creationDate; }

    // Map<User serial ID, User>
    private ArrayList<User> moderators;
    public ArrayList<User> getModerators() { return moderators; }
    public boolean hasModerator(User moderator) { return moderators.contains(moderator); }
    public boolean hasModerators() { return !moderators.isEmpty(); }

    // Map<User serial ID, User>
    private ArrayList<User> members;
    public ArrayList<User> getMembers() { return members; }
    public boolean hasMember(User member) { return members.contains(member); }
    public boolean hasMembers() { return !members.isEmpty(); }

    /// end PROPERTIES ///
    //////////////////////

    /**
     * Constructor.
     * @param name
     * @param creationUser
     */
    public Team(User creationUser, String name, Team.Type type, DateTime creationDate) {
        this.creationUser = creationUser;
        this.name = name;
        this.type = type;
        this.creationDate = creationDate;
        // TEMP
        description = "";

        moderators = new ArrayList<User>();
        members = new ArrayList<User>();
    }

    /**
     * Team.PinCode class.  Extends SerializableEntity.
     */
    public static class PinCode extends SerializableEntity {

        private Team team;
        protected Team getTeam() { return team; }
        protected void setTeam(Team team) { this.team = team; }

        private String value;
        protected String getValue() { return value; }
        protected void setValue(String value) { this.value = value; }

        private static final HashSet<String> values = new HashSet<String>();
        public static final HashSet<String> getValues() { return values; }

        /**
         * PinCode Constructor.
         */
        public PinCode(Team team, String value) {
            setTeam(team);
            setValue(value);
        }

        @Override
        public String toJSONString() {
            return null; //TODO
        }
    }//end class PinCode

    /**
     * Team.Moderator class.  Extends SerializableEntity.
     * NOTE: Not yet used.
     * Goal implementation: - to enable different access levels or application priveleges
     * if future designs find any use for such functionality.
     */
    public static class Moderator extends SerializableEntity {

        protected User user;
        protected Team team;

        protected Moderator(User user, Team team) {
            this.user = user;
            this.team = team;
        }

        @Override
        public String toJSONString() {
            return null; //TODO
        }
    }//end class Moderator

    /**
     * Team.Member class.  Extends SerializableEntity.
     * NOTE: Not yet used.
     * Goal implementation: - to provide a linking entity between User and Team, providing a user
     * team-specific properties, eg. nicknames, roles, team rankings, etc.
     */
    public static class Member extends SerializableEntity {

        private String nickName;
        public String getNickName() { return nickName; }
        protected void setNickName(String nickName) { this.nickName = nickName; }

        protected User user;
        protected Team team;

        protected Member(User user, Team team) {
            setNickName("");
            this.user = user;
            this.team = team;
        }

        @Override
        public String toJSONString() {
            return null; //TODO
        }
    }//end class Member

    @Override
    public String toJSONString() {
        return null; //TODO
    }
}//end class Team
