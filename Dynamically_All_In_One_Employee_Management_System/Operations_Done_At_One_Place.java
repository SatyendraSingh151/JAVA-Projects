package Dynamically_All_In_One_Employee_Management_System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Operations_Done_At_One_Place {
	
//	making a function to insert the value
	
	public void insertion() throws ClassNotFoundException, SQLException {
//		1. Loading and Registering the Driver here, We use forName() which is the static method present in Class class
		Class.forName("org.postgresql.Driver"); //this will throw the ClassNotFoundException
		
//		2. we will build the connection for the Java program and the database by the help of getConnection() which is the static method in DriverManager class
		Connection con =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/company","postgres","tiger");// this will throw SQLException and will return Connection type 
		
//		3. now we will create a statement by the help of connection reference along with prepareStatement(), by using the PreparedStatement interface
//		3.1 => here we will create a placeholder for the values to perform the operations
		PreparedStatement ps =  con.prepareStatement("insert into employee(empid,ename,designation,salary) values(?,?,?,?) "); //this is going to be with the help of the PreparedStatement interface
		
//		3.2 => now we will take the input from the user to perform the operation
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the employee id : ");
		int id = sc.nextInt();
		
		System.out.print("Enter the employee name : ");
		String name = sc.next();
		
		System.out.print("Enter the employee designation : ");
		String designation = sc.next();
		
		System.out.print("Enter the employee salary : ");
		double salary = sc.nextDouble();
		
//		3.3 => now we will pass the user taken value to the placeholder with the help of the PreparedStatement object reference and setter
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setString(3, designation);
		ps.setDouble(4, salary);
		
		
//		4. now here we will execute the query by the help of the PreparedStatement object reference along with the executeUpdate()
		ps.executeUpdate();
		
		System.out.println("\nInsertion of the data is done sucessfully.");
		
//		5. here nothing is there to process as a result
//		6. at last we will close the connection
		con.close();
	}
	
	
//	making a function to update in the record
	
	public void updation() throws ClassNotFoundException, SQLException {
//		1. Loading and Registering the Driver here, We use forName() which is the static method present in Class class
		Class.forName("org.postgresql.Driver"); //this will throw the ClassNotFoundException
		
//		2. we will build the connection for the Java program and the database by the help of getConnection() which is the static method in DriverManager class
		Connection con =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/company","postgres","tiger");// this will throw SQLException and will return Connection type 
		
//		3. now we will create a statement by the help of connection reference along with prepareStatement(), by using the PreparedStatement interface
//		3.1 => here we will create a placeholder for the values to perform the operations
		PreparedStatement ps =  con.prepareStatement("update employee set designation = ? , salary =? where empid = ?");		
//		3.2 => now we will take the input from the user to perform the operation
		Scanner sc = new Scanner(System.in);
	
		System.out.print("Enter the employee designation : ");
		String designation = sc.next();
		
		System.out.print("Enter the employee salary : ");
		double salary = sc.nextDouble();
		
		System.out.print("Enter the employee id : ");
		int id = sc.nextInt();
		
//		3.3 => now we will pass the user taken value to the placeholder with the help of the PreparedStatement object reference and setter
		ps.setString(1, designation);
		ps.setDouble(2, salary);
		ps.setInt(3, id);
		
		
//		4. now here we will execute the query by the help of the PreparedStatement object reference along with the executeUpdate()
		ps.executeUpdate();
		
		System.out.println("\nUpdation of the data is done sucessfully.");
		
//		5. here nothing is there to process as a result
//		6. at last we will close the connection
		con.close();
	}
	
	
	
//	making a function to delete the record of the employee
	
	public void delete() throws ClassNotFoundException, SQLException {
//		1. Loading and Registering the Driver here, We use forName() which is the static method present in Class class
		Class.forName("org.postgresql.Driver"); //this will throw the ClassNotFoundException
		
//		2. we will build the connection for the Java program and the database by the help of getConnection() which is the static method in DriverManager class
		Connection con =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/company","postgres","tiger");// this will throw SQLException and will return Connection type 
		
//		3. now we will create a statement by the help of connection reference along with prepareStatement(), by using the PreparedStatement interface
//		3.1 => here we will create a placeholder for the values to perform the operations
		PreparedStatement ps =  con.prepareStatement("delete from employee where empid = ?");		
//		3.2 => now we will take the input from the user to perform the operation
		Scanner sc = new Scanner(System.in);
	
		System.out.print("Enter the employee id : ");
		int id = sc.nextInt();
		
//		3.3 => now we will pass the user taken value to the placeholder with the help of the PreparedStatement object reference and setter
		ps.setInt(1, id);
		
		
//		4. now here we will execute the query by the help of the PreparedStatement object reference along with the executeUpdate()
		ps.executeUpdate();
		
		System.out.println("\ndeletion of the record is done sucessfully.");
		
//		5. here nothing is there to process as a result
//		6. at last we will close the connection
		con.close();
	}
	
	
//	making a function to show/view the records of the employee
	
	public void view() throws ClassNotFoundException, SQLException {
//		1. Loading and Registering the Driver here, We use forName() which is the static method present in Class class
		Class.forName("org.postgresql.Driver"); //this will throw the ClassNotFoundException
		
//		2. we will build the connection for the Java program and the database by the help of getConnection() which is the static method in DriverManager class
		Connection con =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/company","postgres","tiger");// this will throw SQLException and will return Connection type 
		
//		3. now we will create a statement by the help of connection reference along with prepareStatement(), by using the PreparedStatement interface
		PreparedStatement ps =  con.prepareStatement("select * from employee");		
		
		
//		4. now here we will execute the query by the help of the PreparedStatement object reference along with the executeQuery()
		ResultSet rs =  ps.executeQuery();
		
//		5. processing the result with the help of the ResultSet object reference and the next()
		while(rs.next()) {
			System.out.println(rs.getInt("empid")+" "+rs.getString("ename")+" "+rs.getString("designation")+" "+rs.getDouble("salary"));
		}
		
		System.out.println("\nSucessfully, the records are displayed.");
		

//		6. at last we will close the connection
		con.close();
	}

}
