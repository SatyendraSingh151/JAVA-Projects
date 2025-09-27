package Worker_Management_System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Updation {
	
	public void updation() throws ClassNotFoundException, SQLException {
//		1. Load and Register the Driver
		Class.forName("org.postgresql.Driver");
		
//		2. Connection building to the database and the java program
		Connection con =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/company","postgres","tiger");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\nEnter the count for which you want to update in the worker's details : ");
		int n = sc.nextInt();
		sc.nextLine();
		
		int rows = 0;
		
		for(int i=1;i<=n;i++) {
//			writing the statement
			String updateSQL = "update workers set wname = ?, waddress = ?, wstatus = ?, wdate = ? where wid = ?";
			PreparedStatement ps = con.prepareStatement(updateSQL);
			
//			taking the user input for the placeholder
			System.out.print("\nEnter the worker's id for which data is to be changed : ");
			int id = sc.nextInt();
			sc.nextLine(); //to take the cursor to the new line
			
			System.out.print("Enter the worker's name : ");
			String name = sc.nextLine();
			
			System.out.print("Enter the worker's address : ");
			String address = sc.nextLine();
			
			System.out.print("Enter the worker's status(Yes/No) : ");
			String status = sc.nextLine();
			
			System.out.print("Enter the worker date (yyyy-mm-dd): ");
			String dateString = sc.nextLine();
			java.sql.Date wdate = java.sql.Date.valueOf(dateString);
			
			
			
//			now sending the values to the place of the placeholder
			ps.setString(1, name.toUpperCase());
			ps.setString(2, address.toUpperCase());
			ps.setString(3, status.toUpperCase());
			ps.setDate(4, wdate);
			ps.setInt(5, id);
			
//			4. executing the query
			ps.executeUpdate();
			
			rows++;
		}

		
		System.out.println("\nThe details of"+ rows+" worker is updated sucessfully.");
		
//		5. nothing here to process the result
//		6. now closing the connection
		con.close();
	}
	


	

}
