package myservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productName = request.getParameter("productName");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int total = price * quantity;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Order Receipt</h2>");
        out.println("<p>Product: " + productName + "</p>");
        out.println("<p>Price: ₹" + price + "</p>");
        out.println("<p>Quantity: " + quantity + "</p>");
        out.println("<p><strong>Total: ₹" + total + "</strong></p>");
        out.println("</body></html>");
    }
}
