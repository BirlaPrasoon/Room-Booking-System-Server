package Servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import pojos.RequestResponse;
import entities.Requester;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CancelRequestServlet extends HttpServlet {
    private static Gson gson = new GsonBuilder().create();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int requestId = Integer.parseInt(request.getParameter("requestId"));
        Requester.setRequestStatus(" ",requestId,"cancelled");
        response.setStatus(200);
        response.getWriter().write("Request status: cancelled" );

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get cancellable requests
        String email = request.getParameter("email");

        if(email.isEmpty()){
            response.setStatus(201);
            response.getWriter().write("Invalid Email!");
        }else {
            List<RequestResponse> requests = null;
            requests = Requester.getCancellableRequests(email);
            JsonArray array = new JsonArray();

            for (RequestResponse r : requests) {
                JsonElement json = gson.toJsonTree(r);
                array.add(json);
            }

            response.getWriter().write(array.toString());
            response.setStatus(200);
        }
    }
}
