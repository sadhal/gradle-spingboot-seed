package se.sadhal;

import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sadmir on 2017-01-12.
 */
public class PersonService {

    private final MongoDatabase db;
    private final MongoCollection<Document> collection;

    public PersonService(MongoDatabase db) {
        this.db = db;
        this.collection = db.getCollection("personer");
    }

    public List<Person> findAll() {
        List<Person> personer = new ArrayList<>();

        /*
        List<Document> documents;
        documents = (List<Document>)collection.find().into(new ArrayList<>());

        personer = documents.
                stream().
                map(document -> new Person(document)).
                collect(Collectors.toList());
        */

        collection.find().forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                personer.add(new Person(document));
            }
        });

        return personer;
    }

    public void save(Person person) {
        Document doc = person.getDocument();
        collection.insertOne(doc);
    }
}
