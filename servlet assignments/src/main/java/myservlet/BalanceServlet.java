package myservlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/BalanceServlet")
public class BalanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);

        if (session != null) {
            String name = (String) session.getAttribute("name");
            int balance = (int) session.getAttribute("balance");

            out.println("<h2>Welcome, " + name + "</h2>");
            out.println("<p>Your Balance: ₹" + balance + "</p>");
            out.println("<a href='LogoutServlet'>Logout</a>");
        } else {
            out.println("<h3>Session expired. Please <a href='login.html'>login</a> again.</h3>");
        }
    }
}
