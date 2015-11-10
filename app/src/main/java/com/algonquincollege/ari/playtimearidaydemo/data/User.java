package com.algonquincollege.ari.playtimearidaydemo.data;

/**
 * Created by Cong on 8/14/2015.
 */
public class User extends SerializableEntity {

    private boolean isEnforced;
    public boolean isEnforced() { return isEnforced; }
    protected void setIsEnforced(boolean isEnforced) { this.isEnforced = isEnforced; }

    private UserAccount account;
    public UserAccount getAccount() { return account; }
    // Hint: Use UserAccountController.associateAccountToUser
    protected void setAccount(UserAccount account) { this.account = account; }

    private UserProfile profile;
    public UserProfile getProfile() { return profile; }
    // Hint: Use UserProfileController.associateProfileToUser
    protected void setProfile(UserProfile profile) { this.profile = profile; }

    private int currentPoints;
    public int getCurrentPoint() { return currentPoints; }
    public void setCurrentPoints(int currentPoints) { this.currentPoints = currentPoints; }

    // Constuctor relying on Controllers to associate account and profile
//    public User(int currentPoints) {
//        super();
//        setCurrentPoints(currentPoints);
//    }
    
    public User(UserAccount account, UserProfile profile, int currentPoints) {
        super();
        setAccount(account);
        setProfile(profile);
        setCurrentPoints(currentPoints);
    }

    @Override
    public String toJSONString() {
        return null; //TODO
    }
}//end class User
