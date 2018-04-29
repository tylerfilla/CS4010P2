package edu.umsl.mail.tsfn88.web;

import edu.umsl.mail.tsfn88.beans.CatMapping;
import edu.umsl.mail.tsfn88.beans.Category;
import edu.umsl.mail.tsfn88.beans.Problem;
import edu.umsl.mail.tsfn88.dao.CatMappingDAO;
import edu.umsl.mail.tsfn88.dao.CategoryDAO;
import edu.umsl.mail.tsfn88.dao.ProblemDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryDAO categoryDAO;
        CatMappingDAO catMappingDAO;
        ProblemDAO problemDAO;

        try {
            categoryDAO = new CategoryDAO();
            catMappingDAO = new CatMappingDAO();
            problemDAO = new ProblemDAO();
        } catch (Throwable t) {
            t.printStackTrace();
            return;
        }

        // Get category list
        List<Category> categoryList = categoryDAO.getAllCategories();
        req.setAttribute("categories", categoryList);

        // Get problem list
        List<Problem> problemList = problemDAO.getProblemList(Integer.MAX_VALUE);
        req.setAttribute("problems", problemList);

        // Get category map
        Map<Integer, List<Category>> categoryMap = new HashMap<>();
        for (Problem problem : problemList) {
            int pid = problem.getPid();

            // Get mapped categories for problem
            List<Category> categories = new ArrayList<>();
            for (CatMapping mapping : catMappingDAO.getCategoriesForProblem(pid)) {
                categories.add(categoryDAO.getCategory(mapping.getCid()));
            }
            categoryMap.put(pid, categories);
        }
        req.setAttribute("catmap", categoryMap);

        // Forward to display page
        req.getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp);
    }

}
