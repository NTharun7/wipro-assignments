package jsp;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        int marks = Integer.parseInt(request.getParameter("marks"));

        request.setAttribute("name", name);
        request.setAttribute("marks", marks);

        if (marks >= 40) {
            request.getRequestDispatcher("pass.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("fail.jsp").forward(request, response);
        }
    }
}
