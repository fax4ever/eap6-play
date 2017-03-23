package it.redhat.demo.jaxb;

import it.redhat.demo.model.Clue;
import it.redhat.demo.model.Task;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fabio.ercoli@redhat.com on 23/03/17.
 */
public class JaxbContextFactory {

    private static final Class<?>[] VALID_CTX;
    private static final Class<?>[] INVALID_CTX;

    static {

        VALID_CTX = new Class<?>[]{
            Task.class
        };

        INVALID_CTX = new Class<?>[]{
            Clue.class
        };

    }

    public JAXBContext buildOk() throws JAXBException {

        return build(VALID_CTX);

    }

    public JAXBContext buildKo() throws JAXBException {

        return build(INVALID_CTX);

    }

    private JAXBContext build(Class<?>[] classArray) throws JAXBException {

        List<Class<?>> classes = Arrays.asList(classArray);
        return JAXBContext.newInstance( classes.toArray(new Class[classes.size()]) );

    }


}
