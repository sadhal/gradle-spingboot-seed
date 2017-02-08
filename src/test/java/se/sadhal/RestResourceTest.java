package se.sadhal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by sadmir on 2016-12-28.
 */
public class RestResourceTest {

    @Mock
    PersonService ps;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.Test
    public void test_hello() throws Exception {
        RestResource rr = new RestResource();
        assertEquals("Hello, world!", rr.hello());
    }

    @Test
    public void test_getPersoner() {
        RestResource rr = new RestResource();
        rr.setPersonService(ps);

        Mockito.when(ps.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<?> xs = rr.personer();
        Assert.assertTrue(xs.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void test_getPersonerFailure() {
        RestResource rr = new RestResource();
        rr.setPersonService(ps);

        Mockito.when(ps.findAll()).thenThrow(NullPointerException.class);
        ResponseEntity<?> xs = rr.personer();
        Assert.assertTrue(xs.getStatusCode().is5xxServerError());
    }

    @Test
    public void test_save() {
        RestResource rr = new RestResource();
        rr.setPersonService(ps);

        Mockito.when(ps.save(Mockito.any(Person.class))).thenReturn(new Person());

        Person person = new Person();
        person.setFirstName("f");
        person.setLastName("l");
        person.setEmail("e");
        ResponseEntity<?> xs = rr.save(person);
        Assert.assertTrue(xs.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void test_saveBadRequest() {
        RestResource rr = new RestResource();

        ResponseEntity<?> xs = rr.save(null);
        Assert.assertTrue(xs.getStatusCode().is4xxClientError());
    }

    @Test
    public void test_saveServerError() {
        RestResource rr = new RestResource();
        rr.setPersonService(ps);

        Mockito.when(ps.save(Mockito.any(Person.class))).thenThrow(NullPointerException.class);

        Person person = new Person();
        person.setFirstName("f");
        person.setLastName("l");
        person.setEmail("e");
        ResponseEntity<?> xs = rr.save(person);
        Assert.assertTrue(xs.getStatusCode().is5xxServerError());
    }
}
