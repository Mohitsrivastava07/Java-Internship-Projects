package org.example;
import java.sql.*;

public class DatabaseUtil {
    public static void saveStudent(NewStudent newStudent) {
        String url = "jdbc:mysql://127.0.0.1:3306/studentresult_db";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try {
                Connection conn = DriverManager.getConnection(url, username, password);

                String sql = "INSERT INTO newstudent_tb(name, total , percentage, result) VALUES(?, ?, ?, ?)";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, newStudent.getName());
                ps.setInt(2, newStudent.getTotal());
                ps.setDouble(3, newStudent.getPercentage());
                ps.setString(4, newStudent.getResult());
                ps.executeUpdate();

                System.out.println("Student Record saved Successfully");
                ps.close();
                conn.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
