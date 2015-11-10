package com.algonquincollege.ari.playtimearidaydemo.data;

/**
 * Created by Cong Tran on 8/14/2015.
 */
public class TPApp extends SerializableEntity {

    public class Category {
        public static final String GAME = "game";
        public static final String VIDEO = "video";
        public static final String AUDIO = "audio";
        public static final String MUSIC = "music";
        public static final String EDUCATION = "education";
        public static final String AUDIO_BOOK = "audiobook";
        public static final String UTILITY = "utility";
        public static final String ENTERTAINMENT = "entertainment";
        public static final String GRAPHICS_DESIGN = "graphics_design";
        public static final String CREATIVE_ARTS = "creative_arts";
        public static final String EMAIL = "email";
        public static final String MESSAGING = "messaging";
        public static final String PRODUCTIVITY = "productivity";
        public static final String PERSONALIZATION = "personalization";
        public static final String HEALTH_AND_FITNESS = "health_and_fitness";
        public static final String SOCIAL_NETWORKING = "social_networking";
        public static final String NAVIGATION = "navigation";
        public static final String MARKET = "market";
        public static final String FINANCE = "finance";
        public static final String NEWS_AND_WEATHER = "news_and_weather";
    }

    private String title;
    public String getTitle() { return title; }
    protected void setTitle(String title) { this.title = title; }

    private String description;
    public String getDescription() { return description; }
    protected void setDescription(String description) { this.description = description; }

    private String iconString;
    public String getIconString() { return iconString; }
    protected void setIconString(String iconString) { this.iconString = iconString; }

    private String category;
    public String getCategory() { return category; }
    protected void setCategory(String category) { this.category = category; }

    // some other example metadata of third party apps, but only if necessary
//    private String author;
//    private String publishDate;
//    private String downloadSource;

    protected TPApp(String title, String iconString, String category) {
        setTitle(title);
        setIconString(iconString);
        setCategory(category);
    }

    protected TPApp(String title, String iconString, String category, String description) {
        this(title, iconString, category);
        setDescription(description);
    }

    @Override
    public String toJSONString() {
        return null; //TODO
    }

}//end class TPApp
