package com.example.unischedulebot.controller;

import com.example.unischedulebot.google.GCalendar;
import com.example.unischedulebot.google.GSheets;
import com.google.api.client.util.DateTime;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping
public class GCloudController {
    private GSheets sheets;

    private GCalendar calendar;

    @GetMapping("/sheets")
    public Map<String, List<String>> getSchedule(){
        return sheets.getSchedule();
    }

    @GetMapping("/calendar")
    public List<String> getEvents(){
        return calendar.getEvents();
    }

    @PostMapping("/ivent")
    public void createEvent(){
        calendar.createAnEvent("demo", "demo", new DateTime("2023-05-10T09:00:00"), new DateTime("2023-05-10T10:00:00"));
    }

    @PostMapping
    public void creatEventsForSheets(){
        LocalDate localDate = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        for (Map.Entry<String, List<String>> stringListEntry : sheets.getSchedule().entrySet()) {

            LocalDate lDate = localDate.with(TemporalAdjusters.next(DayOfWeek.valueOf(stringListEntry.getKey())));

            for (String lessonLine :stringListEntry.getValue()) {
                String[] strings = lessonLine
                        .replaceAll("\\[", "")
                        .split("]");

                for (String lesson : List.of(strings)) {
                    Object[] data = Arrays.stream(lesson.split(",")).map(String::trim).toArray();

                    String title = data[0] + " " + data[2];
                    String teacher = data[3].toString();


                    Object[] time = Arrays.stream(data[1].toString().split("-")).map(String::trim).toArray();

                    calendar.createAnEvent(title
                            ,teacher
                            ,new DateTime( lDate + "T"+ time[0].toString()  +":00")
                            ,new DateTime(lDate + "T"+ time[1].toString()  +":00"));
                }
            }
        }
    }
}
