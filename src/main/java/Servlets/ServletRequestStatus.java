package Servlets;

import entities.Requester;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletRequestStatus extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String status = request.getParameter("status");
        String roomId = request.getParameter("roomId");
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        Requester.setRequestStatus(roomId,requestId,status);
        response.setStatus(200);
        response.getWriter().write("Request status: " + status);

    }
}
