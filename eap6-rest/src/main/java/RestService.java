import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 18/07/16
 */

@Path("cache")
public class RestService {

    Logger log = LoggerFactory.getLogger(RestService.class);

    @POST
    public void start() {
        log.info("START");
    }

    @DELETE
    public void stop() {
        log.info("STOP");
    }

    @POST
    @Path("error")
    public void error() {
        throw new RuntimeException();
    }

}