import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 10/06/16
 */
@Singleton
@Startup
public class Start {

    private static Logger log = Logger.getLogger(Start.class);

    @PostConstruct
    public void start() {

        log.info("ciao");

    }

}
