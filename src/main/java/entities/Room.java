package entities;

import database.JDBC;
import pojos.Timeline;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.JDBC.DB_URL;
import static database.JDBC.USER;
import static database.JDBC.closing;

public class Room {

    private String roomId;
    private String room_type;
    private String mic_state;
    private String projector_state;
    private String smartboard_state;
    private String fans_state;
    private String lights_state;
    private String size;
    private String ac;

    public Room() {
    }

    public Room(String roomId, String room_type, String mic_state, String projector_state, String smartboard_state, String fans_state, String lights_state, String size, String ac) {
        this.roomId = roomId;
        this.room_type = room_type;
        this.mic_state = mic_state;
        this.projector_state = projector_state;
        this.smartboard_state = smartboard_state;
        this.fans_state = fans_state;
        this.lights_state = lights_state;
        this.size = size;
        this.ac = ac;
    }

    public String getRoomId() {
        return roomId;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public String getRoom_type() {
        return room_type;
    }
    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }
    public String getMic_state() {
        return mic_state;
    }
    public void setMic_state(String mic_state) {
        this.mic_state = mic_state;
    }
    public String getProjector_state() {
        return projector_state;
    }
    public void setProjector_state(String projector_state) {
        this.projector_state = projector_state;
    }
    public String getSmartboard_state() {
        return smartboard_state;
    }
    public void setSmartboard_state(String smartboard_state) {
        this.smartboard_state = smartboard_state;
    }
    public String getFans_state() {
        return fans_state;
    }
    public void setFans_state(String fans_state) {
        this.fans_state = fans_state;
    }
    public String getLights_state() {
        return lights_state;
    }
    public void setLights_state(String lights_state) {
        this.lights_state = lights_state;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getAc() {
        return ac;
    }
    public void setAc(String ac) {
        this.ac = ac;
    }
    public static List<Timeline> getTimelinesOnDate(String date){

        List<Timeline> roomsTimelines = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, JDBC.PASS);
            conn.setAutoCommit(false);

            String SQL = "select * from timeline " +
                    "where date = '"+date+"'";

            System.out.println(SQL);

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while(rs.next()) {
                Timeline t = getTimeline(rs);
                roomsTimelines.add(t);
            }
            conn.commit();
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBC.closing(stmt,conn);
        }

        return roomsTimelines;
    }

    private static Timeline getTimeline(ResultSet rs) throws SQLException {
        String roomId = rs.getString("roomId");
        String dat = rs.getString("date");
        String timeslot= rs.getString("timeslot");
        return new Timeline(roomId,dat,timeslot);
    }

    public static List<Timeline> getRoomTimelineOnDate(String date, String roomID){

        List<Timeline> roomsTimelines = new ArrayList<>();


        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, JDBC.PASS);
            conn.setAutoCommit(false);

            String SQL = "select * from timeline " +
                    "where roomId = '"+ roomID +"' and date = '"+date+"'";

            System.out.println(SQL);

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while(rs.next()) {
                Timeline t = getTimeline(rs);
                roomsTimelines.add(t);
            }
            conn.commit();
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            closing(stmt, conn);
        }

        return roomsTimelines;
    }


}
