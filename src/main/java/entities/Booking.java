package entities;

import database.JDBC;

import javax.naming.TimeLimitExceededException;
import java.sql.*;

import static database.JDBC.DB_URL;
import static database.JDBC.USER;

public class Booking {

    private String roomId;
    private int requestid;
    private Date date;
    private String granterId;
    private String requesterId;

    public Booking(String roomId, int requestid, Date date, String granterId, String requesterId) {
        this.roomId = roomId;
        this.requestid = requestid;
        this.date = date;
        this.granterId = granterId;
        this.requesterId = requesterId;
    }
    public String getRoomId() {
        return roomId;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public int getRequestid() {
        return requestid;
    }
    public void setRequestid(int requestid) {
        this.requestid = requestid;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getGranterId() {
        return granterId;
    }
    public void setGranterId(String granterId) {
        this.granterId = granterId;
    }
    public String getRequesterId() {
        return requesterId;
    }
    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

    public static boolean makeRequest(int id, String purpose, int mic, int ac, int cleaning, int projector, String from_time, String to_time, String roomId, String date, String granterId, String email){
        try{

            JDBC.createConnection();

            JDBC.stmt = JDBC.conn.createStatement();

            String RequestSQL = "INSERT INTO request(request_id, purpose, mic,ac,cleaning,projector, from_time, to_time) " +
                    "value (?,?,?,?,?,?,?,?)";

            String BookingSQL = "INSERT  INTO booking(roomId, requestId, date, granterId, requesterId) " + "value (?,?,?,?,?)";

            String TimelineSQL = "INSERT INTO timeline(roomId,date,timeslot)" +
                    "value (?,?,?)";

            PreparedStatement bookingPrepStmt = JDBC.conn.prepareStatement(BookingSQL);
            PreparedStatement timelinePrepStmt = JDBC.conn.prepareStatement(TimelineSQL);
            PreparedStatement requestPrepStmt = JDBC.conn.prepareStatement(RequestSQL);

            requestPrepStmt.setInt(1,id);
            requestPrepStmt.setString(2,purpose);
            requestPrepStmt.setInt(3,mic);
            requestPrepStmt.setInt(4,ac);
            requestPrepStmt.setInt(5,cleaning);
            requestPrepStmt.setInt(6,projector);
            requestPrepStmt.setString(7,from_time);
            requestPrepStmt.setString(8,to_time);

            bookingPrepStmt.setString(1,roomId);
            bookingPrepStmt.setInt(2,id);
            bookingPrepStmt.setString(3,date);
            bookingPrepStmt.setString(4,granterId);
            bookingPrepStmt.setString(5,email);

            timelinePrepStmt.setString(1,roomId);
            timelinePrepStmt.setString(2,date);
            timelinePrepStmt.setString(3,from_time+"to"+to_time);

            requestPrepStmt.execute();
            requestPrepStmt.close();
            bookingPrepStmt.execute();
            bookingPrepStmt.close();
            timelinePrepStmt.execute();
            timelinePrepStmt.close();

            JDBC.conn.commit();

        } catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBC.closing(JDBC.stmt, JDBC.conn);
        }
        return false;
    }

}
