import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BikeShowroomApp {

    // POJO class for Bike
    static class Bike {
        private String name;
        private double price;
        private int year;

        public Bike(String name, double price, int year) {
            this.name = name;
            this.price = price;
            this.year = year;
        }

        public String getName() { return name; }
        public double getPrice() { return price; }
        public int getYear() { return year; }
    }

    // Method to get JDBC connection
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/bikeshowroom";
        String user = "root";
        String password = "tiger"; // 🔐 Replace with your actual MySQL password
        return DriverManager.getConnection(url, user, password);
    }

    // Method to add bike to database
    public static boolean addBikeToShowroom(Bike bike) {
        String query = "INSERT INTO bikes (name, price, year) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, bike.getName());
            stmt.setDouble(2, bike.getPrice());
            stmt.setInt(3, bike.getYear());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error while adding bike: " + e.getMessage());
            return false;
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("🚀 Add a New Bike to the Showroom");
        System.out.print("Enter Bike Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Bike Price: ");
        double price = sc.nextDouble();

        System.out.print("Enter Bike Year: ");
        int year = sc.nextInt();

        Bike bike = new Bike(name, price, year);
        boolean success = addBikeToShowroom(bike);

        if (success) {
            System.out.println("✅ Bike added successfully!");
        } else {
            System.out.println("❌ Failed to add bike.");
        }

        sc.close();
    }
}