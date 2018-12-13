package Servlets;

import com.google.gson.JsonObject;
import entities.Booking;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

public class SubmitRequestServlet extends HttpServlet {



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //inputs -> email (requesterId), roomId, granterId, date, purpose, mic, ac, cleaning, projector, from_time, to_time
        //1: generate Request
        //2: generate booking
        //3: generate timeline

        String purpose = request.getParameter("purpose");
        String from_time = request.getParameter("from_time");
        String to_time = request.getParameter("to_time");
        int mic = Integer.parseInt(request.getParameter("mic"));
        int ac = Integer.parseInt(request.getParameter("ac"));
        int cleaning = Integer.parseInt(request.getParameter("cleaning"));
        int projector = Integer.parseInt(request.getParameter("projector"));
        String roomId = request.getParameter("roomId");
        String date = request.getParameter("date");
        String email = request.getParameter("email");
        //todo: generate granterId
        String granterId = request.getParameter("granterId");

        Random random = new Random();
        int id =  random.nextInt(9000000) + 1000000000;
        Booking.makeRequest(id,purpose,mic,ac,cleaning,projector,from_time,to_time,roomId,date,granterId,email);

        response.setStatus(200);
        response.getWriter().write("Request Submitted");
    }
}
