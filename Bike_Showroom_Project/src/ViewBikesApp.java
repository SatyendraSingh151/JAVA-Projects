import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewBikesApp {

    // Method to get JDBC connection
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/bikeshowroom";
        String user = "root";
        String password = "your_password"; // 🔐 Replace with your actual MySQL password
        return DriverManager.getConnection(url, user, password);
    }

    // Method to display all bikes
    public static void viewAllBikes() {
        String query = "SELECT * FROM bikes";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("🏍️ Available Bikes in Showroom:");
            System.out.println("----------------------------------");

            boolean found = false;
            while (rs.next()) {
                found = true;
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int year = rs.getInt("year");

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Price: ₹" + price);
                System.out.println("Year: " + year);
                System.out.println("----------------------------------");
            }

            if (!found) {
                System.out.println("🚫 No bikes available in the showroom.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error retrieving bikes: " + e.getMessage());
        }
    }

    // Main method
    public static void main(String[] args) {
        viewAllBikes();
    }
}