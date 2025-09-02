import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class ShowroomMainApp {

    // ---------- Bike POJO ----------
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

    // ---------- Booking POJO ----------
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

    // ---------- JDBC Connection ----------
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/bikeshowroom";
        String user = "root";
        String password = "tiger"; // 🔐 Replace with your actual password
        return DriverManager.getConnection(url, user, password);
    }

    // ---------- Add Bike ----------
    public static boolean addBike(Bike bike) {
        String query = "INSERT INTO bikes (name, price, year) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, bike.getName());
            stmt.setDouble(2, bike.getPrice());
            stmt.setInt(3, bike.getYear());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error adding bike: " + e.getMessage());
            return false;
        }
    }

    // ---------- View Bikes ----------
    public static void viewBikes() {
        String query = "SELECT * FROM bikes";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n🏍️ Available Bikes:");
            System.out.println("----------------------------------");

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Price: ₹" + rs.getDouble("price"));
                System.out.println("Year: " + rs.getInt("year"));
                System.out.println("----------------------------------");
            }

            if (!found) {
                System.out.println("🚫 No bikes available.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error viewing bikes: " + e.getMessage());
        }
    }

    // ---------- Book Bike ----------
    public static boolean bookBike(Booking booking) {
        String query = "INSERT INTO bookings (customer_name, bike_name, bike_year, booking_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, booking.getCustomerName());
            stmt.setString(2, booking.getBikeName());
            stmt.setInt(3, booking.getBikeYear());
            stmt.setString(4, booking.getBookingDate());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error booking bike: " + e.getMessage());
            return false;
        }
    }

    // ---------- Main Method ----------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n🚗 BIKE SHOWROOM SYSTEM");
            System.out.println("1. Add New Bike");
            System.out.println("2. View Available Bikes");
            System.out.println("3. Book a Bike");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Bike Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Bike Price: ");
                    double price = sc.nextDouble();
                    System.out.print("Enter Bike Year: ");
                    int year = sc.nextInt();
                    Bike bike = new Bike(name, price, year);
                    if (addBike(bike)) {
                        System.out.println("✅ Bike added successfully!");
                    } else {
                        System.out.println("❌ Failed to add bike.");
                    }
                    break;

                case 2:
                    viewBikes();
                    break;

                case 3:
                    System.out.print("Enter Customer Name: ");
                    String customer = sc.nextLine();
                    System.out.print("Enter Bike Name: ");
                    String bikeName = sc.nextLine();
                    System.out.print("Enter Bike Year: ");
                    int bikeYear = sc.nextInt();
                    Booking booking = new Booking(customer, bikeName, bikeYear);
                    if (bookBike(booking)) {
                        System.out.println("✅ Booking successful!");
                    } else {
                        System.out.println("❌ Booking failed.");
                    }
                    break;

                case 4:
                    System.out.println("👋 Exiting... Thank you!");
                    break;

                default:
                    System.out.println("⚠️ Invalid choice. Try again.");
            }

        } while (choice != 4);

        sc.close();
    }
}