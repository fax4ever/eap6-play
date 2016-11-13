package it.redhat.demo.web;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by fabio on 13/11/16.
 */
@WebServlet("/one")
public class OneServlet implements Servlet {

    private static Logger log = Logger.getLogger(OneServlet.class.getName());

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        log.info("Servlet One Calling");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }

}
