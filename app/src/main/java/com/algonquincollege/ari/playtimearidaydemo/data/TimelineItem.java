package com.algonquincollege.ari.playtimearidaydemo.data;

import android.content.res.Resources;

import com.algonquincollege.ari.playtimearidaydemo.R;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.Period;

import java.util.Comparator;


/**
 * Created by Cong on 8/14/2015.
 */
public abstract class TimelineItem extends SerializableEntity {

    public static class SubType extends StateProperty<String> {
        public static final String TP_APP_USAGE = "TPAppUsage";
        public static final String CHALLENGE = "Challenge";

        private static final String[] states = {TP_APP_USAGE, CHALLENGE};

        protected SubType(String initialState) {
            super(states, initialState);
        }
    }

    /**
     * Subtype will be used by the TimelineAdapter to interpret the layout of each custom row
     * to properly display information of each timelineItem sub type.
     */
    private SubType subType;
    public SubType getSubType() { return subType; }
    protected void setSubType(SubType subType) { this.subType = subType; }

    private User user;

    private String primaryText;
    public String getPrimaryText() { return primaryText; }
    protected void setPrimaryText(String primaryText) { this.primaryText = primaryText; }

    private String secondaryLabelText;
    public String getSecondaryLabelText() { return secondaryLabelText; }
    protected void setSecondaryLabelText(String secondaryLabelText) { this.secondaryLabelText = secondaryLabelText; }

    private String secondaryValueText;
    public String getSecondaryValueText() { return secondaryValueText; }
    protected void setSecondaryValueText(String secondaryValueText) { this.secondaryValueText = secondaryValueText; }

    private String amountText;
    public String getAmountText() { return amountText; }
    protected void setAmountText(String amountText) { this.amountText = amountText; }

    private String amountUnitText;
    public String getAmountUnitText() { return amountUnitText; }
    protected void setAmountUnitText(String amountUnitText) { this.amountUnitText = amountUnitText; }

    private int amountForegroundColor;
    public int getAmountForegroundColor() { return amountForegroundColor; }
    protected void setAmountForegroundColor(int amountForegroundColor) { this.amountForegroundColor = amountForegroundColor; }

    private int amountBackgroundColor;
    public int getAmountBackgroundColor() { return amountBackgroundColor; }
    protected void setAmountBackgroundColor(int amountBackgroundColor) { this.amountBackgroundColor = amountBackgroundColor; }

    private String iconFileName;
    public String getIconFileName() { return iconFileName; }
    protected void setIconFileName(String iconFileName) { this.iconFileName = iconFileName; }

    protected abstract void processIconFileName();

    private String specialLabelText;
    public String getSpecialLabelText() { return specialLabelText; }
    protected void setSpecialLabelText(String specialLabelText) { this.specialLabelText = specialLabelText; }

    private String specialValueText; // eg. time remaining
    public String getSpecialValueText() { return specialValueText; }
    protected void setSpecialValueText(String specialValueText) { this.specialValueText = specialValueText; }

    private int specialValueColor;
    public int getSpecialValueColor() { return specialValueColor; }
    protected void setSpecialValueColor(int specialValueColor) { this.specialValueColor = specialValueColor; }

    private DateTime occurrenceTime; // eg. 25m // eg. 1h 24m
    public DateTime getOccurrenceTime() { return occurrenceTime; }
    private void setOccurrenceTime(DateTime occurrenceTime) { this.occurrenceTime = occurrenceTime; }

    protected abstract void processAmountAndUnitText();

    /**
     * Author: Cong
     * Calculate and return a simple formatted time elapsed since the time of this TimelineItem's
     * occurrence (ie. creation).  Highest amount of time displayed is in weeks. Uses a ternary-if stack.
     * @return
     */
    public String getElapsedTime() {
        /**
         * Joda instruction credit to BalusC @ stackoverflow.com
         * http://stackoverflow.com/posts/2179831/revisions
         */
        Period delta = new Period(getOccurrenceTime(), DateTime.now());
        return
            // 1+ weeks
            (delta.getWeeks() >= 1) ? delta.getWeeks()+"w" :
                    // 1+ days
                    (delta.getDays() >= 1) ? delta.getDays()+"d" :
                            // 1+ hours
                            (delta.getHours() >= 1) ? delta.getHours()+"h" :
                                    // 1+ minutes
                                        (delta.getMinutes() >= 1) ? delta.getMinutes()+"m" :
                                                // 1+ seconds
                                                delta.getSeconds()+"s";
    }

    private LogEvent logEvent;

    /**
     * Constructor.
     * @param occurrenceTime
     */
    protected TimelineItem(DateTime occurrenceTime) {
        super();
        Resources res = DemoModel.getInstance().getContext().getResources();
        // Prevent null pointer reference by setting properties to default values.
        setPrimaryText(res.getString(R.string.timeline_row_primary));
        setSecondaryValueText(res.getString(R.string.timeline_row_secondary));
        setAmountText(res.getString(R.string.timeline_row_amount));
        setAmountUnitText(res.getString(R.string.timeline_row_amount_unit));
        setAmountForegroundColor(res.getColor(R.color.content_text_light));
        setAmountBackgroundColor(res.getColor(R.color.invisible));
        setIconFileName("");
        setSpecialLabelText(res.getString(R.string.timeline_row_special_label));
        setSpecialValueText(res.getString(R.string.timeline_row_special_value));
        setSpecialValueColor(res.getColor(R.color.invisible));

        setOccurrenceTime(occurrenceTime);
    }

    @Override
    public String toJSONString() {
        return null; //TODO
    }

    public static final Comparator<TimelineItem> orderByOccurrenceTime = new Comparator<TimelineItem>() {
        @Override
        public int compare(TimelineItem lhs, TimelineItem rhs) {
            return DateTimeComparator.getInstance().compare(rhs.getOccurrenceTime(), lhs.getOccurrenceTime());
        }
    };

}//end class TimelineItem
