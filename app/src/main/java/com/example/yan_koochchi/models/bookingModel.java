package com.example.yan_koochchi.models;

public class bookingModel {
           String id;
           String from;
           boolean status;
           String to;
          String  date;
          int  count;
          String  train_Schedule;
           String user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTrain_Schedule() {
        return train_Schedule;
    }

    public void setTrain_Schedule(String train_Schedule) {
        this.train_Schedule = train_Schedule;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
