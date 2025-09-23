package Dynamically_All_In_One_Employee_Management_System;

import java.sql.SQLException;
import java.util.Scanner;

public class Student_Management_Main_control {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		making the object reference of the class which haves all the operations defined
		Operations_Done_At_One_Place ref = new Operations_Done_At_One_Place();
		
		boolean status = true;
		
//		creating the loop to run till the user wants
		while(status) {
			System.out.println("\n1 - Insert\n2 - Update\n3 - Delete\n4 - View\n5 - Exit");
			
			Scanner sc = new Scanner(System.in);
			System.out.print("make your choice to perform the actions - ");
			int choice = sc.nextInt();
			System.out.println();
			
//			now giving the switch case to select the prior task to do
			switch(choice) {
			case 1:{
				ref.insertion();
				break;
			}
			case 2:{
				ref.updation();
				break;
			}
			case 3:{
				ref.delete();
				break;
			}
			case 4:{
				ref.view();
				break;
			}
			case 5:{
				status = false;
				System.out.println("Thank you for using the Student Management System, Hope it was a good experience here.");
				break;
			}
			default:{
				System.out.println("Opps! you made a wrong choice, try again.");
			}
			}
			
		}

	}

}
