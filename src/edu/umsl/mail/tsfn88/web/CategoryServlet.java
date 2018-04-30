package edu.umsl.mail.tsfn88.web;

import edu.umsl.mail.tsfn88.beans.Category;
import edu.umsl.mail.tsfn88.dao.CategoryDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/category")
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryDAO dao;
        try {
            dao = new CategoryDAO();
        } catch (Throwable t) {
            t.printStackTrace();
            return;
        }

        String action = req.getParameter("action");

        if ("create".equals(action)) {
            Category category = new Category();
            category.setName(req.getParameter("name"));

            // Insert category
            dao.addCategory(category);
        }

        // Redirect to main servlet
        resp.sendRedirect("/");
    }

}
