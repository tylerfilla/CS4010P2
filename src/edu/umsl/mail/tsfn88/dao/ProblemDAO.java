package edu.umsl.mail.tsfn88.dao;

import edu.umsl.mail.tsfn88.beans.Problem;

import java.sql.*;
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

    /** Prepared query: Insert single problem at top. */
    private PreparedStatement mPreparedInsert;

    /** Prepared query: Update follows on a single problem. */
    private PreparedStatement mPreparedUpdateFollows;

    public ProblemDAO() throws Throwable {
        Class.forName("com.mysql.jdbc.Driver");

        // Connect to database
        mConnection = DriverManager.getConnection(DB_PATH, DB_USER, DB_PASS);

        // Prepare single problem query
        mPreparedSingle = mConnection.prepareStatement("SELECT * FROM `probs` WHERE `pid` = ?");

        // Prepare follows problem query
        mPreparedFollows = mConnection.prepareStatement("SELECT * FROM `probs` WHERE `follows` = ?");

        // Prepare insert problem query
        mPreparedInsert = mConnection.prepareStatement("INSERT INTO `probs` (`follows`, `content`) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS);

        // Prepare update problem follows query
        mPreparedUpdateFollows = mConnection.prepareStatement("UPDATE `probs` SET `follows` = ? WHERE `pid` = ?");
    }

    @Override
    protected void finalize() throws Throwable {
        mPreparedSingle.close();
        mPreparedFollows.close();
        mPreparedInsert.close();
        mPreparedUpdateFollows.close();
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

    public List<Problem> getProblemList(int num) {
        List<Problem> problemList = new ArrayList<>();

        int pid = 0;
        while (true) {
            try {
                ResultSet rs;

                // Find pid for problem who follows this pid
                mPreparedFollows.setInt(1, pid);
                rs = mPreparedFollows.executeQuery();
                if (rs.next()) {
                    pid = rs.getInt(1);
                } else {
                    // End of chain
                    break;
                }

                // Insert problem bean into list
                problemList.add(getProblemUnsafe(pid));

                if (problemList.size() == num) {
                    // Requested stopping point
                    break;
                }

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

        return problemList;
    }

    public boolean addProblem(Problem problem) {
        if (problem.getPid() != -1) {
            return false;
        }

        try {
            ResultSet rs;

            // Get pid for original first problem
            int pidOld = -1;
            mPreparedFollows.setInt(1, 0);
            rs = mPreparedFollows.executeQuery();
            if (rs.next()) {
                pidOld = rs.getInt(1);
            }

            // Insert new problem
            mPreparedInsert.setInt(1, problem.getFollows());
            mPreparedInsert.setString(2, problem.getContent());
            mPreparedInsert.executeUpdate();

            // Get pid for newly inserted problem
            int pidNew = -1;
            rs = mPreparedInsert.getGeneratedKeys();
            if (rs.next()) {
                pidNew = rs.getInt(1);
            }

            // Update old first problem to follow new first problem
            mPreparedUpdateFollows.setInt(1, pidNew);
            mPreparedUpdateFollows.setInt(2, pidOld);
            mPreparedUpdateFollows.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
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
