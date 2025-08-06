package com.wiproassignments.springbootjdbcbooks;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    
    private final String jdbcURL = "jdbc:mysql://localhost:3306/booksdb";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "Nut5823@"; // 🔁 Replace with your password

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        String query = "SELECT * FROM books";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Book book = new Book();
                book.setBookid(rs.getInt("bookid"));
                book.setBookname(rs.getString("bookname"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getInt("price"));
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
}
