package RegisterLogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import org.json.JSONObject;

@WebServlet("/register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static HashMap<String, String> users = new HashMap<>();

    public static HashMap<String, String> getUsers() {
        return users;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonResponse = new JSONObject();

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                jsonResponse.put("error", "Username and password cannot be empty");
            } else if (users.containsKey(username)) {
                jsonResponse.put("error", "Username already taken. Please choose another.");
            } else {
                users.put(username, password);
                jsonResponse.put("message", "Registration successful. You can now log in.");
            }

            out.print(jsonResponse.toString());
            out.flush();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Register.html").forward(request, response);
    }
}

