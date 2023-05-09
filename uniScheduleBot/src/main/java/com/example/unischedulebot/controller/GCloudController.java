package com.example.unischedulebot.controller;

import com.example.unischedulebot.google.GCalendar;
import com.example.unischedulebot.google.GSheets;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
