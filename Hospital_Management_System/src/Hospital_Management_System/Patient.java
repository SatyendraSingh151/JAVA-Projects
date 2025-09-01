package Hospital_Management_System;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {

  private Connection connection;
  private Scanner scanner;

  public Patient(Connection connection, Scanner scanner){
      this.connection= connection;
      this.scanner=scanner;
  }

  public void addPatient(){
      System.out.println("Enter the patient name: ");
      String name =scanner.next();

      System.out.println("Enter the patient age: ");
      int age = scanner.nextInt();
      System.out.print("Enter Patient Gender: ");
      String gender = scanner.next();

//      using the try and catch block
      try{
//          inserting the values into the table
          String query = "INSERT INTO patients(name,age,gender) VALUES(? ,?,?)";

//      making the object reference of the PreparedStatement to insert the value into the table
          PreparedStatement preparedStatement = connection.prepareStatement(query);

//          setting the value into the table
          preparedStatement.setString(1,name);
          preparedStatement.setInt(2,age);
          preparedStatement.setString(3,gender);

//          executeUpdate() returns the integer type value
          int affectRows = preparedStatement.executeUpdate();

          if(affectRows>0){
              System.out.println("Patient added successfully");
          }
          else{
              System.out.println("Failed to  add Patient!!");
          }


      }catch (SQLException e){
          e.printStackTrace();

      }

  }


//  making the method to view the  list of the patients

  public void viewPatients(){
        String query = "select * from Patients";

//      making the try block exception
try{
//    this takes the query as an arguments
    PreparedStatement preparedStatement = connection.prepareStatement(query);

    ResultSet resultSet = preparedStatement.executeQuery();
//    we will use the format specifier here to print the multiple data
    System.out.println("Patients: ");
    System.out.println("+----------- +-----------------+--------------+--------------+");
    System.out.println("| Patient id | Name             | Age         | Gender       |");
    System.out.println("+----------- +-----------------+--------------+--------------+");
    while(resultSet.next()){
        int id  = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int age = resultSet.getInt("age");
        String gender = resultSet.getString("gender");

        System.out.printf("|%-12s|%-20s|%-10s|%-12s\n",id,name,age,gender);
        System.out.println("+----------- +-----------------+--------------+--------------+");
    }

}catch(SQLException e){
    e.printStackTrace();
}
  }


//  now making the check patient method
    public boolean getPatientById(int id){
      String query = "SELECT * FROM PATIENTS WHERE ID + ?";

      try{
          PreparedStatement preparedStatement = connection.prepareStatement(query);
          preparedStatement.setInt(1,id);

//          data comes in this instance
          ResultSet resultSet = preparedStatement.executeQuery();

          if(resultSet.next()){
              return true;
          }
          else {
              return false;
          }


      }catch(SQLException e){
          e.printStackTrace();
      }
      return false;

    }



}
