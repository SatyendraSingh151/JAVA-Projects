package Hospital_Management_System;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {

    private Connection connection;


    public Doctor(Connection connection){
        this.connection= connection;

    }




//  making the method to view the  list of the patients

    public void viewDoctor(){
        String query = "select * from doctors";

//      making the try block exception
        try{
//    this takes the query as an arguments
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
//    we will use the format specifier here to print the multiple data
            System.out.println("Doctors: ");
            System.out.println("+----------- +-----------------+----------------------+");
            System.out.println("| Doctor id | Name             | Specialization       |");
            System.out.println("+----------- +-----------------+----------------------+");
            while(resultSet.next()){
                int id  = resultSet.getInt("id");
                String name = resultSet.getString("name");

                String specialization = resultSet.getString("specialization");

                System.out.printf("| %-12s | %-20s | %-12s \n",id,name,specialization);
                System.out.println("+----------- +-----------------+----------------------+");            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    //  now making the check patient method
    public boolean getDoctorById(int id){
        String query = "SELECT * FROM DOCTORS WHERE ID = ?";

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
