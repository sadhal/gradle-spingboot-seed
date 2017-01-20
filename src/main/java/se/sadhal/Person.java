package se.sadhal;

import org.bson.Document;

import java.util.Date;

/**
 * Created by sadmir on 2017-01-12.
 */
public class Person {
    private String id;
    private String name;
    private String username;
    private Date createdOn = new Date();

    public Person(Document document) {
        this.id = document.getObjectId("_id").toString();
        //this.id = ((ObjectId) dbObject.get("_id")).toString();
        this.name = document.getString("name");
        this.username = document.getString("username");
        this.createdOn = document.getDate("createdOn");
    }

    public String getName() { return this.name; }
    public String getUsername() { return this.username; }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Document getDocument() {
        Document doc = new Document("name", getName()).
                append("username", getUsername()).
                append("createdOn", getCreatedOn());

        return doc;
    }
}
