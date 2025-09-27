package Worker_Management_System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Delete_Section {

	public void delete() throws ClassNotFoundException, SQLException {
//		1. Load and Register the Driver
		Class.forName("org.postgresql.Driver");
		
//		2. Connection building to the database and the java program
		Connection con =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/company","postgres","tiger");
		
//		setting the connection to commit as false
		con.setAutoCommit(false);
		
		int rows = 0;
		
		Scanner sc = new Scanner(System.in);
		
//		taking the user input to insert the records as the user wants
		System.out.print("\nEnter the count of the worker you want to delete from the existing records : ");
		int n = sc.nextInt();
		sc.nextLine(); // this will fix the scanner issue
		
//		running the loop to run the insertion program
		for(int i=1;i<=n;i++){
			
//				3. statement creation
			String insertSQL = "delete from workers where wid = ? ";
//				3.1 -> here is the statement created with the help of the PreparedStatement interface
			PreparedStatement insertps = con.prepareStatement(insertSQL);
			
//				3.2 -> Now taking the user input for the placeholder defined
			System.out.print("\nEnter the worker id to delete the record : ");
			int id = sc.nextInt();
			
//				3.3 -> now sending the data at the place of the placeholder by the help of the setter
			insertps.setInt(1, id);
			
//				now we will add the query into the batch
			insertps.addBatch();
			
//				4. now we will execute the query
			insertps.executeUpdate();
			
			rows++;
			
		}
		
//		now we will commit the SQL query
		con.commit();
		
		System.out.println("\n The records of "+rows+" worker is deleted sucessfully.");
		
//		5.nothing here to process the result so we will skip this
		
//		6. now we will close the connection
		con.close();
	}
	
}
