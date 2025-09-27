package Worker_Management_System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Truncate_Section {
	
	public void truncate() throws ClassNotFoundException, SQLException {
		
//		1. Load and Register the Driver
		Class.forName("org.postgresql.Driver");
		
//		2. Connection building to the database and the java program
		Connection con =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/company","postgres","tiger");
		
//		setting the connection to commit as false
		con.setAutoCommit(false);
		
//		3. statement creation
		String insertSQL = "truncate table workers ";
//		3.1 -> here is the statement created with the help of the PreparedStatement interface
		PreparedStatement insertps = con.prepareStatement(insertSQL);

//		now we will add the query into the batch
		insertps.addBatch();
			
//		4. now we will execute the query
		insertps.executeUpdate();
			
//		now we will commit the SQL query
		con.commit();
		
		System.out.println("\n All the records of the workers deleted sucessfully.");
		
//		5.nothing here to process the result so we will skip this
		
//		6. now we will close the connection
		con.close();

	}
	
}
