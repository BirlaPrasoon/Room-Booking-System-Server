package database;

import entities.Requester;

import java.sql.*;

public class JDBC {
    // JDBC driver name and database URL
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/room_booking_system";

    //  Database credentials
    public static final String USER = "root";
    public static final String PASS = "Yashu@1993";

    public static Connection conn = null;
    public static Statement stmt = null;

    public static ResultSet singleStatement(String SQL) {

        ResultSet rs = null;
        try{
            createConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
            conn.commit();

        } catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally {
           closing(stmt,conn);

        }
        System.out.println("Goodbye!");
        return rs;
    }

    public static Connection createConnection(){
        //STEP 2: Register JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, JDBC.PASS);
            conn.setAutoCommit(false);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closing(Statement stmt,Connection conn ) {
        try {
            if(conn!=null) {
                conn.close();
            }
            if(stmt!=null)
                stmt.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }

}
