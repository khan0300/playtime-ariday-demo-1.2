package com.algonquincollege.ari.playtimearidaydemo;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.algonquincollege.ari.playtimearidaydemo.anim.ResizeAnimation;
import com.algonquincollege.ari.playtimearidaydemo.data.Challenge;
import com.algonquincollege.ari.playtimearidaydemo.data.ChallengeTimelineItem;
import com.algonquincollege.ari.playtimearidaydemo.data.DemoModel;
import com.algonquincollege.ari.playtimearidaydemo.data.TPAppUsage;
import com.algonquincollege.ari.playtimearidaydemo.data.TPAppUsageTimelineItem;
import com.algonquincollege.ari.playtimearidaydemo.data.TimelineItem;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeSet;

/**
 * Created by Ammar on 2015-08-12.
 */
public class HomePage extends Fragment{
    
    ListView timelineListView;

    ArrayList<TimelineItem> timelineItemListAll;
    ListAdapter timelineListAllAdapter;

    ArrayList<TimelineItem> timelineItemListTimeWaster;
    ListAdapter timelineListTimeWasterAdapter;

    ArrayList<TimelineItem> timelineItemListProductive;
    ListAdapter timelineListProductiveAdapter;

    FilterButtonBar filterButtonBar;

    /**
     * Constructor
     */
    public HomePage() {

        // Instantiate the ArrayLists that will be holding the TimelineItems
        timelineItemListAll = new ArrayList<>();
        timelineItemListTimeWaster = new ArrayList<>();
        timelineItemListProductive = new ArrayList<>();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ////////////////////
        // begin TIMELINE //

        // Get reference to the model's data collections
        ArrayList<TPAppUsage> tpAppUsages = DemoModel.getInstance().getTpAppUsages();
        ArrayList<Challenge> challenges = DemoModel.getInstance().getChallenges();

        // Generate new Timeline Items and add them to the TimelineItem lists
        Random r = new Random();
        /// TPAppUsage TimelineItems
        DateTime lastOccurrenceTime = DateTime.now();
        for (TPAppUsage tpAppUsage : tpAppUsages) {
            DateTime timePassedInterval = lastOccurrenceTime.minusMinutes((tpAppUsage.getTimeSpentSeconds()/60)+((r.nextInt(10) < 9)?r.nextInt(4):r.nextInt(5)+2));
            TPAppUsageTimelineItem item = new TPAppUsageTimelineItem(timePassedInterval, tpAppUsage);
            lastOccurrenceTime = timePassedInterval;
            // Add item to ALL list
            timelineItemListAll.add(item);
            // Add item reference to Timewaster list or Productive list
            if (tpAppUsage.getUserTPApp().isTimeWaster()) {
                timelineItemListTimeWaster.add(item);
            } else {
                timelineItemListProductive.add(item);
            }
        }
        /// Challenge TimelineItems
        Duration duration = new Duration(lastOccurrenceTime, DateTime.now());
        for (Challenge challenge : challenges) {
            timelineItemListAll.add(new ChallengeTimelineItem(DateTime.now().minusMinutes(r.nextInt(duration.toStandardMinutes().getMinutes())), challenge));
        }

        // Sort the TimelineItem lists by occurrence time
        Collections.sort(timelineItemListAll, TimelineItem.orderByOccurrenceTime);
        Collections.sort(timelineItemListTimeWaster, TimelineItem.orderByOccurrenceTime);
        Collections.sort(timelineItemListProductive, TimelineItem.orderByOccurrenceTime);

        // Set the ListView's items to listen for onItemClick events
        timelineListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Toast.makeText(getActivity(), item, Toast.LENGTH_LONG).show();
                        TimelineItem item = (TimelineItem) parent.getItemAtPosition(position);
//                        Toast.makeText(getActivity(), Integer.toString(view.getLayoutParams().height), Toast.LENGTH_SHORT).show();
                        int collapsedHeight = (int) getResources().getDimension(R.dimen.timeline_row_height_collapsed);
                        int expandedHeight = (int) getResources().getDimension(R.dimen.timeline_row_height_expanded);
                        int currentHeight = view.getLayoutParams().height;
                        int targetHeight = (currentHeight == collapsedHeight) ? expandedHeight : collapsedHeight;
                        // Transform the view with an animation
                        ResizeAnimation resizeAnimation = new ResizeAnimation(view, view.getWidth(), targetHeight);
                        resizeAnimation.setDuration(210);
                        view.startAnimation(resizeAnimation);
                    }
                }
        );

        // end TIMELINE //
        //////////////////

    }//end onActivityCreated

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Instantiate ListAdapters by passing the context Activity and ArrayList of TimelineItems
        timelineListAllAdapter = new TimelineAdapter(getActivity(), timelineItemListAll);
        timelineListTimeWasterAdapter = new TimelineAdapter(getActivity(), timelineItemListTimeWaster);
        timelineListProductiveAdapter = new TimelineAdapter(getActivity(), timelineItemListProductive);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        /// FilterButtonBar
        filterButtonBar = new FilterButtonBar(view);

        // Get the ListView element from XML documents
        timelineListView = (ListView) view.findViewById(R.id.timelineListView);

        // Initialize view mode to ALL by default
        filterButtonBar.changeViewModeToReflectState(TimelineFilter.STATE_ALL);

        return view;
    }

    /**
     * Filter Button Bar.
     */
    private class FilterButtonBar {

        public final int COLOR_TEXT_FILTER_ON;
        public final int COLOR_TEXT_FILTER_OFF;

        public final Button BUTTON_SHOW_ALL;
        public final Button BUTTON_SHOW_TIME_WASTER;
        public final Button BUTTON_SHOW_PRODUCTIVE;

        Resources res = getActivity().getResources();

        /**
         * Constructor
         */
        protected FilterButtonBar(View rootView) {

            COLOR_TEXT_FILTER_ON          = res.getColor(R.color.white);
            COLOR_TEXT_FILTER_OFF         = res.getColor(R.color.content_deep_navy);

            ////////////////
            // begin INIT //

            // Configure filterAllButton onClick
            BUTTON_SHOW_ALL = (Button) rootView.findViewById(R.id.homeFilterAllButton);
            BUTTON_SHOW_ALL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TimelineFilter.getState().equals(TimelineFilter.STATE_ALL)) {
                        changeViewModeToReflectState(TimelineFilter.STATE_ALL);
                    }
                }
            });
            // Configure filterTimeWasterButton onClick
            BUTTON_SHOW_TIME_WASTER = (Button) rootView.findViewById(R.id.homeFilterTimeWasterButton);
            BUTTON_SHOW_TIME_WASTER.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TimelineFilter.getState().equals(TimelineFilter.STATE_TIME_WASTER)) {
                        changeViewModeToReflectState(TimelineFilter.STATE_TIME_WASTER);
                    }
                }
            });
            // Configure filterProductiveButton onClick
            BUTTON_SHOW_PRODUCTIVE = (Button) rootView.findViewById(R.id.homeFilterProductiveButton);
            BUTTON_SHOW_PRODUCTIVE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TimelineFilter.getState().equals(TimelineFilter.STATE_PRODUCTIVE)) {
                        changeViewModeToReflectState(TimelineFilter.STATE_PRODUCTIVE);
                    }
                }
            });

            // Set button typefaces
            BUTTON_SHOW_ALL.setTypeface(((MainActivity) getActivity()).FONT_HOUSE_SLANT);
            BUTTON_SHOW_TIME_WASTER.setTypeface(((MainActivity) getActivity()).FONT_HOUSE_SLANT);
            BUTTON_SHOW_PRODUCTIVE.setTypeface(((MainActivity) getActivity()).FONT_HOUSE_SLANT);

            // end INIT //
            //////////////

        }//end constructor

        protected void changeViewModeToReflectState(TimelineFilter.State state) {
            TimelineFilter.setState(state);

            boolean ALL = TimelineFilter.getState().equals(TimelineFilter.STATE_ALL);
            boolean TW = TimelineFilter.getState().equals(TimelineFilter.STATE_TIME_WASTER);
            boolean PRODUCTIVE = TimelineFilter.getState().equals(TimelineFilter.STATE_PRODUCTIVE);

            BUTTON_SHOW_ALL.setTextColor((ALL) ? COLOR_TEXT_FILTER_ON : COLOR_TEXT_FILTER_OFF);
            BUTTON_SHOW_ALL.setBackgroundResource((ALL) ? R.drawable.home_filterbuttonon : R.drawable.home_filterbuttonoff);
            BUTTON_SHOW_TIME_WASTER.setTextColor((TW) ? COLOR_TEXT_FILTER_ON : COLOR_TEXT_FILTER_OFF);
            BUTTON_SHOW_TIME_WASTER.setBackgroundResource((TW) ? R.drawable.home_filterbuttonon : R.drawable.home_filterbuttonoff);
            BUTTON_SHOW_PRODUCTIVE.setTextColor((PRODUCTIVE) ? COLOR_TEXT_FILTER_ON : COLOR_TEXT_FILTER_OFF);
            BUTTON_SHOW_PRODUCTIVE.setBackgroundResource((PRODUCTIVE) ? R.drawable.home_filterbuttonon : R.drawable.home_filterbuttonoff);

            // Set the adapter to the Listview;
            if (state.equals(TimelineFilter.STATE_ALL))
                timelineListView.setAdapter(timelineListAllAdapter);
            else if (state.equals(TimelineFilter.STATE_TIME_WASTER))
                timelineListView.setAdapter(timelineListTimeWasterAdapter);
            else
                timelineListView.setAdapter(timelineListProductiveAdapter);
        }

    }//end class FilterButtonBar

}//end class HomePage
