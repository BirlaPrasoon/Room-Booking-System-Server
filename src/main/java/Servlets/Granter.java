package Servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import responseObjects.RequestResponse;
import entities.Requester;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Granter extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String status = request.getParameter("status");
        String roomId = request.getParameter("roomId");
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        Requester.setRequestStatus(roomId,requestId,status);
        response.setStatus(200);
        response.getWriter().write("Request status: " + status);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson =new  GsonBuilder().create();
        String granterId = request.getParameter("granterId");
        List<RequestResponse> requestsResponseList =  Requester.getGranterRequests(granterId);

        JsonArray array = new JsonArray();

        for(RequestResponse g: requestsResponseList){
            JsonElement json = gson.toJsonTree(g);
            array.add(json);
        }

        response.setStatus(200);
        response.setHeader("Content-Type","application/json");
        response.getWriter().write(array.toString());
    }
}
