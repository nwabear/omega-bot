package com.nwabear.discord;

public class Reminder {
    public String reminder_id;
    public String user_id;
    public String reminder_text;
    public String reminder_date;

    public void print() {
        System.out.println(reminder_id + ", " + user_id + ", " + reminder_text + ", " + reminder_date);
    }
}
