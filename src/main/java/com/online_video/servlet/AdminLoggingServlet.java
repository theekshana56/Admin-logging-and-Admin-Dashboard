package com.online_video.servlet;

import com.online_video.model.AdminLogging;
import com.online_video.service.AdminLoggingService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@SuppressWarnings({ "serial"})
@WebServlet("/login")
public class AdminLoggingServlet extends HttpServlet {
    private AdminLoggingService adminLoggingService;

    @Override
    public void init() throws ServletException {
        adminLoggingService = new AdminLoggingService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/AdminActions/AdminLogging.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("adminEmail");
        String password = request.getParameter("adminPassword");

        // Basic input validation
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Email and password are required.");
            request.getRequestDispatcher("/AdminActions/AdminLogging.jsp").forward(request, response);
            return;
        }

        try {
            AdminLogging admin = adminLoggingService.getAdminByEmail(email);
            if (admin == null) {
                request.setAttribute("errorMessage", "Invalid email or password.");
                request.getRequestDispatcher("/AdminActions/AdminLogging.jsp").forward(request, response);
                return;
            }

            
            if (password.equals(admin.getPassword())) {
                // Successful login
                HttpSession session = request.getSession();
                session.setAttribute("loggedInAdmin", admin);
                response.sendRedirect(request.getContextPath() + "/admin");
            } else {
                // Failed login
                request.setAttribute("errorMessage", "Invalid email or password.");
                request.getRequestDispatcher("/AdminActions/AdminLogging.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/AdminActions/AdminLogging.jsp").forward(request, response);
        }
    }
}