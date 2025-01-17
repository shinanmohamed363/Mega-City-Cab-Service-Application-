package MegaCityCabServiceApplication.controllers;

import MegaCityCabServiceApplication.models.User;
import MegaCityCabServiceApplication.services.AuthenticationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private final AuthenticationService authService = new AuthenticationService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parse input JSON (username and password)
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> requestData = mapper.readValue(request.getReader(), Map.class);

        String username = requestData.get("username");
        String password = requestData.get("password");

        // Validate input
        if (username == null || password == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Missing username or password.");
            return;
        }

        // Authenticate user
        User authenticatedUser = authService.authenticate(username, password);

        // Prepare response
        Map<String, Object> responseBody = new HashMap<>();
        if (authenticatedUser != null) {
            responseBody.put("message", "Login successful");
            responseBody.put("role", authenticatedUser.getType());
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            responseBody.put("message", "Invalid username or password");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(mapper.writeValueAsString(responseBody));
    }
}
