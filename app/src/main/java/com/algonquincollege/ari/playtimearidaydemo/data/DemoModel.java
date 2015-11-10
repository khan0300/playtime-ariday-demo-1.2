package com.algonquincollege.ari.playtimearidaydemo.data;

import android.app.Activity;
import android.content.res.Resources;

import com.algonquincollege.ari.playtimearidaydemo.R;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Cong on 8/16/2015.
 */
public class DemoModel {
    /// Singleton
    private static final DemoModel singleton = new DemoModel();
    public static final DemoModel getInstance() { return singleton; }
    private DemoModel () {  }
    private Activity context;
    protected Activity getContext() { return context; }

    private boolean hasInitialized = false;
    protected static final String CLIENT_USER_USERNAME = "peterpumpkins";
    private static User clientUser;
    private static User clientUser() { return clientUser; }
    public static final User CLIENT_USER = clientUser();

    ///////////////////////////
    // begin DATA ARRAYLISTS //

    private ArrayList<User> users;
    public ArrayList<User> getUsers() { return users; }

    private ArrayList<UserAccount> userAccounts;
    public ArrayList<UserAccount> getUserAccounts() { return userAccounts; }

    private ArrayList<UserProfile> userProfiles;
    public ArrayList<UserProfile> getUserProfiles() { return userProfiles; }

    private ArrayList<Team> teams;
    public ArrayList<Team> getTeams() { return teams; }

    private ArrayList<TPApp> tpApps;
    public ArrayList<TPApp> getTpApps() { return tpApps; }

    private ArrayList<UserTPApp> userTPApps;
    public ArrayList<UserTPApp> getUserTPApps() { return userTPApps; }

    private ArrayList<TPAppUsage> tpAppUsages;
    public ArrayList<TPAppUsage> getTpAppUsages() { return tpAppUsages; }

    private ArrayList<Challenge> challenges;
    public ArrayList<Challenge> getChallenges() { return challenges; }

    // end DATA ARRAYLISTS //
    /////////////////////////

    /////////////////
    // begin INIT  //

    private static Random r = new Random();
    private static int randomPoints() { return r.nextInt(500)+18; }

    /**
     * Demo Data Initialization method.
     */
    public DemoModel init(Activity context) {
        if (hasInitialized) return getInstance();

        this.context = context;
        Resources res = context.getResources();

        users = new ArrayList<>();
        userAccounts = new ArrayList<>();
        userProfiles = new ArrayList<>();
        teams = new ArrayList<>();
        tpApps = new ArrayList<>();
        userTPApps = new ArrayList<>();
        tpAppUsages = new ArrayList<>();
        challenges = new ArrayList<>();

        DateTime creationDate = new DateTime(2015, 5, 8, 1, 1);
        DateTime lastActiveDate = new DateTime(2015, 7, 21, 1, 1);
        DateTime birthDate = new DateTime(1980, 1, 1, 1, 1);


        // Create array of User objects
        User[] userArray = {
                new User( // 0 Client User
                        new UserAccount(CLIENT_USER_USERNAME, "peter_pumpkins@itsplaytime.com", creationDate, new UserAccount.Status(UserAccount.Status.ACTIVE), lastActiveDate),
                        new UserProfile("Peter", "Pumpkins", birthDate, new UserProfile.Gender(UserProfile.Gender.MALE)),
                        randomPoints()
                ),
                new User( // 1 Jim
                        new UserAccount("john_nado", "johnnado@itsplaytime.com", creationDate, new UserAccount.Status(UserAccount.Status.ACTIVE), lastActiveDate),
                        new UserProfile("John", "Nado", birthDate, new UserProfile.Gender(UserProfile.Gender.MALE)),
                        randomPoints()
                ),
                new User( // 2 Jane
                        new UserAccount("jane_nado", "janenado@itsplaytime.com", creationDate, new UserAccount.Status(UserAccount.Status.ACTIVE), lastActiveDate),
                        new UserProfile("Jane", "Nado", birthDate, new UserProfile.Gender(UserProfile.Gender.FEMALE)),
                        randomPoints()
                ),
                new User( // 3 Jimmy
                        new UserAccount("jonny_nado", "jonnynado@itsplaytime.com", creationDate, new UserAccount.Status(UserAccount.Status.ACTIVE), lastActiveDate),
                        new UserProfile("Jonny", "Nado", birthDate, new UserProfile.Gender(UserProfile.Gender.MALE)),
                        randomPoints()
                ),
                new User( // 4 Janet
                        new UserAccount("janet_nado", "janetnado@itsplaytime.com", creationDate, new UserAccount.Status(UserAccount.Status.ACTIVE), lastActiveDate),
                        new UserProfile("Janet", "Nado", birthDate, new UserProfile.Gender(UserProfile.Gender.FEMALE)),
                        randomPoints()
                ),
                new User( // 5 Impending Doom
                        new UserAccount("pending_doomy", "pendingdoomy@itsplaytime.com", creationDate, new UserAccount.Status(UserAccount.Status.PENDING), lastActiveDate),
                        new UserProfile("Impending", "Doom", birthDate, new UserProfile.Gender(UserProfile.Gender.OTHER)),
                        randomPoints()
                )
        };
        // Add users from array to ArrayList
        for (User user : userArray) {
            userAccounts.add(user.getAccount());
            userProfiles.add(user.getProfile());
            users.add(user);
        }
        // Hold reference to Client User
        clientUser = userArray[0];


        // Create array of Team objects
        Team[] teamArray = {
                new Team(users.get(1), "The J-Nadoes", Team.TYPE_ENFORCED, creationDate)
                , new Team(users.get(2), "John versus Jane", Team.TYPE_PRIVATE, creationDate)
                , new Team(users.get(1), "Open Action Fitness Group Ottawa", Team.TYPE_PUBLIC, creationDate)
        };
        // Add teams from array to ArrayList
        for (Team team : teamArray) {
            teams.add(team);
        }
        // Set moderators and members for each team
        /*
         NOTE:  Order of insertion matters!!
                user 0 is added last to avoid issuing challenges to himself.
          */
        /// Team 0 - J-Nadoes
        ArrayList<User> team0Members = teamArray[0].getMembers();
        team0Members.add(userArray[1]); // john
        team0Members.add(userArray[2]); // jane
        team0Members.add(userArray[3]); // jonny
        team0Members.add(userArray[4]); // janet
        team0Members.add(userArray[0]); // peter
        teamArray[0].getModerators().add(userArray[1]); // john
        teamArray[0].getModerators().add(userArray[2]); // jane
        /// Team 1 - John versus Jane
        ArrayList<User> team1Members = teamArray[1].getMembers();
        team1Members.add(userArray[1]); // john
        team1Members.add(userArray[2]); // jane
        teamArray[1].getModerators().add(userArray[1]); // john
        teamArray[1].getModerators().add(userArray[2]); // jane
        /// Team 2 - Open Action Fitness Group Ottawa
        ArrayList<User> team2Members = teamArray[2].getMembers();
        team2Members.add(userArray[1]); // john
        team2Members.add(userArray[2]); // jane
        team2Members.add(userArray[5]); // impending doom
        team2Members.add(userArray[0]); // peter
        teamArray[2].getModerators().add(userArray[1]); // john
        teamArray[2].getModerators().add(userArray[2]); // jane


        // Create array of ThirdPartyApps
        TPApp[] tpAppArray = {
                // Timewasters
                new TPApp("Petflix", "petflix", TPApp.Category.VIDEO, res.getString(R.string.tpa_description_petflix))                                  //0
                , new TPApp("MyTube", "mytube", TPApp.Category.VIDEO, res.getString(R.string.tpa_description_mytube))                                   //1
                , new TPApp("Conflict of Clubs", "conflictofclubs", TPApp.Category.GAME, res.getString(R.string.tpa_description_conflictofclubs))       //2
                , new TPApp("Talk @ Me", "talkatme", TPApp.Category.MESSAGING, res.getString(R.string.tpa_description_talkatme))                        //3
                , new TPApp("Pic Me!", "picme", TPApp.Category.SOCIAL_NETWORKING, res.getString(R.string.tpa_description_picme))                        //4
                , new TPApp("Picture Post", "picturepost", TPApp.Category.SOCIAL_NETWORKING, res.getString(R.string.tpa_description_picturepost))       //5
                , new TPApp("Mugscroll", "mugscroll", TPApp.Category.SOCIAL_NETWORKING, res.getString(R.string.tpa_description_mugscroll))              //6
                , new TPApp("MeowMeow Beenz", "meowmeowbeenz", TPApp.Category.GAME, res.getString(R.string.tpa_description_meowmeowbeenz))              //7
                , new TPApp("Bitter Butterflies", "bitterbutterflies", TPApp.Category.GAME, res.getString(R.string.tpa_description_bitterbutterflies))  //8
                // Other
                , new TPApp("Hearable", "hearable", TPApp.Category.AUDIO_BOOK, res.getString(R.string.tpa_description_hearable))                                //9
                , new TPApp("MATHs", "maths", TPApp.Category.EDUCATION, res.getString(R.string.tpa_description_maths))                                          //10
                , new TPApp("Crafty", "crafty", TPApp.Category.CREATIVE_ARTS, res.getString(R.string.tpa_description_crafty))                                   //11
                , new TPApp("Drawr", "drawr", TPApp.Category.GRAPHICS_DESIGN, res.getString(R.string.tpa_description_drawr))                                    //12
                , new TPApp("Lurnt!", "lurnt", TPApp.Category.EDUCATION, res.getString(R.string.tpa_description_lurnt))                                         //13
                , new TPApp("CodeWiz", "codewiz", TPApp.Category.EDUCATION, res.getString(R.string.tpa_description_codewiz))                                    //14
                , new TPApp("JED Talks", "jedtalks", TPApp.Category.EDUCATION, res.getString(R.string.tpa_description_jedtalks))                                //15
                , new TPApp("7 Min Workout", "7minworkout", TPApp.Category.HEALTH_AND_FITNESS, res.getString(R.string.tpa_description_7minworkout))             //16
                , new TPApp("Rest Music Lessons", "restmusiclessons", TPApp.Category.CREATIVE_ARTS, res.getString(R.string.tpa_description_restmusiclessons))   //17
        };

        // Add TPApps from array to ArrayList
        for (TPApp tpApp : tpAppArray) {
            tpApp.setIconString("tpa_" + tpApp.getIconString());
            tpApps.add(tpApp);
        }

        // Create UserTPApps for each existing TPApp
        for (TPApp tpApp : tpAppArray) {
            // Create new UserTPApp
            UserTPApp userTPApp = new UserTPApp(clientUser, tpApp);

            // Set the datetime propeties for the UserTPApp, using random values
            DateTime expirationDateTime = DateTime.now().plusMinutes(r.nextInt(57)+2);
            DateTime lastOpenedDateTime = DateTime.now().minusMinutes(r.nextInt(10080)+132);
            DateTime lastClosedDateTime = lastOpenedDateTime.plusMinutes(r.nextInt(100));

            userTPApp.setExpirationDateTime(expirationDateTime);
            userTPApp.setLastOpenedDateTime(lastOpenedDateTime);
            userTPApp.setLastClosedDateTime(lastClosedDateTime);

            // Add UserTPApp to the arrayList
            userTPApps.add(userTPApp);
        }

        // Create TPAppUsages at random in a loop and add subsequently add them to the ArrayList
        final int tpAppUsageCount = 35;
        for (int i = 0; i < tpAppUsageCount; i++) {
            tpAppUsages.add(new TPAppUsage(userTPApps.get(r.nextInt(userTPApps.size()-1)), ((r.nextInt(10) < 8) ? r.nextInt(600) : r.nextInt(4200))+15));
        }
        // Create and Add one TPAppUsage for each TPApp, just to guarantee that all TPApss are displayed
        for (UserTPApp userTPApp : userTPApps) {
            tpAppUsages.add(new TPAppUsage(userTPApp, r.nextInt(3600)+15));
        }

        // Create array of Challenges
        Challenge[] challengeArray = {
                // J-Nadoes Challenges
                new Challenge(team0Members.get(r.nextInt(team0Members.size()-1)), Challenge.TYPE_MIND, 15, "Read Lone Wolf Survivor", "you're three chapters behind me, hurry up!!")
                , new Challenge(team0Members.get(r.nextInt(team0Members.size()-1)), Challenge.TYPE_BODY, 30, "Wash the van", "Just the exterior and quick polish on the rims", new Period(2,30,0,0))
                , new Challenge(team0Members.get(r.nextInt(team0Members.size()-1)), Challenge.TYPE_MIND, 10, "Practice Guitar", "The Beatles Blackbird")
                , new Challenge(team0Members.get(r.nextInt(team0Members.size()-1)), Challenge.TYPE_SPIRIT, 20, "Meditate", "Focus on your breathing...")
                , new Challenge(team0Members.get(r.nextInt(team0Members.size()-1)), Challenge.TYPE_BODY, 15, "Walk the dog", "Jipson has been stuck inside all day!", new Period(1,0,0,0))
                , new Challenge(team0Members.get(r.nextInt(team0Members.size()-1)), Challenge.TYPE_SPIRIT, 60, "Family Game Night", "Monopoly showdown!!! You won't win this time, Peter")
                , new Challenge(team0Members.get(r.nextInt(team0Members.size()-1)), Challenge.TYPE_BODY, 45, "Mow the lawn", "front and beside the patio", new Period(1,0,0,0))
                , new Challenge(team0Members.get(r.nextInt(team0Members.size()-1)), Challenge.TYPE_CUSTOM, 45, "Cheer for Jonny at his hockey game", "tomorrow at Ottawa Sens Arena 630pm")
                , new Challenge(team0Members.get(r.nextInt(team0Members.size()-1)), Challenge.TYPE_MIND, 25, "Finish math exercises", "keep it up Math wiz!", new Period(1,30,0,0))
                , new Challenge(team0Members.get(r.nextInt(team0Members.size()-1)), Challenge.TYPE_CUSTOM, 25, "Clean the basement den area", "and I'll make the wings and nachos for game night", new Period(3,0,0,0))
                , new Challenge(team0Members.get(r.nextInt(team0Members.size()-1)), Challenge.TYPE_CUSTOM, 40, "Cook dinner", "I'm feeling Italian cuisine tonight")
                // Open Action Fitness Group Challenges
                , new Challenge(team1Members.get(r.nextInt(team1Members.size()-1)), Challenge.TYPE_BODY, 10, "Jog 5 km", "Go for a 5 kilometer job around the city", new Period(48,0,0,0))
                , new Challenge(team1Members.get(r.nextInt(team1Members.size()-1)), Challenge.TYPE_BODY, 6, "Skip rope for 6 minutes", "Good cardio!", new Period(0,6,0,0))
                , new Challenge(team1Members.get(r.nextInt(team1Members.size()-1)), Challenge.TYPE_SPIRIT, 12, "Lead next session warmups", "Get the team pumped and ready", new Period(0,25,0,0))
        };
        // Add Challenges from array to ArrayList
        for (Challenge challenge : challengeArray) {
            challenges.add(challenge);
            if (r.nextInt(100) < 55) { // TEMP: 55% chance that the challenge is accepted and thus inProgress
                challenge.isInProgress(true);
            }
        }

        ////////////
        // FINISH //
        //--------//

        hasInitialized = true;
        return getInstance();
    }//end init

    // end INIT //
    //////////////

}//end class DemoModel
