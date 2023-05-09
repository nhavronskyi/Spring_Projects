package com.example.unischedulebot.google;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.Getter;

import java.io.*;
import java.util.List;
import java.util.Objects;

@Getter
public class GAuthorisation {
    private final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    public Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT, List<String> SCOPES) throws IOException {
        var is = GAuthorisation.class.getResourceAsStream("/credentials.json");
        return GoogleCredential.fromStream(Objects.requireNonNull(is))
                .createScoped(SCOPES);
    }
}
