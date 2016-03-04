package edu.umich.hwf.persistence;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.drive.Drive;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mdemerat on 3/4/16.
 */
public class GoogleStorage implements Persistence {

    public boolean addQuestionAnswer(String name, String value) {
        return false;
    }

    public boolean persistQuestion(String name, String value) {
        GoogleServiceAccount account = new GoogleServiceAccount("127.0.0.1");
        GoogleCredential creds = GoogleSecurity.authorize(account, "rad-hwf-2016@hwf-survey.iam.gserviceaccount.com");
        Drive drive = GoogleSecurity.getGoogleDrive(creds);
        //drive.
        return false;
    }
    public void persistQuestions(Map<String, String> responses) {
    }

    public void registerQuestionAndAnswer(String name, String value) {

    }

    public boolean persistQuestion(String name, String value, boolean isFreeformAnswer) {
        return false;
    }

    public Map<String, Integer> getQuestionResults(String name) {
        return null;
    }

    public Map<String, HashMap<String, Integer>> getFullResults() {
        return null;
    }

    public static void main(String[] args) {
        GoogleStorage s = new GoogleStorage();
        s.persistQuestion("x", "y");
    }
}
