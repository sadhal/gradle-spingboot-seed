package se.sadhal;

import static org.junit.Assert.*;

/**
 * Created by sadmir on 2016-12-28.
 */
public class RestResourceTest {
    @org.junit.Test
    public void hello() throws Exception {
        RestResource rr = new RestResource();
        assertEquals("Hello, openshift developer preview (NextGen)!", rr.hello());
    }

}
