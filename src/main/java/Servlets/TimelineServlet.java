package Servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import entities.Room;
import responseObjects.Timeline;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TimelineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String date = request.getParameter("date");
        String roomId= request.getParameter("roomId");
        List<Timeline> timelines =  Room.getRoomTimelineOnDate(date, roomId);

        JsonArray array = new JsonArray();

        Gson gson =new GsonBuilder().create();

        for(Timeline g: timelines){
            JsonElement json = gson.toJsonTree(g);
            array.add(json);
        }

        response.setStatus(200);
        response.setHeader("Content-Type","application/json");
        response.getWriter().write(array.toString());


    }
}
