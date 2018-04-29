package edu.umsl.mail.tsfn88.web;

import edu.umsl.mail.tsfn88.beans.Problem;
import edu.umsl.mail.tsfn88.dao.ProblemDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get problem list
        List<Problem> problemList = null;
        try {
            ProblemDAO dao = new ProblemDAO();
            problemList = dao.getProblemList(1, 10);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        // Add problem list to request
        req.setAttribute("problems", problemList);

        // Forward to display page
        req.getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp);
    }

}
