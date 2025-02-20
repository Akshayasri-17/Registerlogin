package RegisterLogin;

import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import org.json.JSONObject;

@WebServlet("/register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Store users in a static HashMap
    private static HashMap<String, String> users = new HashMap<>();

    // Provide access to the users HashMap
    public static HashMap<String, String> getUsers() {
        return users;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username != null && password != null) {
                users.put(username, password);
                json.put("username", username);
                json.put("password", password);

                // Redirect to Login Page after successful registration
                response.sendRedirect("Login.html");
                return; // Stop further execution
            } else {
                json.put("error", "Username or Password is missing");
            }
        } catch (Exception e) {
            json.put("error", e.getMessage());
        }

        out.print(json.toString());
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Register.html").forward(request, response);
    }
}
