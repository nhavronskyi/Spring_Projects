package com.example.unischedulebot.google;

import com.google.api.client.util.DateTime;

import java.util.List;

public interface GCalendar {
    List<String> getEvents();

    void createAnEvent(String title, String teacher, DateTime start, DateTime end);
}
