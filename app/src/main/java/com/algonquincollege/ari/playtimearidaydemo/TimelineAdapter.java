package com.algonquincollege.ari.playtimearidaydemo;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.algonquincollege.ari.playtimearidaydemo.data.TimelineItem;

import java.util.List;

/**
 * Created by Ammar on 2015-08-13.
 */
public class TimelineAdapter extends ArrayAdapter<TimelineItem> {

    private final MainActivity context;
    private final List<TimelineItem> timelineItems;

    public TimelineAdapter(Activity context, List<TimelineItem> timelineItems) {
        super(context, R.layout.timeline_row, timelineItems);
        this.context = (MainActivity) context;
        this.timelineItems = timelineItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Resources res = context.getResources();
        LayoutInflater newInflater = LayoutInflater.from(getContext());
        View customRow = newInflater.inflate(R.layout.timeline_row, parent, false);
        customRow.getLayoutParams().height = (int) res.getDimension(R.dimen.timeline_row_height_collapsed);

        // TimelineItem of this current position in the list
        TimelineItem timelineItem = timelineItems.get(position);
        // Reference customRow XML elements
        RelativeLayout mainLayout = (RelativeLayout) customRow.findViewById(R.id.timeline_row_container_layout);
        TextView primaryTextView = (TextView) customRow.findViewById(R.id.timeline_row_primary);
        TextView secondaryLabelTextView = (TextView) customRow.findViewById(R.id.timeline_row_secondary_label);
        TextView secondaryValueTextView = (TextView) customRow.findViewById(R.id.timeline_row_secondary_value);
        ImageView challengeRewardImageView = (ImageView) customRow.findViewById(R.id.timeline_row_challenge_reward_circle);
        TextView amountTextView = (TextView) customRow.findViewById(R.id.timeline_row_amount);
        TextView amountUnitTextView = (TextView) customRow.findViewById(R.id.timeline_row_amount_unit);
        TextView timeSinceOccurrenceTextView = (TextView) customRow.findViewById(R.id.timeline_row_time_elapsed);
        ImageView thumbnailImageView = (ImageView) customRow.findViewById(R.id.timeline_row_thumbnail);
        TextView specialTextViewLabel = (TextView) customRow.findViewById(R.id.timeline_row_special_label);
        TextView specialTextViewValue = (TextView) customRow.findViewById(R.id.timeline_row_special_value);

        final Typeface houseSlant_font = context.FONT_HOUSE_SLANT;
        final Typeface robotoBold_font = context.FONT_ROBOTO_BOLD_ITALIC;
        final Typeface roboto_font = context.FONT_ROBOTO_ITALIC;

        boolean isChallengeTimelineItem = timelineItem.getSubType().isState(TimelineItem.SubType.CHALLENGE);

        /// Main layout
            if (isChallengeTimelineItem) {
                mainLayout.setBackgroundColor(res.getColor(R.color.theme_light_beige));
                mainLayout.setElevation(4f);
            }
            else {
                mainLayout.setBackgroundColor(res.getColor(R.color.theme_lighter_beige));
                mainLayout.setElevation(1f);
            }

        /// Primary
        primaryTextView.setText(" "+/*<-- EWWW*/timelineItem.getPrimaryText().toUpperCase());
        primaryTextView.setLineSpacing(0f, 0.8f);
        primaryTextView.setTypeface(houseSlant_font);

            if (isChallengeTimelineItem) {
                primaryTextView.setTextColor(res.getColor(R.color.content_orange));
            }
            else {
                primaryTextView.setTextColor(res.getColor(R.color.content_deep_navy));
            }

        /// Secondary Label
            if (isChallengeTimelineItem) {
                secondaryLabelTextView.setText(timelineItem.getSecondaryLabelText());
                secondaryLabelTextView.setTextColor(res.getColor(R.color.content_deep_red));
                secondaryLabelTextView.setTypeface(robotoBold_font);
            }
            else {
                secondaryLabelTextView.setVisibility(View.GONE);
            }

        /// Secondary Value
        secondaryValueTextView.setText(timelineItem.getSecondaryValueText());
        secondaryValueTextView.setTypeface(roboto_font);
        secondaryValueTextView.setTextColor(res.getColor(R.color.content_grey));

            if (isChallengeTimelineItem) {
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                lp.addRule(RelativeLayout.BELOW, R.id.timeline_row_secondary_label);
                lp.addRule(RelativeLayout.ALIGN_LEFT, R.id.timeline_row_secondary_label);

                secondaryValueTextView.setLayoutParams(lp);
                secondaryValueTextView.setGravity(Gravity.LEFT);
            }

        /// Time Since Occurrence
        timeSinceOccurrenceTextView.setText(timelineItem.getElapsedTime());
        timeSinceOccurrenceTextView.setTextColor(res.getColor(R.color.content_grey));
        timeSinceOccurrenceTextView.setTypeface(roboto_font);

        /// Amount
        amountTextView.setText(timelineItem.getAmountText());
        amountTextView.setTextColor(timelineItem.getAmountForegroundColor());
        amountTextView.setTypeface(robotoBold_font);

            if (isChallengeTimelineItem) {
                amountTextView.getLayoutParams().height *= 2;
                amountTextView.setTextSize(24);
                ((GradientDrawable) challengeRewardImageView.getBackground()).setColor(timelineItem.getAmountBackgroundColor());
            } else {
                amountTextView.setBackgroundColor(timelineItem.getAmountBackgroundColor());
                challengeRewardImageView.setVisibility(View.GONE);
            }

        /// Amount Unit
            if (isChallengeTimelineItem) {
                amountUnitTextView.setVisibility(View.GONE);
            } else {
                amountUnitTextView.setText(timelineItem.getAmountUnitText());
                amountUnitTextView.setTextColor(timelineItem.getAmountForegroundColor());
                amountUnitTextView.setTypeface(robotoBold_font);
                amountUnitTextView.setBackgroundColor(timelineItem.getAmountBackgroundColor());
            }

        /// Thumbnail
        String PACKAGE_NAME = getContext().getPackageName();
        String uri = PACKAGE_NAME+":drawable/"+timelineItem.getIconFileName();
        int imageResId = res.getIdentifier(uri,null,null);
        Bitmap downscaledBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, imageResId), 128, 128, false);
        thumbnailImageView.setImageBitmap(downscaledBitmap);
        /// Special Label
        specialTextViewLabel.setText(timelineItem.getSpecialLabelText());
        specialTextViewLabel.setTextSize(12);
        specialTextViewLabel.setTypeface(robotoBold_font);
        /// Special Value
        specialTextViewValue.setText(timelineItem.getSpecialValueText());
        specialTextViewValue.setTypeface(robotoBold_font);
        return customRow;
    }//end getView

}//end class TimelineAdapter
