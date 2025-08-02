package myservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String selected = request.getParameter("product"); // e.g., "Laptop-50000"
        String[] parts = selected.split("-");

        String productName = parts[0];
        String price = parts[1];

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Enter Quantity</h2>");
        out.println("<form action='order' method='post'>");
        out.println("Product: <b>" + productName + "</b><br><br>");
        out.println("Price per unit: ₹<b>" + price + "</b><br><br>");
        out.println("Quantity: <input type='number' name='quantity' required><br><br>");

        // Hidden fields
        out.println("<input type='hidden' name='productName' value='" + productName + "'>");
        out.println("<input type='hidden' name='price' value='" + price + "'>");

        out.println("<input type='submit' value='Generate Bill'>");
        out.println("</form>");
        out.println("</body></html>");
    }
}
