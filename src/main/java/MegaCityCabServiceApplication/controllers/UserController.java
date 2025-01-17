package MegaCityCabServiceApplication.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import MegaCityCabServiceApplication.models.User;
import MegaCityCabServiceApplication.repositories.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class UserController extends HttpServlet {

    private final UserRepository userRepository = new UserRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read the JSON request body to extract user details
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(request.getReader(), User.class);

        // Validate input (basic validation here, improve as needed)
        if (user.getUsername() == null || user.getPassword() == null || user.getType() == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Missing required fields");
            return;
        }

        // Save user to the database
        userRepository.addUser(user);

        // Return a success message
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("User registered successfully");
    }
}
