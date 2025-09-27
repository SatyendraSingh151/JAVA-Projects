package Worker_Management_System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class View_Section {
	public void view() throws ClassNotFoundException, SQLException {
//		1. Load and Register the Driver
		Class.forName("org.postgresql.Driver");
		
//		2. Building the connection
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/company","postgres","tiger");
		
//		3.statement creation
		PreparedStatement ps = con.prepareStatement("select * from workers");
		
//		4. executing the query
		ResultSet rs = ps.executeQuery();
		
//		5. Now processing the result
		//5. Now processing the result
		
		System.out.println("\nThe records of the workers working here are :");
		boolean foundData = false;
		while(rs.next()) {
		    foundData = true;
		    System.out.println("\nID : "+rs.getInt("wid")+", NAME : "+rs.getString("wname")+", ADDRESS : "+rs.getString("waddress")+", PRESENT :  "+rs.getString("wstatus")+", DATE : "+rs.getDate("wdate"));
		}
		if(!foundData) {
		    System.out.println("\nNo records found in the workers table.");
		}

		
//		6. Closing the connection
		con.close();
		
	}


}
