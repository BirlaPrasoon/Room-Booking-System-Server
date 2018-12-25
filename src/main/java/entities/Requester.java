package entities;
import database.JDBC;
import pojos.RequestResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.JDBC.*;

public class Requester {
    private String name;
    private String phoneNumber;
    private String password;
    private String email;

    public Requester() {
    }

    public Requester(String name, String phoneNumber, String password, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public static void createTable(){
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, JDBC.PASS);
            conn.setAutoCommit(false);

            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            String sql = "CREATE TABLE REQUESTER " +
                    "( email varchar(50) PRIMARY KEY , " +
                    " name VARCHAR(50) NOT NULL , " +
                    " password VARCHAR(30) NOT NULL , " +
                    " phone_number varchar(13) NOT NULL);";

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

            conn.commit();

        } catch(Exception e){
            e.printStackTrace();
        }finally{
            closing( stmt,conn);
        }
    }



    public static Requester validateCredentials(String email, String password){
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, JDBC.PASS);
            conn.setAutoCommit(false);

            String SQL = "SELECT * FROM Requester WHERE email = '" + email + "'" ;

            System.out.println(SQL);

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while(rs.next()){
                //Retrieve by column name
                String e = rs.getString("email");
                String name = rs.getString("name");
                String phone_number = rs.getString("phone_number");
                String pass = rs.getString("password");

                //Display values
                System.out.print("Email: " + e);
                System.out.print(", Username: " + name);
                System.out.print(", PhoneNumber : " + phone_number);
                System.out.println(", Password : " + pass);
                return new Requester(name,phone_number,pass,e);
            }

            conn.commit();

        } catch(Exception e){
            e.printStackTrace();
        }finally{
            closing( stmt,conn);
        }
        return null;
    }

    public static List<RequestResponse> getGranterRequests(String granterId){
        List<RequestResponse> result = new ArrayList<>();

        String SQL = "Select * from booking " +
                "inner join request " +
                "on request.request_id = booking.requestId " +
                "where granterId = '" + granterId +"' and status ='pending'";

        try {
            JDBC.createConnection();
            stmt = JDBC.conn.createStatement();
            ResultSet rs =  stmt.executeQuery(SQL);
            getResponse(result, rs);
            closing(JDBC.stmt,JDBC.conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean setRequestStatus(String roomId, int requestId, String status ){

        try{

            JDBC.createConnection();

            String SQL = "update request set status = ?" +
                    "where request_id = ?";

            System.out.println(SQL);
            PreparedStatement preparedStatement = JDBC.conn.prepareStatement(SQL);
            preparedStatement.setString(1,status);
            preparedStatement.setInt(2,requestId);

            preparedStatement.execute();

            JDBC.conn.commit();

        } catch(Exception e){
            e.printStackTrace();
        }finally{
            closing(stmt,conn);
        }
        return false;
    }

    public static List<RequestResponse> getRequesterHistory(String requesterEmail){

        List<RequestResponse> requests = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, JDBC.PASS);
            conn.setAutoCommit(false);

            String SQL = "select * from request,booking where request.request_id = booking.requestId and request_id in ( " +
                    "select requestId " +
                    "from booking " +
                    "join requester on requester.email = booking.requesterId\n" +
                    "where email = '"+ requesterEmail +"')";

            System.out.println(SQL);

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            getResponse(requests, rs);
            conn.commit();

        } catch(Exception e){
            e.printStackTrace();
        }finally{
            closing(stmt,conn);
        }
        return requests;
    }

    private static void getResponse(List<RequestResponse> requests, ResultSet rs) throws SQLException {
        while(rs.next()) {

            //Retrieve by column name
            String roomId = rs.getString("roomId");
            int requestId = rs.getInt("requestId");
            String date = rs.getString("date");
            String requesterId = rs.getString("requesterId");
            String purpose = rs.getString("purpose");
            int mic = rs.getInt("mic");
            int ac = rs.getInt("ac");
            int cleaning = rs.getInt("cleaning");
            int projector = rs.getInt("projector");
            String from_time = rs.getString("from_time");
            String to_time = rs.getString("to_time");
            String status = rs.getString("status");

            RequestResponse requestResponse =
                    new RequestResponse(roomId,requestId,date,requesterId,purpose,mic,ac,cleaning,projector,from_time,to_time,status);
            requests.add(requestResponse);

        }
    }

    public static List<RequestResponse> getCancellableRequests(String email){

        List<RequestResponse> requests = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, JDBC.PASS);
            conn.setAutoCommit(false);

            String SQL = "select * from request,booking " +
                    "where request.request_id = booking.requestId " +
                    "  and request_id in ( " +
                    "             select requestId " +
                    "             from booking join requester on requester.email = booking.requesterId\n" +
                    "             where email = '" + email + "' and status in ('approved','pending'))";

            System.out.println(SQL);

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            getResponse(requests, rs);
            conn.commit();

        } catch(Exception e){
            e.printStackTrace();
        }finally{
            closing(stmt, conn);
        }
        return requests;
    }
}
