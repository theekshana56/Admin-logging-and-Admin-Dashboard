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

@SuppressWarnings("serial")
@WebServlet("/editAdmin")
public class EditAdminServlet extends HttpServlet {
    private AdminService adminService;

    @Override
    public void init() throws ServletException {
        adminService = new AdminService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Admin admin = adminService.getAdminById(id);
            if (admin == null) {
                request.setAttribute("errorMessage", "Admin not found with ID: " + id);
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }
            request.setAttribute("admin", admin);
            request.getRequestDispatcher("/AdminActions/editAdmin.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unable to fetch admin: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid admin ID");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String userName = request.getParameter("userName");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            String password = request.getParameter("password");

            Admin admin = new Admin();
            admin.setId(id);
            admin.setUserName(userName);
            admin.setEmail(email);
            admin.setPhoneNumber(phoneNumber);
            admin.setPassword(password);

            adminService.updateAdmin(admin);
            response.sendRedirect("admin");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unable to update admin: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid admin ID");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}