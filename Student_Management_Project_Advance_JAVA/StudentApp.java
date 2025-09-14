package Project_Advance_JAVA;



import java.util.*;

public class StudentApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        System.out.println("Welcome to the Student Management System");

        while (true) {
            System.out.println("\n1. Create Table\n2. Insert Student\n3. Show All Students\n4. Update Student Email\n5. Delete Student by Name\n6. Exit");
            System.out.print("Choose an action: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    dao.createTable();
                    break;
                case 2:
                    System.out.print("Enter SID: ");
                    int sid = sc.nextInt();
                    System.out.print("Enter Name: ");
                    String name = sc.next();
                    System.out.print("Enter Email: ");
                    String email = sc.next();
                    Student s = new Student(sid, name, email);
                    dao.insertStudent(s);
                    break;
                case 3:
                    List<Student> list = dao.getAllStudents();
                    for (Student st : list) {
                        System.out.println("SID: " + st.getSid() + ", Name: " + st.getSname() + ", Email: " + st.getSemail());
                    }
                    break;
                case 4:
                    System.out.print("Enter SID to update: ");
                    int upSid = sc.nextInt();
                    System.out.print("Enter new Email: ");
                    String newEmail = sc.next();
                    dao.updateStudentEmail(upSid, newEmail);
                    break;
                case 5:
                    System.out.print("Enter Name to delete: ");
                    String delName = sc.next();
                    dao.deleteStudentByName(delName);
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
