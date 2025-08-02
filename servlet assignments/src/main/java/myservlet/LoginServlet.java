package myservlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("username");
        String pass = request.getParameter("password");

        if ("admin".equals(name) && "1234".equals(pass)) {
            HttpSession session = request.getSession();
            session.setAttribute("name", name);
            session.setAttribute("balance", 10000); // hardcoded balance
            response.sendRedirect("BalanceServlet");
        } else {
            out.println("<h3>Invalid credentials. <a href='login.html'>Try again</a></h3>");
        }
    }
}
