<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-900 text-white">
    <div class="p-6">
        <h1 class="text-2xl text-red-500">Error</h1>
        <p>${errorMessage}</p>
        <a href="admin" class="text-blue-400">Back to Dashboard</a>
    </div>
</body>
</html>