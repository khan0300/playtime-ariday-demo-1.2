package com.algonquincollege.ari.playtimearidaydemo.data;

import org.joda.time.DateTime;

/**
 *  Created by Cong Tran
 */
public class UserProfile extends SerializableEntity {

    public static class Gender extends StateProperty<String> {
        public static final String MALE = "Male";
        public static final String FEMALE = "Female";
        public static final String OTHER = "Other";

        private static final String[] states = {MALE, FEMALE, OTHER};

        public Gender(String initialState) {
            super(states, initialState);
        }
    }

    private User user;
    public User getUser() { return user; }

    private String firstName;
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    private String lastName;
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getFullName() { return firstName + " " + lastName; }

    private DateTime birthDate;
    public DateTime getBirthDate() { return birthDate; }
    public void setBirthDate(DateTime birthDate) { this.birthDate = birthDate; }

    private Gender gender;
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public UserProfile(String firstName, String lastName, DateTime birthDate, Gender gender) {
        super();
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setGender(gender);
    }

    @Override
    public String toJSONString() {
        return null; //TODO
    }

}//end class UserProfile
