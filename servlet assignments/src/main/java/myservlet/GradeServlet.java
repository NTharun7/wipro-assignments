package myservlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/grade")
public class GradeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = (String) request.getAttribute("name");
        int marks = (int) request.getAttribute("marks");

        String grade;
        if (marks >= 90) {
            grade = "A+";
        } else if (marks >= 75) {
            grade = "A";
        } else if (marks >= 60) {
            grade = "B";
        } else if (marks >= 50) {
            grade = "C";
        } else {
            grade = "Fail";
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Student Grade</h2>");
        out.println("Name: " + name + "<br>");
        out.println("Marks: " + marks + "<br>");
        out.println("Grade: " + grade);
        out.println("</body></html>");
    }
}
