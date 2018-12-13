
package Servlets;

import com.google.gson.Gson;
import entities.Requester;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
//        System.out.println("Email: "+ email);
//        System.out.println("Password: "+ password);
        Requester requester =  Requester.validateCredentials(email,password);
        if(requester !=null){
            Gson gson = new Gson();
            String jsonObject = gson.toJson(requester);
            response.setStatus(200);
            response.setHeader("Content-Type","application/json");
            response.getWriter().write(jsonObject);
        }else{
            response.setHeader("Content-Type","application/json");
            response.setStatus(201);
            response.getWriter().write("Invalid Credentials");
        }
    }

}
