package edu.umsl.mail.tsfn88.web;

import edu.umsl.mail.tsfn88.beans.Problem;

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
        Problem p1 = new Problem();
        p1.setPid(1);
        p1.setContent("This is problem 1");

        Problem p2 = new Problem();
        p2.setPid(2);
        p2.setContent("This is problem 2");

        Problem p3 = new Problem();
        p3.setPid(3);
        p3.setContent("This is problem 3");

        // The problem list
        List<Problem> problems = new ArrayList<Problem>();
        problems.add(p1);
        problems.add(p2);
        problems.add(p3);

        // Add problem list to request
        req.setAttribute("problems", problems);

        // Forward to display page
        req.getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp);
    }

}
