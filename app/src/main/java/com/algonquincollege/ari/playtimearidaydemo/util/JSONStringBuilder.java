package com.algonquincollege.ari.playtimearidaydemo.util;

import org.joda.time.DateTime;

import java.util.LinkedHashMap;
import java.util.Map;

public class JSONStringBuilder {
	
	private LinkedHashMap<String, Object> pairs;
	private StringBuilder sb;
	
	public JSONStringBuilder() {
		sb = new StringBuilder();
		pairs = new LinkedHashMap<String, Object>();
	}
	
	public JSONStringBuilder add(String field, Character value) {
		pairs.put(field, Character.toString(value));
		return this;
	}
	
	public JSONStringBuilder add(String field, boolean value) {
		pairs.put(field, (value) ? "true" : "false");
		return this;
	}
	
	public JSONStringBuilder add(String field, String value) {
		pairs.put(field, value);
		return this;
	}
	
	public JSONStringBuilder add(String field, Number value) {
		pairs.put(field, value);
		return this;
	}
	
	public JSONStringBuilder add(String field, DateTime value) {
		pairs.put(field, String.format("%d–%d–%d", value.getYear(), value.getMonthOfYear(), value.getDayOfMonth()));
		return this;
	}
	
	@Override
	public String toString() {
		sb.append("{");
		int count = 0;
		for (@SuppressWarnings("rawtypes") Map.Entry entry : pairs.entrySet()) {
			sb.append("'").append(entry.getKey()).append("':'").append(entry.getValue()).append("'");
			count++;
			if (count < pairs.size()) 
				sb.append(",");
		}
		sb.append("}");
		return sb.toString();
	}
	
}//end JSONBuilder
