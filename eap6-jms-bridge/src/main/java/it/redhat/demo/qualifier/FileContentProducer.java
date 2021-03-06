package it.redhat.demo.qualifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created by fabio.ercoli@redhat.com on 19/04/17.
 */

@ApplicationScoped
public class FileContentProducer {

    private static final Logger LOG = LoggerFactory.getLogger(FileContentProducer.class);

    private String startProcess = null;

    @PostConstruct
    public void init() {

        startProcess = loadFile("startProcess.json");

    }

    @Produces
    @StartProcess
    public String getContent() {

        return startProcess;

    }

    private String loadFile(String name) {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(name);

        StringBuffer sb = new StringBuffer();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(is, Charset.defaultCharset()));

            for(String e = reader.readLine(); e != null; e = reader.readLine()) {
                sb.append(e).append("\n");
            }
        } catch (IOException var11) {
            throw new RuntimeException(var11);
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            }

        }

        return sb.toString();
    }


}
