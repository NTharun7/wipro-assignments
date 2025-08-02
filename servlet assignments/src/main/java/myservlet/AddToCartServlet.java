package myservlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String item = request.getParameter("item");

        if (item != null && !item.isEmpty()) {
            Cookie cookie = new Cookie("item_" + item, item);
            cookie.setMaxAge(60 * 60); // 1 hour
            response.addCookie(cookie);
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>Item added to cart: " + item + "</h2>");
        out.println("<a href='addItem.html'>Add More</a><br>");
        out.println("<a href='ViewCartServlet'>View Cart</a>");
    }
}
