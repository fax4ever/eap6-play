package it.redhat.demo.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import t.redhat.demo.model.Person;

import javax.jws.WebService;
import java.util.Date;
import java.util.List;

/**
 * Created by fabio.ercoli@redhat.com on 10/05/17.
 */
@WebService
public class PersonWS {

    private static final Logger LOG = LoggerFactory.getLogger(PersonWS.class);

    public String printInfos(List<Person> people, Date moment, Integer times) {

        if (times == null) {
            LOG.warn("times is null!");
            return "times is null!";
        }

        if (people == null) {
            LOG.warn("people is null!");
            return "people is null!";
        }

        if (people.isEmpty()) {
            LOG.warn("people is empty!");
            return "people is empty!";
        }

        if (moment == null) {
            LOG.warn("moment is null!");
            return "moment is null!";
        }

        StringBuilder builder = new StringBuilder();
        builder.append(moment);
        builder.append(" --> {");

        for (int i=0; i<times; i++) {
            for (Person person : people) {
                builder.append(person);
            }
        }

        builder.append(" }");

        return builder.toString();
    }

}
