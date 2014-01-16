package org.baharan.model.line;

import org.baharan.model.BaseEntity;
import org.baharan.model.station.Station;


public class LineStation extends BaseEntity {
	
	private Line line;
	private Station station;
	private int ordering;
	private int goBack;
	private int betweenTimeMinute;
	
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	public Station getStation() {
		return station;
	}
	public void setStation(Station station) {
		this.station = station;
	}
	public int getOrdering() {
		return ordering;
	}
	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}
	public int getGoBack() {
		return goBack;
	}
	public void setGoBack(int goBack) {
		this.goBack = goBack;
	}
	public int getBetweenTimeMinute() {
		return betweenTimeMinute;
	}
	public void setBetweenTimeMinute(int betweenTimeMinute) {
		this.betweenTimeMinute = betweenTimeMinute;
	}
	

	
	
}
