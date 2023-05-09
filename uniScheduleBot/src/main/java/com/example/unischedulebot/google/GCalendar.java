package com.example.unischedulebot.google;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GCalendar {
    @SneakyThrows
    public List<String> getEvents(){
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        List<String> SCOPES =
                Collections.singletonList(CalendarScopes.CALENDAR_READONLY);


        GAuthorisation authorisation = new GAuthorisation();
        Calendar service =
                new Calendar.Builder(HTTP_TRANSPORT, authorisation.getJSON_FACTORY(), authorisation.getCredentials(HTTP_TRANSPORT, SCOPES))
                        .setApplicationName("Google GCalendar API Java Quickstart")
                        .build();

        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("primary")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();
        List<String> eventsList = new ArrayList<>();
        if (items.isEmpty()) return List.of("No upcoming events found.");
        else {
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                eventsList.add(event.getSummary() + " " + start);
            }
        }
        return eventsList;
    }
}
