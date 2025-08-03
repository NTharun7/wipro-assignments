package jsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String birthday = request.getParameter("birthday");
        String gender = request.getParameter("gender");
        String profession = request.getParameter("profession");
        String married = request.getParameter("married");
        String note = request.getParameter("note");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h2>Registration Details</h2>");
        out.println("<p>Full Name: " + fullname + "</p>");
        out.println("<p>Email: " + email + "</p>");
        out.println("<p>Birthday: " + birthday + "</p>");
        out.println("<p>Gender: " + gender + "</p>");
        out.println("<p>Profession: " + profession + "</p>");
        out.println("<p>Married: " + (married != null ? "Yes" : "No") + "</p>");
        out.println("<p>Note: " + note + "</p>");
    }
}
