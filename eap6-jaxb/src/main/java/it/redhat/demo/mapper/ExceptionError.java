package it.redhat.demo.mapper;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by fabio.ercoli@redhat.com on 23/03/17.
 */
@XmlRootElement
public class ExceptionError {

    private String message;

    public ExceptionError() {
    }

    public ExceptionError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
