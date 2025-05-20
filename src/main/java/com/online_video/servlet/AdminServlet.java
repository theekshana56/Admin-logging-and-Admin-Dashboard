package com.online_video.servlet;

import com.online_video.model.Admin;
import com.online_video.service.AdminService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("serial")
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private AdminService adminService;

    @Override
    public void init() throws ServletException {
        adminService = new AdminService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Admin> admins = adminService.getAllAdmins();
            request.setAttribute("admins", admins);
            request.getRequestDispatcher("/AdminActions/AdminDashBoard.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unable to fetch admin records: " + e.getMessage());
            request.getRequestDispatcher("AdminActions/error.jsp").forward(request, response);
        }
    }
}