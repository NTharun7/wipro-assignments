package myservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Registerservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handles POST request from studentForm.html
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String course = request.getParameter("course");

        out.println("<html><body>");
        out.println("<h2>Student Registration Details</h2>");
        out.println("<p><strong>Name:</strong> " + name + "</p>");
        out.println("<p><strong>Email:</strong> " + email + "</p>");
        out.println("<p><strong>Course:</strong> " + course + "</p>");
        out.println("</body></html>");
    }

    // Handles GET request (e.g., if user directly types /register in browser)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h3>This servlet only handles form submissions using POST method.</h3>");
        out.println("<p>Please fill the <a href='studentForm.html'>Student Form</a>.</p>");
        out.println("</body></html>");
    }
}
