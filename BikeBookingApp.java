import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class BikeBookingApp {

    // POJO class for Booking
    static class Booking {
        private String customerName;
        private String bikeName;
        private int bikeYear;
        private String bookingDate;

        public Booking(String customerName, String bikeName, int bikeYear) {
            this.customerName = customerName;
            this.bikeName = bikeName;
            this.bikeYear = bikeYear;
            this.bookingDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        }

        public String getCustomerName() { return customerName; }
        public String getBikeName() { return bikeName; }
        public int getBikeYear() { return bikeYear; }
        public String getBookingDate() { return bookingDate; }
    }

    // Method to get JDBC connection
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/bikeshowroom";
        String user = "root";
        String password = "your_password"; // 🔐 Replace with your actual MySQL password
        return DriverManager.getConnection(url, user, password);
    }

    // Method to insert booking into database
    public static boolean bookBike(Booking booking) {
        String query = "INSERT INTO bookings (customer_name, bike_name, bike_year, booking_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, booking.getCustomerName());
            stmt.setString(2, booking.getBikeName());
            stmt.setInt(3, booking.getBikeYear());
            stmt.setString(4, booking.getBookingDate());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error while booking bike: " + e.getMessage());
            return false;
        }
    }

    // Main method to take input and book bike
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("📝 Book a Bike");
        System.out.print("Enter Customer Name: ");
        String customerName = sc.nextLine();

        System.out.print("Enter Bike Name: ");
        String bikeName = sc.nextLine();

        System.out.print("Enter Bike Year: ");
        int bikeYear = sc.nextInt();

        Booking booking = new Booking(customerName, bikeName, bikeYear);
        boolean success = bookBike(booking);

        if (success) {
            System.out.println("✅ Booking successful!");
        } else {
            System.out.println("❌ Booking failed.");
        }

        sc.close();
    }
}