package com.onlineQuiz;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProgressTracking 
{
    public static void saveAttempt(Connection conn, int userId, int quizId, int score) throws Exception 
    {
        String query = "INSERT INTO QuizAttempts VALUES (?, ?, ?, NOW())";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, userId);
        stmt.setInt(2, quizId);
        stmt.setInt(3, score);
        stmt.executeUpdate();
        System.out.println("Attempt saved successfully.");
    }
}
