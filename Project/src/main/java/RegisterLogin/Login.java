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

@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Access the users HashMap from Register class
    private static HashMap<String, String> users = Register.getUsers();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username != null && password != null) {
                if (users.containsKey(username) && users.get(username).equals(password)) {
                    json.put("message", "Logged in successfully");
                } else {
                    json.put("error", "Invalid Username or Password. Try again");
                }
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
        request.getRequestDispatcher("/Login.html").forward(request, response);
    }
}
