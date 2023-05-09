package com.example.googlesheetsdemo.controller;

import com.example.googlesheetsdemo.google.SheetsQuickstart;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class DashboardController {
    private SheetsQuickstart sheetsQuickstart;

    @GetMapping
    public Map<String, List<String>> getSchedule(){
        return sheetsQuickstart.getSchedule();
    }
}
