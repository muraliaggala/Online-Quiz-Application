package com.onlineQuiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class QuizTaking 
{

    public static void takeQuiz(Connection conn, int quizId) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int score = 0;
        int questionCount = 0;

        String query = "SELECT question, option1, option2, option3, option4, correct_option FROM questions WHERE quiz_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, quizId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) 
        {
            questionCount++;
            System.out.println("\nQuestion " + questionCount + ": " + rs.getString("question"));
            System.out.println("1. " + rs.getString("option1"));
            System.out.println("2. " + rs.getString("option2"));
            System.out.println("3. " + rs.getString("option3"));
            System.out.println("4. " + rs.getString("option4"));

            System.out.print("Your answer (1-4): ");
            int userAnswer = sc.nextInt();

            int correctAnswer = rs.getInt("correct_option");
            if (userAnswer == correctAnswer) 
            {
                System.out.println("Correct!");
                score++;
            } 
            else 
            {
                System.out.println("Incorrect. The correct answer was option " + correctAnswer + ".");
            }
        }
        System.out.println("\nQuiz completed! Your score: " + score + "/" + questionCount);
    }
}
