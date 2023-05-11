package com.example.unischedulebot.google;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GCalendarImpl implements GCalendar {

    private Calendar service;

    @SneakyThrows
    public GCalendarImpl() {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        List<String> SCOPES =
                Collections.singletonList(CalendarScopes.CALENDAR);


        GAuthorisation authorisation = new GAuthorisation();
        service =
                new Calendar.Builder(HTTP_TRANSPORT, authorisation.getJSON_FACTORY(), authorisation.getCredentials(SCOPES))
                        .setApplicationName("Google GCalendar API Java Quickstart")
                        .build();
    }

    @SneakyThrows
    public List<String> getEvents() {
        DateTime now = new DateTime(System.currentTimeMillis());

        // get all calendars
        Events events = service.events().list("nhavronskyi@gmail.com")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        List<Event> items = events.getItems();
        List<String> eventsList = new ArrayList<>();
        for (Event event : items) {
            DateTime start = event.getStart().getDateTime();
            if (start == null) {
                start = event.getStart().getDate();
            }
            eventsList.add(event.getSummary() + " " + start);
        }
        return eventsList;
    }

    @SneakyThrows
    public void createAnEvent(String title, String teacher, DateTime start, DateTime end) {
        Event event = new Event()
                .setSummary(title)
                .setDescription(teacher)
                .setStart(new EventDateTime().setTimeZone("Europe/Warsaw").setDateTime(start))
                .setEnd(new EventDateTime().setTimeZone("Europe/Warsaw").setDateTime(end));

        service.events().insert("nhavronskyi@gmail.com", event).execute();
    }
}
