package jsp;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("username", request.getParameter("username"));
        request.setAttribute("password", request.getParameter("password"));
        request.setAttribute("address", request.getParameter("address"));
        request.setAttribute("subscribe", request.getParameter("subscribe"));
        request.setAttribute("frameworks", request.getParameterValues("frameworks"));
        request.setAttribute("gender", request.getParameter("gender"));
        request.setAttribute("favnum", request.getParameter("favnum"));

        request.getRequestDispatcher("/userinfo.jsp").forward(request, response);
    }
}
