package it.redhat.demo.web;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        log.info(req.getRequestURI());
        log.info(req.getMethod());

        resp.setContentType("text/html");

        PrintWriter pw = resp.getWriter();
        pw.write("<!DOCTYPE html>");
        pw.write("<html>");
        pw.write("<head>");
        pw.write("<meta charset=\"UTF-8\">");
        pw.write("<title>Servlet</title>");
        pw.write("</head>");
        pw.write("<body>");
        pw.write("<h3>This is a servlet!</h3>");
        pw.write("</body>");
        pw.write("</html>");

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }

}
