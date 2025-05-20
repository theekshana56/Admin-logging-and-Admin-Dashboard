<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Login | Admin Dashboard</title>
  <link rel="icon" href="https://cdn-icons-png.flaticon.com/512/3135/3135715.png" type="image/png" />
  <script src="https://cdn.tailwindcss.com"></script>
</head> 
  
<body class="bg-gray-900 text-white min-h-screen flex items-center justify-center px-4">
  <div class="bg-gray-800 shadow-lg rounded-lg w-full max-w-md p-8">
    <div class="mb-6 text-center">
      <h1 class="text-3xl font-bold text-red-500">Welcome</h1>
      <p class="text-gray-300 mt-2">Login to your admin account</p>
    </div>



    <!-- Admin Login Form -->
    <form id="adminForm" action="${pageContext.request.contextPath}/login" method="POST" class="space-y-4">
      <div>
        <label for="adminEmail" class="block text-sm text-gray-200 mb-1">Admin Email</label>
        <input type="email" id="adminEmail" name="adminEmail" required
               class="w-full px-4 py-2 rounded bg-gray-700 border border-gray-600 text-white focus:outline-none focus:ring-2 focus:ring-red-500" />
      </div>
      <div>
        <label for="adminPassword" class="block text-sm text-gray-200 mb-1">Password</label>
        <input type="password" id="adminPassword" name="adminPassword" required
               class="w-full px-4 py-2 rounded bg-gray-700 border border-gray-600 text-white focus:outline-none focus:ring-2 focus:ring-red-500" />
      </div>
          <% if (request.getAttribute("errorMessage") != null) { %>
      <div class="text-red-500 py-1 rounded mb-4 text-center">
        <%= request.getAttribute("errorMessage") %>
      </div>
    <% } %>
      
      <button type="submit" class="w-full bg-red-600 hover:bg-red-700 text-white py-2 rounded">
        Login as Admin
      </button>
    </form>
  </div>
  
</body>
</html>