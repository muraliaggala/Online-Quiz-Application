package com.onlineQuiz;
import java.sql.*;
import java.util.Scanner;

public class UserAuthentication 
{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/OnlineQuiz";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "Mounu123";

    public static void main(String[] args)throws Exception 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Sign Up\n2. Login");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) 
        {
            signUp(sc);
        } 
        else if (choice == 2) 
        {
            login(sc);
        } 
        else 
        {
            System.out.println("Invalid choice.");
        }
    }

    
    private static void signUp(Scanner sc) throws Exception
    {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement pst = conn.prepareStatement("INSERT INTO Users VALUES (?, ?, ?)");
        

        pst.setString(1, username);
        pst.setString(2, email);
        pst.setString(3, password);
        pst.executeUpdate();

        System.out.println("-----Signup successful!------");
       
    }

    
    private static void login(Scanner sc) throws Exception
    {
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM Users WHERE email = ? AND password = ?");

        pst.setString(1, email);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) 
        {
            System.out.println("Login successful! Welcome, " + rs.getString("username"));
            mainMenu(conn);
        } 
        else 
        {
            System.out.println("Invalid credentials.");
        }
    }
    
    
    public static void mainMenu(Connection conn) throws Exception 
    {
        Scanner sc = new Scanner(System.in);
        
        UserAuthentication user = new UserAuthentication();
        while (true) 
        {
            System.out.println("\n===== Online Quiz Application =====");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Take Quiz");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) 
            {
                case 1:
                	user.signUp(sc);
                    break;
                case 2:
                	user.login(sc);
                    break;
                case 3:
                    System.out.print("Enter Quiz ID to take: ");
                    int quizId = sc.nextInt();
                    QuizTaking.takeQuiz(conn, quizId);
                    break;
                case 4:
                    System.out.println("Thank you for using the Online Quiz Application!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

}
