package Project_Advance_JAVA;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private final String url = "jdbc:mysql://localhost:3306/codingwala";
    private final String userName = "root";
    private final String password = "tiger";
    
    public void createTable() {
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             Statement statement = connection.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS student(sid INT PRIMARY KEY, sname VARCHAR(10) NOT NULL, semail VARCHAR(30) NOT NULL)";
            statement.execute(query);
            System.out.println("Table created or already exists.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertStudent(Student student) {
        String query = "INSERT INTO student(sid, sname, semail) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, student.getSid());
            ps.setString(2, student.getSname());
            ps.setString(3, student.getSemail());
            ps.executeUpdate();
            System.out.println("Student inserted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        String query = "SELECT * FROM student";
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Student s = new Student(rs.getInt("sid"), rs.getString("sname"), rs.getString("semail"));
                studentList.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public void updateStudentEmail(int sid, String newEmail) {
        String query = "UPDATE student SET semail = ? WHERE sid = ?";
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, newEmail);
            ps.setInt(2, sid);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Student email updated.");
            else
                System.out.println("Student not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteStudentByName(String name) {
        String query = "DELETE FROM student WHERE sname = ?";
        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Student deleted.");
            else
                System.out.println("Student not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
