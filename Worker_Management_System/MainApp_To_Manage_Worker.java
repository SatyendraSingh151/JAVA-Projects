package Worker_Management_System;

import java.sql.SQLException;
import java.util.Scanner;

public class MainApp_To_Manage_Worker {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
				
//		1. making the object reference of the insert section
		Insert_Section insert = new Insert_Section();
		
//		2. making the object reference of the update class
		Updation update = new Updation();
		
//		3. making the object reference of the delete class
		Delete_Section delete = new Delete_Section();
		
//		4. making the object reference of the view class
		View_Section view = new View_Section();
		
//		5. making the object reference of the search class
		Search_By_Name nameSearch = new Search_By_Name();
		
//		making the object reference of the truncate class
		Truncate_Section truncate = new Truncate_Section();

		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nWelcome to \'workeres Management System\'");

		boolean status = true;
		
		while(status) {
//			giving the choice to select
			System.out.println("\n1 - View all records\n2 - Insert records\n3 - Update records\n4 - Delete records\n5 - Truncate(Delete all records)\n6 - Search worker by name\n7 - Exit");
			
//			displaying the message to the user
			System.out.print("\nEnter the choice to perform the action : ");
			int choice = sc.nextInt();
			
//			giving the switch condition
			switch(choice) {
				case 1:{
					view.view();
					break;					
				}
				case 2:{
					insert.insert();
					break;										
				}
				case 3:{
					update.updation();
					break;										
				}
				case 4:{
					delete.delete();
					break;
				}
				case 5:{
					truncate.truncate();
					break;					
				}
				case 6:{
					nameSearch.searchByName();
					break;
				}
				case 7:{  // this is to exit
					status = false;
					System.out.println("\nThank you for using the \"Worker Management System\".");
					break;
				}
				default:{
					System.out.println("\nOpps!, you made a wrong choice, Please try again.");
				}
			}
		}
		

	}

}


