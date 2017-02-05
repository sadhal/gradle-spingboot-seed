package se.sadhal;

import org.bson.Document;

import java.util.Date;

public class Person {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String twitterHandle;
    private Date createdOn = new Date();

    public Person() {}

    public Person(Document document) {
        this.id = document.getObjectId("_id").toString();
        this.firstName = document.getString("firstName");
        this.lastName = document.getString("lastName");
        this.email = document.getString("email");
        this.twitterHandle = document.getString("twitterHandle");
        this.createdOn = document.getDate("createdOn");
    }

    public Document createDocument() {
        Document doc = new Document("firstName", getFirstName())
                .append("lastName", getLastName())
                .append("email", getEmail())
                .append("twitterHandle", getTwitterHandle())
                .append("createdOn", getCreatedOn());

        return doc;
    }

    public void setId(String id) { this.id = id; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setTwitterHandle(String twitterHandle) { this.twitterHandle = twitterHandle; }
    public void setCreatedOn(Date date) { this.createdOn = date; }

    public String getId() { return id; }
    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }
    public Date getCreatedOn() {
        return createdOn;
    }
    public String getEmail() { return email; }
    public String getTwitterHandle() { return twitterHandle; }
}
