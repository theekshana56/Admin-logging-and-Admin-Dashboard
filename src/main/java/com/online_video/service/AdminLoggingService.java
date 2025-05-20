package com.online_video.service;

import com.online_video.model.AdminLogging;
import com.online_video.utill.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoggingService {

    public AdminLogging getAdminByEmail(String email) throws SQLException {
        AdminLogging admin = null;
        String query = "SELECT ID, UserName, Email, Password FROM Admin WHERE Email = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    admin = new AdminLogging();
                    admin.setId(rs.getInt("ID"));
                    admin.setUsername(rs.getString("UserName"));
                    admin.setEmail(rs.getString("Email"));
                    admin.setPassword(rs.getString("Password"));
                }
            }
        }
        return admin;
    }
}