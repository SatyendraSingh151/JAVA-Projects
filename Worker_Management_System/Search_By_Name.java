package Worker_Management_System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Search_By_Name {
	
	Scanner sc = new Scanner(System.in);
	
	public void searchByName() throws ClassNotFoundException, SQLException {
//		1. Loading and Registering the Driver
		Class.forName("org.postgresql.Driver");
		
//		2. Making the connection to connect the JDBC and the Java program
		Connection con =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/company","postgres","tiger");
		
//		3. creation of the statement
		String searchQuery = "select * from workers where wname LIKE ?";
		PreparedStatement ps = con.prepareStatement(searchQuery);
		
//		taking the user input to search the worker
		System.out.print("\nGive the name of the worker to search in the list : ");
		String name = sc.nextLine();
		
//		sending the name in the place of the placeholder to search
		ps.setString(1, name.toUpperCase());
		
//		executing the query
		ResultSet rs = ps.executeQuery();
		
		
//		5. Processing the result
		
		boolean workerFound = false;
		while(rs.next()) {
			workerFound = true;
			System.out.println("\nYes, This worker is present here.");
			System.out.println("\nID : "+rs.getInt("wid")+", NAME : "+rs.getString("wname")+", ADDRESS : "+rs.getString("waddress")+", PRESENT :  "+rs.getString("wstatus")+", DATE : "+rs.getDate("wdate"));
		}
		if(!workerFound) {
			System.out.println("\nSorry, this worker is not available here.");
		}
		
//		6. Closing the connection
		con.close();
		
	}

}
