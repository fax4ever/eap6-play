package it.redhat.demo.rest;

import it.redhat.demo.jaxb.JaxbContextFactory;
import it.redhat.demo.model.Clue;
import it.redhat.demo.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 18/07/16
 */

@Path("")
public class RestService {

    Logger log = LoggerFactory.getLogger(RestService.class);

    @GET
    public String ciao() {
        log.info("ciao");
        return "ciao";
    }

    @GET
    @Path("task")
    @Produces({ "application/xml" })
    public Task task() {
        Task task = getTask();

        return task;
    }

    @GET
    @Path("jaxb/task")
    @Produces({ "application/xml" })
    public String jaxbTask() {
        Task task = getTask();

        StringWriter w = new StringWriter();
        JAXB.marshal(task, w);

        return w.toString();
    }

    @GET
    @Path("clue")
    @Produces({ "application/xml" })
    public Clue clue() {
        Clue clue = getClue();

        return clue;
    }

    @GET
    @Path("valid")
    @Produces({ "application/xml" })
    public String validJaxbContext() throws JAXBException {

        Task task = getTask();

        JaxbContextFactory contextFactory = new JaxbContextFactory();
        JAXBContext jaxbContext = contextFactory.buildOk();

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter w = new StringWriter();
        marshaller.marshal(task, w);

        return w.toString();

    }

    @GET
    @Path("invalid")
    @Produces({ "application/xml" })
    public String invalidJaxbContext() throws JAXBException {

        Clue clue = getClue();

        JaxbContextFactory contextFactory = new JaxbContextFactory();
        JAXBContext jaxbContext = contextFactory.buildKo();

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter w = new StringWriter();
        marshaller.marshal(clue, w);

        return w.toString();

    }

    private Task getTask() {
        Task task = new Task();
        task.setId(1l);
        task.setTitle("My Task");
        return task;
    }

    private Clue getClue() {
        Clue clue = new Clue();
        clue.setUpper("Going ...");
        clue.setDecision(7l);
        return clue;
    }


}
