package edu.umsl.mail.tsfn88.dao;

import edu.umsl.mail.tsfn88.beans.Category;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private static final String DB_PATH = "jdbc:mysql://localhost:3306/mathbank";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "password";

    /** The MySQL connection. */
    private Connection mConnection;

    /** Prepared query: Get single category by ID. */
    private PreparedStatement mPreparedSingle;

    /** Prepared query: Get all categories. */
    private PreparedStatement mPreparedAll;

    /** Prepared query: Insert category. */
    private PreparedStatement mPreparedInsert;

    public CategoryDAO() throws Throwable {
        Class.forName("com.mysql.jdbc.Driver");

        // Connect to database
        mConnection = DriverManager.getConnection(DB_PATH, DB_USER, DB_PASS);

        // Prepare single category query
        mPreparedSingle = mConnection.prepareStatement("SELECT * FROM `cats` WHERE `cid` = ?");

        // Prepare all categories query
        mPreparedAll = mConnection.prepareStatement("SELECT * FROM `cats`");

        // Prepare insert category query
        mPreparedInsert = mConnection.prepareStatement("INSERT INTO `cats` (`name`) VALUES (?)");
    }

    @Override
    protected void finalize() throws Throwable {
        mPreparedSingle.close();
        mPreparedAll.close();
        mPreparedInsert.close();
        mConnection.close();
    }

    public Category getCategory(int cid) {
        try {
            // Execute single category query
            mPreparedSingle.setInt(1, cid);
            ResultSet rs = mPreparedSingle.executeQuery();

            if (rs.next()) {
                // Create category bean
                Category category = new Category();
                category.setCid(rs.getInt(1));
                category.setName(rs.getString(2));

                return category;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();

        try {
            ResultSet rs = mPreparedAll.executeQuery();

            while (rs.next()) {
                // Create category bean
                Category category = new Category();
                category.setCid(rs.getInt(1));
                category.setName(rs.getString(2));

                categoryList.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categoryList;
    }

    public void addCategory(Category category) {
        try {
            // Execute insert category query
            mPreparedInsert.setString(1, category.getName());
            mPreparedInsert.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
