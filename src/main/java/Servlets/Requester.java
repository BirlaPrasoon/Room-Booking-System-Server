package Servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import pojos.RequestResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Requester extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        if(!email.isEmpty()) {
            Gson gson = new GsonBuilder().create();

            List<RequestResponse> requests = null;
            requests = entities.Requester.getRequesterHistory(email);
            JsonArray array = new JsonArray();

            assert requests != null;
            for (RequestResponse r : requests) {
                JsonElement json = gson.toJsonTree(r);
                array.add(json);
            }

            System.out.println(array);

            response.setHeader("Content-Type","application/json");
            response.setStatus(200);
            response.getWriter().write(array.toString());
        }
        else{
            response.setHeader("Content-Type","application/text");
            response.sendError(422,"invalid email!");
        }
    }
}
