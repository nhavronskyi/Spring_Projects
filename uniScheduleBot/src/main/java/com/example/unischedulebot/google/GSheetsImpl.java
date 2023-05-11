package com.example.unischedulebot.google;

import com.example.unischedulebot.exception.SheetsException;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;


@Service
@AllArgsConstructor
public class GSheetsImpl implements GSheets {

    private GoogleProps googleProps;

    public LinkedHashMap<String, List<String>> getSchedule() {
        LinkedHashMap<String, List<String>> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("MONDAY", dayManager("A3:D8"));
        linkedHashMap.put("TUESDAY", dayManager("A10:D11"));
        linkedHashMap.put("WEDNESDAY", dayManager("A13:D14"));
        linkedHashMap.put("FRIDAY", dayManager("A16:D18"));
        return linkedHashMap;
    }


    private List<String> dayManager(String range) {
        try {
            List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            final String spreadsheetId = googleProps.getSheetId();
            String APPLICATION_NAME = "sheets-project-386108";

            GAuthorisation authorisation = new GAuthorisation();

            Sheets service =
                    new Sheets.Builder(HTTP_TRANSPORT, authorisation.getJSON_FACTORY(), authorisation.getCredentials(SCOPES))
                            .setApplicationName(APPLICATION_NAME)
                            .build();
            ValueRange response = service.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
            return response.getValues().stream().map(Object::toString).toList();
        } catch (Exception e) {
            new SheetsException("day manager exception", e).printStackTrace();
            return null;
        }
    }
}