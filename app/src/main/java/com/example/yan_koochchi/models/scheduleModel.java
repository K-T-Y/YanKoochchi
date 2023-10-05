package com.example.yan_koochchi.models;

public class scheduleModel {

    String id;
    String departure;
    String designation;
    String stations;
    String days;
    String startingTimes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getStations() {
        return stations;
    }

    public void setStations(String stations) {
        this.stations = stations;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getStartingTimes() {
        return startingTimes;
    }

    public void setStartingTimes(String startingTimes) {
        this.startingTimes = startingTimes;
    }
}
