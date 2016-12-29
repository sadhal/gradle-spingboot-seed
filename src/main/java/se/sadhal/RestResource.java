package se.sadhal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestResource {

    private static final Logger LOG = LoggerFactory.getLogger(RestResource.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        LOG.info("/hello called");

        return "Hello, openshift origin!";
    }
    
    @RequestMapping(value = "/healthz", method = RequestMethod.GET)
    public String healthz() {
        LOG.info("/healthz called");
        return hello();
    }
}
