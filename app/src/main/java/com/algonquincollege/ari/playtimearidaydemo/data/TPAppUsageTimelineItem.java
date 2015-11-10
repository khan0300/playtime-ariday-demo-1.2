package com.algonquincollege.ari.playtimearidaydemo.data;

import com.algonquincollege.ari.playtimearidaydemo.R;

import org.joda.time.DateTime;
import org.joda.time.Period;

/**
 * Created by Cong on 8/14/2015.
 */
public class TPAppUsageTimelineItem extends TimelineItem {

    public static final SubType SUB_TYPE = new SubType(SubType.TP_APP_USAGE);

    public static final int TIME_WASTER_APPS_COLOR = DemoModel.getInstance().getContext().getResources().getColor(R.color.content_red);
    public static final int OTHER_APPS_COLOR = DemoModel.getInstance().getContext().getResources().getColor(R.color.content_light_green);
    public static final int TIME_USED_TEXT_COLOR = DemoModel.getInstance().getContext().getResources().getColor(R.color.content_text_light);

    private TPAppUsage tpAppUsage;
    public TPAppUsage getTpAppUsage() { return tpAppUsage; }
    protected void setTpAppUsage(TPAppUsage tpAppUsage) { this.tpAppUsage = tpAppUsage; }

    @Override
    protected void processAmountAndUnitText() {
        int t = tpAppUsage.getTimeSpentSeconds();
        // Calculate the amount
        int amount;
        /// Hours
        if (t >= 3600) {
            amount = t/3600;
            setAmountText(Integer.toString(t/3600));
            setAmountUnitText((amount == 1) ? "hr" : "hrs");
        }
        /// Minutes
        else if (t >= 60 && t < 3600) {
            amount = t/60;
            setAmountText(Integer.toString(amount));
            setAmountUnitText((amount == 1) ? "min" : "mins");
        }
        /// Seconds
        else {
            setAmountText("< 1");
            setAmountUnitText("min");
        }
    }

    /**
     * Gets the icon string from the TPApp.
     * Sets the iconFileName of the TPAppUsageTimelineItem.
     */
    protected void processIconFileName() {
        setIconFileName(tpAppUsage.getUserTPApp().getTpApp().getIconString());
    }

    public void processSpecialLabelAndValue() {
        setSpecialLabelText("time used");
        setSpecialValueColor(DemoModel.getInstance().getContext().getResources().getColor(R.color.content_grey));

        Period t = tpAppUsage.getTimeSpentPeriod();
        StringBuilder sb = new StringBuilder();
        if (t.getHours() >= 1) {
            sb.append(Integer.toString(t.getHours())).append(" h");
            if (t.getMinutes() >= 1) {
                sb.append(" ");
            }
        }
        if (t.getMinutes() >= 1) {
            sb.append(Integer.toString(t.getMinutes())).append(" m");
            if (t.getSeconds() >= 1) {
                sb.append(" ");
            }
        }
        if (t.getSeconds() >= 1) {
            sb.append(Integer.toString(t.getSeconds())).append(" s");
        }

        setSpecialValueText(sb.toString());
    }

    /**
     * Constructor.
     * @param occurrenceTime
     * @param tpAppUsage
     */
    public TPAppUsageTimelineItem(DateTime occurrenceTime, TPAppUsage tpAppUsage) {
        super(occurrenceTime);
        setSubType(SUB_TYPE);
        setTpAppUsage(tpAppUsage);

        // set all the TimelineItem values
        /// App Title
        setPrimaryText(tpAppUsage.getUserTPApp().getTpApp().getTitle());
        /// App Description
        setSecondaryValueText(tpAppUsage.getUserTPApp().getTpApp().getDescription());
        /// Time Used
        processAmountAndUnitText();
        /// Time Used Text Color
        setAmountForegroundColor(TIME_USED_TEXT_COLOR);
        /// IsTimeWaster Signifier Color
        setAmountBackgroundColor((tpAppUsage.getUserTPApp().isTimeWaster()) ? TIME_WASTER_APPS_COLOR : OTHER_APPS_COLOR);
        /// App Icon
        processIconFileName();
        /// Special Label and Value
        processSpecialLabelAndValue();
    }

}//end class
