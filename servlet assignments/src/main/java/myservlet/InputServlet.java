package myservlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/input")
public class InputServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get input from form
        String name = request.getParameter("name");
        int marks = Integer.parseInt(request.getParameter("marks"));

        // Set attributes to pass to next servlet
        request.setAttribute("name", name);
        request.setAttribute("marks", marks);

        // Forward to GradeServlet
        RequestDispatcher rd = request.getRequestDispatcher("grade");
        rd.forward(request, response);
    }
}
