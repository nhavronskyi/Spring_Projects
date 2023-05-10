package com.example.unischedulebot.service;

import com.example.unischedulebot.google.GCalendar;
import com.example.unischedulebot.google.GSheets;
import com.google.api.client.util.DateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
@AllArgsConstructor
public class GService {

    private GSheets sheets;

    private GCalendar calendar;

    public Map<String, List<String>> getSchedule() {
        return sheets.getSchedule();
    }

    public List<String> getEvents() {
        return calendar.getEvents();
    }

    public void createDemoEvent() {
        calendar.createAnEvent("demo", "demo", new DateTime("2023-05-10T09:00:00"), new DateTime("2023-05-10T10:00:00"));
    }

    public void creatEventsForSchedule() {
        LocalDate localDate = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        for (Map.Entry<String, List<String>> stringListEntry : sheets.getSchedule().entrySet()) {

            LocalDate lDate = localDate.with(TemporalAdjusters.next(DayOfWeek.valueOf(stringListEntry.getKey())));

            for (String lessonLine : stringListEntry.getValue()) {
                String[] strings = lessonLine
                        .replaceAll("\\[", "")
                        .split("]");

                for (String lesson : List.of(strings)) {
                    Object[] data = Arrays.stream(lesson.split(",")).map(String::trim).toArray();

                    String title = data[0] + " " + data[2];
                    String teacher = data[3].toString();


                    Object[] time = Arrays.stream(data[1].toString().split("-")).map(String::trim).toArray();

                    calendar.createAnEvent(title
                            , teacher
                            , new DateTime(lDate + "T" + time[0].toString() + ":00")
                            , new DateTime(lDate + "T" + time[1].toString() + ":00"));
                }
            }
        }
    }
}