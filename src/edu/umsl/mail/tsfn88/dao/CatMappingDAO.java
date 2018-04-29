package edu.umsl.mail.tsfn88.dao;

import edu.umsl.mail.tsfn88.beans.CatMapping;
import edu.umsl.mail.tsfn88.beans.Category;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CatMappingDAO {

    private static final String DB_PATH = "jdbc:mysql://localhost:3306/mathbank";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "password";

    /** The MySQL connection. */
    private Connection mConnection;

    /** Prepared query: Get all category mappings for a problem ID. */
    private PreparedStatement mPreparedForProblem;

    public CatMappingDAO() throws Throwable {
        Class.forName("com.mysql.jdbc.Driver");

        // Connect to database
        mConnection = DriverManager.getConnection(DB_PATH, DB_USER, DB_PASS);

        // Prepare all category mappings for PID query
        mPreparedForProblem = mConnection.prepareStatement("SELECT * FROM `catmap` WHERE `pid` = ?");
    }

    @Override
    protected void finalize() throws Throwable {
        mPreparedForProblem.close();
        mConnection.close();
    }

    public List<CatMapping> getCategoriesForProblem(int pid) {
        List<CatMapping> mappingList = new ArrayList<>();

        try {
            mPreparedForProblem.setInt(1, pid);
            ResultSet rs = mPreparedForProblem.executeQuery();

            while (rs.next()) {
                // Create category mapping bean
                CatMapping mapping = new CatMapping();
                mapping.setMid(rs.getInt(1));
                mapping.setPid(rs.getInt(2));
                mapping.setCid(rs.getInt(3));

                mappingList.add(mapping);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mappingList;
    }

}
