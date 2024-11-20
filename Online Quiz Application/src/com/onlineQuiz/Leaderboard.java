package com.onlineQuiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Leaderboard {
    public static void displayLeaderboard(Connection conn, int quizId) throws Exception 
    {
        String query = "SELECT u.username, a.score FROM QuizAttempts a JOIN Users u ON a.user_id = u.id WHERE a.quiz_id = ? ORDER BY a.score DESC";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, quizId);
            ResultSet rs = stmt.executeQuery();

            System.out.println("Leaderboard:");
            while (rs.next()) 
            {
                System.out.println(rs.getString("username") + ": " + rs.getInt("score"));
            }
        }
    }
}
