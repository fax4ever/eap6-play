package it.redhat.demo.web;

import it.redhat.demo.service.AsyncService;

import javax.inject.Inject;
import javax.servlet.AsyncContext;
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
@WebServlet(value = "/async", asyncSupported = true)
public class AsyncServlet extends HttpServlet {

    private Logger log = Logger.getLogger(AsyncServlet.class.getName());

    @Inject
    private AsyncService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("this is thread who manage response");

        AsyncContext asyncContext = req.startAsync();
        service.readData(asyncContext);

    }

}
