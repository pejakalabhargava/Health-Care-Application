package com.odl.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Database object to load drivers and perform queries.
 */
public class DBUtil {
    
    /** The con. */
    private static Connection con;
    
    /** The Constant Driver. */
    private static final String Driver = "oracle.jdbc.driver.OracleDriver";
    
    /** The Constant ConnectionString. */
    private static final String ConnectionString = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
    
    /** The Constant user. */
    private static final String user = "bkakran";
    
    /** The Constant pwd. */
    private static final String pwd = "bhar1956";
   
    /**
     * create Database object.
     */
   public DBUtil() {
    }
    
    /**
     * to load the database base driver.
     *
     * @return a database connection
     * @throws SQLException throws an exception if an error occurs
     */
    public static Connection loadDriver() throws SQLException {
        try {
            Class.forName(Driver);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        con = DriverManager.getConnection(ConnectionString, user, pwd);
        return con;
    }
 
    /**
     * to get a result set of a query.
     *
     * @param query custom query
     * @param con the con
     * @return a result set of custom query
     * @throws SQLException throws an exception if an error occurs
     */
    public static ResultSet getResultSet(String query, Connection con) throws SQLException {
        ResultSet rs;
        PreparedStatement st = con.prepareStatement(query);
        rs = st.executeQuery();
        return rs;
    }
 
    /**
     * to run an update query such as update, delete.
     *
     * @param query custom query
     * @return the int
     * @throws SQLException throws an exception if an error occurs
     */
    public static int runQuery(String query) throws SQLException {
        Connection con = loadDriver();
        PreparedStatement st = con.prepareStatement(query);
        con.close();
        return st.executeUpdate();
    }
}
