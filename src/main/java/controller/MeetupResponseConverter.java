package controller;

import domain.ETEvent;
import domain.Venue;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Apathetic spawn of Wesb on 11/27/16.
 */
public class MeetupResponseConverter {

    public ETEvent convertMeetupEventToEvent(JSONObject meetupEvent) {
        try {
            String title = meetupEvent.getString("name");
            Date startDate = new Date(meetupEvent.getLong("time"));
            Date endDate = null;
            if (meetupEvent.has("duration")) {
                endDate = new Date(meetupEvent.getLong("time") + meetupEvent.getLong("duration"));
            }

            JSONObject groupObj = meetupEvent.getJSONObject("group");
            String organizer = groupObj.getString("name");
            String description = meetupEvent.getString("description");

            JSONObject venueObj = meetupEvent.getJSONObject("venue");
            Venue venue = convertVenueObjToVenue(venueObj);

            return new ETEvent(title, startDate, endDate, organizer, description, venue);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Venue convertVenueObjToVenue(JSONObject venueObj) throws JSONException {

        int id = venueObj.getInt("id");
        String name = venueObj.getString("name");
        float lat = Float.parseFloat(venueObj.getString("lat"));
        float lon = Float.parseFloat(venueObj.getString("lon"));
        String address = venueObj.getString("address_1");
        String city = venueObj.getString("city");
        String country = venueObj.getString("country");
        String state = venueObj.getString("state");

        return new Venue(id, name, lat, lon, address, city, country, state);
    }


    public JSONObject ETEventToResponse(ETEvent eTEvent) throws JSONException {
        JSONObject eventObj = new JSONObject();
        eventObj.put("title", eTEvent.getTitle());
        eventObj.put("startDate", eTEvent.getStartDate());
        eventObj.put("endDate", eTEvent.getEndDate());
        eventObj.put("organizer", eTEvent.getOrganizer());
        eventObj.put("description", eTEvent.getDescription());
        eventObj.put("venue", convertVenueToJsonObj(eTEvent.getVenue()));

        return eventObj;
    }

    private Object convertVenueToJsonObj(Venue venue) throws JSONException {
        JSONObject venueObj = new JSONObject();
        venueObj.put("id", venue.getId());
        venueObj.put("name", venue.getName());
        venueObj.put("lat", venue.getLat());
        venueObj.put("lon", venue.getLon());
        venueObj.put("address", venue.getAddress());
        venueObj.put("city", venue.getCity());
        venueObj.put("country", venue.getCountry());
        venueObj.put("state", venue.getState());

        return venueObj;
    }
}
