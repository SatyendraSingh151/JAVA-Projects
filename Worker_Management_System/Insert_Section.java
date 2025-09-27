package Worker_Management_System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Insert_Section {
	
	public void insert() throws ClassNotFoundException, SQLException {
//		1. Load and Register the Driver
		Class.forName("org.postgresql.Driver");
		
//		2. Connection building to the database and the java program
		Connection con =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/company","postgres","tiger");
		
//		setting the connection to commit as false
		con.setAutoCommit(false);
		
		int rows = 0;
				
		Scanner sc = new Scanner(System.in);
//				taking the user input to insert the records as the user wants
				System.out.print("\nEnter the count of the records of the worker you want to insert into the data : ");
				int n = sc.nextInt();
				sc.nextLine(); // this will fix the scanner issue
				
//				running the loop to run the insertion program
				for(int i=1;i<=n;i++){
					
	//				3. statement creation
	//				3.1 -> here is the statement created with the help of the PreparedStatement interface
					String insertSQL = "insert into workers(wid,wname,waddress,wstatus,wdate) values(?,?,?,?,?) ";
					PreparedStatement insertps = con.prepareStatement(insertSQL);
					
	//				3.2 -> Now taking the user input for the placeholder defined
					System.out.print("\nEnter the worker id : ");
					int id = sc.nextInt();
					sc.nextLine(); // this will fix the scanner issue					
					
					System.out.print("Enter the worker name : ");
					String name = sc.nextLine();
					
					System.out.print("Enter the worker address : ");
					String address = sc.nextLine();
					
					System.out.print("Enter the worker  status (yes/no) : ");
					String status = sc.nextLine();
					
					System.out.print("Enter the worker date of birth (yyyy-mm-dd): ");
					String dateString = sc.nextLine();
					java.sql.Date wdate = java.sql.Date.valueOf(dateString);
					
	//				3.3 -> now sending the data at the place of the placeholder by the help of the setter
					insertps.setInt(1, id);
					insertps.setString(2, name.toUpperCase());
					insertps.setString(3, address.toUpperCase());
					insertps.setString(4, status.toUpperCase());
					insertps.setDate(5, wdate);
					
	//				now we will add the query into the batch
					insertps.addBatch();
					
	//				4. now we will execute the query
					insertps.executeUpdate(); 
					
					rows++; //here it will return the total count of the change done
				}
				
//				now we will commit the SQL query
				con.commit();
				
				System.out.println("\n The records of "+rows+" worker is inserted sucessfully.");
				
//				5.nothing here to process the result so we will skip this
				
//				6. now we will close the connection
				con.close();
				
//				now breaking this condition
				
				
			}
		


}
