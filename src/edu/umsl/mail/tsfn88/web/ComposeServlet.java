package edu.umsl.mail.tsfn88.web;

import edu.umsl.mail.tsfn88.beans.Problem;
import edu.umsl.mail.tsfn88.dao.ProblemDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/compose")
public class ComposeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String content = req.getParameter("content");

        // Create bean for problem
        Problem problem = new Problem();
        problem.setContent(content);

        // Store new problem
        try {
            ProblemDAO dao = new ProblemDAO();
            dao.addProblem(problem);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        // Redirect to main servlet
        resp.sendRedirect("/");
    }

}
