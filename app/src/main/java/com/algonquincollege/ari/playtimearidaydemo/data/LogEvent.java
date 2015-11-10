package com.algonquincollege.ari.playtimearidaydemo.data;

import java.util.Calendar;

/**
 * Created by Cong on 8/14/2015.
 */
public class LogEvent extends SerializableEntity {

    private Calendar occurrenceDate;
    public Calendar getOccurrenceDate() { return occurrenceDate; }
    protected void setOccurrenceDate(Calendar occurrenceDate) { this.occurrenceDate = occurrenceDate; }

    private String note;
    public String getNote() { return note; }
    protected void setNote(String note) { this.note = note; }

    protected LogEvent(Calendar occurrenceDate, String note) {
        setOccurrenceDate(occurrenceDate);
        setNote(note);
    }

    @Override
    public String toJSONString() {
        return null; //TODO
    }

}//end class LogEvent
