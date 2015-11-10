package com.algonquincollege.ari.playtimearidaydemo;

import android.widget.Button;

import com.algonquincollege.ari.playtimearidaydemo.data.StateProperty;

/**
 * Created by Cong on 8/19/2015.
 */


public class TimelineFilter {

    public static final State STATE_ALL         = new State(State.ALL);
    public static final State STATE_TIME_WASTER = new State(State.TIME_WASTER);
    public static final State STATE_PRODUCTIVE  = new State(State.PRODUCTIVE);

    public static class State extends StateProperty<Character> {

        public static final char ALL = 'a';
        public static final char TIME_WASTER = 'w';
        public static final char PRODUCTIVE = 'p';

        private static final Character[] states = {ALL, TIME_WASTER, PRODUCTIVE};

        private State(char initialState) {
            super(states, initialState);
        }
    }

    private static State state;
    public static State getState() { return state; }
    protected static void setState(State state) {
        TimelineFilter.state = state;
    }

}//end class TimelineFilter