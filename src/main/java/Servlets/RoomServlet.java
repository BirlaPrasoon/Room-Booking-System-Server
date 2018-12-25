package Servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import entities.Room;
import pojos.Timeline;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RoomServlet extends HttpServlet {

    private static Gson gson;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String date = request.getParameter("date");
        String roomId = request.getParameter("roomId");

        JsonArray array = new JsonArray();
        gson = new GsonBuilder().create();

        if(roomId.isEmpty()){

            List<Timeline> timelines =  Room.getTimelinesOnDate(date);

            for(Timeline t: timelines){
                array.add(gson.toJson(t));
            }


        }else{
            List<Timeline> timelines =  Room.getRoomTimelineOnDate(date,roomId);

            for(Timeline t: timelines){
                array.add(gson.toJson(t));
            }

        }

        response.setStatus(200);
        response.getWriter().write(array.toString());

    }
}
