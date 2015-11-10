package com.algonquincollege.ari.playtimearidaydemo.data;

import com.algonquincollege.ari.playtimearidaydemo.R;

import org.joda.time.DateTime;
import org.joda.time.Period;

/**
 * Created by Cong on 8/17/2015.
 */
public class ChallengeTimelineItem extends TimelineItem {

    public static final TimelineItem.SubType SUBTYPE = new SubType(SubType.CHALLENGE);

    public static final int REWARD_FOREGROUND_COLOR = DemoModel.getInstance().getContext().getResources().getColor(R.color.content_text_light);
    public static final int REWARD_INCOMPLETE_BACKGROUND_COLOR = DemoModel.getInstance().getContext().getResources().getColor(R.color.content_grey);
    public static final int REWARD_COMPLETE_BACKGROUND_COLOR = DemoModel.getInstance().getContext().getResources().getColor(R.color.content_yellow);

    private Challenge challenge;
    public Challenge getChallenge() { return challenge; }
    protected void setChallenge(Challenge challenge) { this.challenge = challenge; }

    @Override
    protected void processAmountAndUnitText() {
        setAmountText("+"+Integer.toString(challenge.getRewardPoints()));
        setAmountUnitText("");
    }

    protected void processSpecialLabelAndValue() {
        if (challenge.hasTimeLimit()) {

            Period t = challenge.getTimeLimit();
            if (challenge.isInProgress()) {
                setSpecialLabelText("time remaining");

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
            else {
                setSpecialLabelText("time limit");
                setSpecialValueText(
                        // Is time limit 1 hour or longer?
                        (t.getHours() >= 1) ?
                                // hour, minute, second
                                String.format("%2dh:%02dm:%02ds", t.getHours(), t.getMinutes(), t.getSeconds()) :
                                        // minute, second
                                        String.format("%2dm:%02ds", t.getMinutes(), t.getSeconds())
                );
            }
        }
        else {
            setSpecialLabelText("");
            setSpecialValueText("");
        }
    }

    protected void processIconFileName() {
        switch (challenge.getType().getState()) {
            case Challenge.Type.MIND:
                setIconFileName("challenge_mind");
                break;
            case Challenge.Type.BODY:
                setIconFileName("challenge_body");
                break;
            case Challenge.Type.SPIRIT:
                setIconFileName("challenge_spirit");
                break;
            case Challenge.Type.CUSTOM:
                /*TODO
                Get a custom challenge icon.
                Suggestions:
                - a circle with all 3 split evenly into 3
                - (a circle) avatar of the user or team that issued the challenge
                - a trophy icon
                 */
                setIconFileName("challenge_spirit");
                break;
        }
    }

    /**
     * Constructor
     * @param occurrenceTime
     * @param challenge
     */
    public ChallengeTimelineItem(DateTime occurrenceTime, Challenge challenge) {
        super(occurrenceTime);
        setSubType(SUBTYPE);
        setChallenge(challenge);

        /// Title
        setPrimaryText(challenge.getTitle());
        /// Issuer
        setSecondaryLabelText(challenge.getIssuer().getProfile().getFullName());
        /// Challenge Type
        processIconFileName();
        /// Description or Notes
        setSecondaryValueText(challenge.getDescription());
        /// Amount Unit and Text
        processAmountAndUnitText();
        /// Reward Text Color
        setAmountForegroundColor(REWARD_FOREGROUND_COLOR);
        /// Reward Background color
        setAmountBackgroundColor((challenge.isInProgress()) ? REWARD_INCOMPLETE_BACKGROUND_COLOR : REWARD_COMPLETE_BACKGROUND_COLOR);
        /// Special Label and Value
        processSpecialLabelAndValue();
    }

}//end class ChallengeTimelineItem
