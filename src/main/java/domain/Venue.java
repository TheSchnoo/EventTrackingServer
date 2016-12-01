package domain;

/**
 * Apathetic spawn of Wesb on 11/27/16.
 */
public class Venue {

    private int id;
    private String name;
    private float lat;
    private float lon;
    private String address;
    private String city;
    private String country;
    private String state;

    public Venue(int id, String name, float lat, float lon, String address, String city, String country, String state) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
        this.city = city;
        this.country = country;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
