package edu.umsl.mail.tsfn88.dao;

import java.sql.Connection;

public class ProblemDAO {

    /** The MySQL connection. */
    private Connection mConnection;

    public ProblemDAO() throws Throwable {
        Class.forName("com.mysql.jdbc.Driver");
    }

    @Override
    protected void finalize() throws Throwable {
        mConnection.close();
    }

}
