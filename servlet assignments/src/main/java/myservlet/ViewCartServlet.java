package myservlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ViewCartServlet")
public class ViewCartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Cookie[] cookies = request.getCookies();
        out.println("<h2>Your Shopping Cart</h2>");

        boolean found = false;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().startsWith("item_")) {
                    out.println("<p>" + c.getValue() + "</p>");
                    found = true;
                }
            }
        }

        if (!found) {
            out.println("<p>Your cart is empty.</p>");
        }

        out.println("<br><a href='addItem.html'>Back to Add Items</a>");
    }
}

