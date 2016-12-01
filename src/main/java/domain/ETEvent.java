package domain;

import java.util.Date;

/**
 * Apathetic spawn of Wesb on 11/27/16.
 */
public class ETEvent {

    private String title;
    private Date startDate;
    private Date endDate;
    private String imageURL;
    private String organizer;
    private String description;
    private Venue venue;

    public ETEvent(String title, Date startDate, Date endDate, String organizer, String description) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageURL = null;
        this.organizer = organizer;
        this.description = description;
        this.venue = null;
    }

    public ETEvent(String title, Date startDate, Date endDate, String organizer, String description, Venue venue) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageURL = null;
        this.organizer = organizer;
        this.description = description;
        this.venue = venue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
