package it.redhat.demo.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by fabio on 13/11/16.
 */
@WebServlet("/one-http")
public class OneHttpServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(OneHttpServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info(req.getRequestURI());
        log.info(req.getMethod());
    }

}
