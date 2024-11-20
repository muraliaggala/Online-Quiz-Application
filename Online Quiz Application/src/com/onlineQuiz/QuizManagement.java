package com.onlineQuiz;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class QuizManagement 
{
    public static void addQuiz(Connection conn, String title, String description) throws Exception 
    {
        String query = "INSERT INTO Quizzes VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, title);
        stmt.setString(2, description);
        stmt.executeUpdate();
        System.out.println("Quiz added successfully.");
    }

    
    public static void addQuestion(Connection conn, int quizId, String questionText, String options, String correctAnswer) throws Exception 
    {
        String query = "INSERT INTO Questions VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, quizId);
        stmt.setString(2, questionText);
        stmt.setString(3, options);
        stmt.setString(4, correctAnswer);
        stmt.executeUpdate();
        System.out.println("Question added successfully.");
    }
}
