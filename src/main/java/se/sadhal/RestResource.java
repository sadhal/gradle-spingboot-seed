package se.sadhal;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
public class RestResource {

    private static final Logger LOG = LoggerFactory.getLogger(RestResource.class);

    private PersonService personService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        LOG.info("/hello called");

        return "Hello, openshift developer preview (NextGen)!";
    }

    @RequestMapping(value = "/healthz", method = RequestMethod.GET)
    public String healthz() {
        LOG.info("/healthz called");

        String host = System.getenv("MONGODB_SERVICE_HOST");
        String port = System.getenv("MONGODB_SERVICE_PORT");

        LOG.info("mongo host {}, port {}", host, port);
        if (host == null || port == null) {
            LOG.error("mongodb host eller port env var saknas");
            throw new RuntimeException("mongodb host eller port env var saknas");
        }

        return "hello from /healthz endpoint";
    }

    public PersonService getPersonService() throws Exception {
        if (personService != null)
            return personService;

        return new PersonService(mongo());
    }

    @RequestMapping(value = "/personer", method = RequestMethod.GET)
    public ResponseEntity<?> personer() {
        LOG.info("/personer called");


        try {
            LOG.info("fetching personer från mongodb");
            List<Person> personer = getPersonService().findAll();

            HttpHeaders hh = new HttpHeaders();
            hh.set("MyLocation", "http://example.com/some/uri");
            return new ResponseEntity<List<Person>>(personer, hh, HttpStatus.OK);

        } catch (Exception e) {
            LOG.error("Ett fel inträffade", e);
            return new ResponseEntity<String>(e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static MongoDatabase mongo() throws Exception {
        String host = System.getenv("MONGODB_SERVICE_HOST");
        int port = Integer.parseInt(System.getenv("MONGODB_SERVICE_PORT"));

        LOG.info("mongo host {}, port {}", host, port);
        if (host == null || port < 1) {
            LOG.error("mongodb host eller port env var saknas");
            throw new RuntimeException("mongodb host eller port env var saknas");
        }


        // should inject from application.yml
        String dbname = System.getenv("MONGODB_DATABASE");
        dbname = dbname != null ? dbname : "sampledb";

        String username = System.getenv("MONGODB_USER");
        username = username != null ? username : "sadhal";

        String password = System.getenv("MONGODB_PASSWORD");
        password = password != null ? password : "sadhal";

        LOG.info("mongo port {}, dbname {}, username {}, password {}", port, dbname, username, password);
        MongoCredential credential = MongoCredential.createCredential(username, dbname, password.toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port),
                Arrays.asList(credential));
        //mongoClient.setWriteConcern(WriteConcern.SAFE);
        MongoDatabase db = mongoClient.getDatabase(dbname);
        return db;
    }
}
