package edu.umsl.mail.tsfn88.dao;

import edu.umsl.mail.tsfn88.beans.Problem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProblemDAO {

    private static final String DB_PATH = "jdbc:mysql://localhost:3306/mathbank";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "password";

    /** The MySQL connection. */
    private Connection mConnection;

    /** Prepared query: Get single problem by ID. */
    private PreparedStatement mPreparedSingle;

    /** Prepared query: Get single problem by follows. */
    private PreparedStatement mPreparedFollows;

    public ProblemDAO() throws Throwable {
        Class.forName("com.mysql.jdbc.Driver");

        // Connect to database
        mConnection = DriverManager.getConnection(DB_PATH, DB_USER, DB_PASS);

        // Prepare single problem query
        mPreparedSingle = mConnection.prepareStatement("SELECT * FROM `probs` WHERE `pid` = ?");

        // Prepare follows problem query
        mPreparedFollows = mConnection.prepareStatement("SELECT * FROM `probs` WHERE `follows` = ?");
    }

    @Override
    protected void finalize() throws Throwable {
        mPreparedSingle.close();
        mPreparedFollows.close();
        mConnection.close();
    }

    public Problem getProblem(int pid) {
        try {
            // Get problem bean
            return getProblemUnsafe(pid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Problem> getProblemList(int pidA, int pidB) {
        List<Problem> problemList = new ArrayList<>();

        int pid = pidA;
        while (pid != -1 && pid != pidB) {
            try {
                // Insert problem bean into list
                problemList.add(getProblemUnsafe(pid));

                // Execute follows problem query
                mPreparedFollows.setInt(1, pid);
                ResultSet rs = mPreparedFollows.executeQuery();

                if (rs.next()) {
                    // Update iteration index
                    pid = rs.getInt(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return problemList;
    }

    private Problem getProblemUnsafe(int pid) throws Exception {
        // Execute single problem query
        mPreparedSingle.setInt(1, pid);
        ResultSet rs = mPreparedSingle.executeQuery();

        if (rs.next()) {
            // Create problem bean
            Problem problem = new Problem();
            problem.setPid(rs.getInt(1));
            problem.setFollows(rs.getInt(2));
            problem.setContent(rs.getString(3));

            return problem;
        }

        return null;
    }

}
