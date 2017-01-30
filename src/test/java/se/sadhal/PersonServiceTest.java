package se.sadhal;

import com.github.fakemongo.Fongo;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertEquals("sadhal", personer.get(0).getUsername());
    }



    private static MongoDatabase mongo() throws Exception {
        Fongo fongo = new Fongo("mongo server 1");

        MongoDatabase database = fongo.getDatabase("sampledb");
        MongoCollection coll = database.getCollection("personer");
        Document doc = new Document("name","Sadmir").append("username","sadhal");
        coll.insertOne(doc);

        return database;
    }
}
