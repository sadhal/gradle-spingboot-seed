package se.sadhal;

import com.github.fakemongo.Fongo;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class PersonServiceTest {

    @Test
    public void test_findAll() throws Exception{
        MongoDatabase db = mongo();
        PersonService service = new PersonService(db);
        List<Person> personer = service.findAll();
        Assert.assertEquals(1, personer.size());
        Assert.assertEquals("sadhal", personer.get(0).getLastName());
    }

    @Test
    public void test_save() throws Exception{
        MongoDatabase db = mongo();
        PersonService service = new PersonService(db);
        List<Person> personer = service.findAll();
        Assert.assertEquals(1, personer.size());
        Document document = new Document("firstName", "Rimdas")
                .append("lastName", "halsad")
                .append("_id", new ObjectId(new Date(), 2));
        Person person = new Person(document);
        service.save(person);
        personer = service.findAll();
        Assert.assertEquals(2, personer.size());
        Assert.assertEquals("halsad", personer.get(1).getLastName());
    }

    private static MongoDatabase mongo() throws Exception {
        Fongo fongo = new Fongo("mongo server 1");

        MongoDatabase database = fongo.getDatabase("sampledb");
        MongoCollection coll = database.getCollection("personer");
        Document doc = new Document("firstName","Sadmir").append("lastName","sadhal");
        coll.insertOne(doc);

        return database;
    }
}
