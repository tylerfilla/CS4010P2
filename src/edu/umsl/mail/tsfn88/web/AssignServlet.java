package edu.umsl.mail.tsfn88.web;

import edu.umsl.mail.tsfn88.beans.CatMapping;
import edu.umsl.mail.tsfn88.dao.CatMappingDAO;
import edu.umsl.mail.tsfn88.dao.CategoryDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/assign")
public class AssignServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CatMappingDAO dao;
        try {
            dao = new CatMappingDAO();
        } catch (Throwable t) {
            t.printStackTrace();
            return;
        }

        int pid = Integer.parseInt(req.getParameter("pid"));
        int cid = Integer.parseInt(req.getParameter("cid"));

        CatMapping mapping = new CatMapping();
        mapping.setPid(pid);
        mapping.setCid(cid);
        dao.addMapping(mapping);

        // Redirect to main servlet
        resp.sendRedirect("/");
    }

}
