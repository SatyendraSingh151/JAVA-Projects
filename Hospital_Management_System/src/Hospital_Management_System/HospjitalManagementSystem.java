package Hospital_Management_System;

import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HospjitalManagementSystem {

    private static final String url = "jdbc:mysql://localhost:3306/Hospital";

    private static final String username = "root";

    private static final String password = "tiger";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            Patient patient = new Patient(connection, scanner);
            Doctor doctor = new Doctor((connection));
            while (true) {
                System.out.println("HOSPITAL MANAGEMENT SYSTEM");
                System.out.println("1. Add Patients");
                System.out.println("2. View Patients");
                System.out.println("3. View Doctors");
                System.out.println("4. Book Appointments");
                System.out.println("5. Exit");
                System.out.println("Enter your Choice : ");
                int choice = scanner.nextInt();

//                making the choice
                switch (choice) {
                    case 1:
//                        add patient
                        patient.addPatient();

                    case 2:
//                        view patient
                        patient.viewPatients();
                        break;

                    case 3:
//                        view doctors
                        doctor.viewDoctor();
                        break;

                    case 4:
//                         book appointment
                        bookAppointment(patient,doctor,connection,scanner);
                        break;

                    case 5:
//                        to exit
                        return;

                    default:
                        System.out.println("Enter a valid choice");
                        break;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    //    making the method to book the appointment
    public static void bookAppointment(Patient patient, Doctor doctor, Connection connection, Scanner scanner) {
        System.out.println("Enter patient id: ");
        int patientId = scanner.nextInt();

        System.out.println("Enter Doctor id: ");
        int doctorId = scanner.nextInt();

        System.out.println("Enter appointment date in the format (yyyy-mm-dd)");
        String appointmentDate = scanner.next();

        if (patient.getPatientById(patientId) && doctor.getDoctorById(doctorId)) {

            if (checkDoctorAvailability(doctorId, appointmentDate, connection)) {
                String appointmentQuery = "INSERT INTO appointments (patient_id, doctor_id, appointment_date) VALUES (?, ?, ?";

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1, patientId);
                    preparedStatement.setInt(2, doctorId);
                    preparedStatement.setString(3, appointmentDate);
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Appointment BOOKED !");
                    } else {
                        System.out.println("Failed to book doctor appointment !");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("Doctor not available on this date");
            }

        } else {
            System.out.println("Either doctor or patient does not exist.");
        }


    }


    //    to check doctor availability
    public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection) {
        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, appointmentDate);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count == 0) {
                    return true;
                } else {
                    return false;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

}