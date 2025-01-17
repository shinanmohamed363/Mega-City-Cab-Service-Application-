package MegaCityCabServiceApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

//@WebServlet("/sample")
public class SampleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Hardcoded variables
        String fruit1 = "apple";
        String fruit2 = "mango";

        // Create a response map to send as JSON
        Map<String, String> fruits = new HashMap<>();
        fruits.put("fruit1", fruit1);
        fruits.put("fruit2", fruit2);

        // Set response content type to JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Convert the map to JSON and write to the response
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(fruits));
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        request.setAttribute("userName", name);
        request.getRequestDispatcher("hello.jsp").forward(request, response);
    }
}
