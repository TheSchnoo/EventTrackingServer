package controller;

import domain.ETEvent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Apathetic spawn of Wesb on 11/11/16.
 */
public class DatabaseController {

    private static String MEEUTP_OPEN_EVENTS_URL = "https://api.meetup.com/2/open_events?";

    private Connection connection;
    private MeetupResponseConverter converter;

    public DatabaseController() {
        this.converter = new MeetupResponseConverter();
    }

    private void openConnection() {
        System.out.println(System.getenv("CLEARDB_DATABASE_URL"));
        try {
            URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();
            connection = DriverManager.getConnection(dbUrl, username, password);
        } catch (Exception e) {
            System.out.println("Issue");
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JSONObject searchEvents(String zip, String text) throws JSONException {
        String meetupUrl = MEEUTP_OPEN_EVENTS_URL + "key=" + System.getenv("MEETUP_KEY")
                + "&zip=" + zip + "&text=" + text;
        JSONArray eventsList = get(meetupUrl);
        ETEvent event = converter.convertMeetupEventToEvent((JSONObject) eventsList.get(0));
        return converter.ETEventToResponse(event);
    }

    private static JSONArray get(String targetURL) {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            connection.getResponseMessage();

            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            List<String> list = new ArrayList<>();
            String line;
            while ((line = rd.readLine()) != null) {
                list.add(line);
            }
            rd.close();
            JSONObject jsonObject = new JSONObject(list.get(0));
            JSONArray obj = (JSONArray) jsonObject.get("results");
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
