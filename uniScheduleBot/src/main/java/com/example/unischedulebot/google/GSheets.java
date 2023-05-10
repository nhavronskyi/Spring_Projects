package com.example.unischedulebot.google;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;


@Service
public class GSheets {

    public Map<String, List<String>> getSchedule() {
        return Map.of(
                "MONDAY", dayManager("A3:D8"),
                "TUESDAY", dayManager("A10:D11"),
                "WEDNESDAY", dayManager("A13:D14"),
                "FRIDAY", dayManager("A16:D18")
        );
    }

    @SneakyThrows
    private List<String> dayManager(String range) {
        List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        final String spreadsheetId = "1iaOhFr9FQP_NGF4y2lnrjj3OAbAm7dyMYWl7pb4d4fs";
        String APPLICATION_NAME = "sheets-project-386108";

        GAuthorisation autorisation = new GAuthorisation();

        Sheets service =
                new Sheets.Builder(HTTP_TRANSPORT, autorisation.getJSON_FACTORY(), autorisation.getCredentials(SCOPES))
                        .setApplicationName(APPLICATION_NAME)
                        .build();
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();

        return response.getValues().stream().map(Object::toString).toList();
    }
}