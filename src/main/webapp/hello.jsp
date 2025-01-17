<!DOCTYPE html>
<html>
<head>
    <title>Hello</title>
</head>
<body>
    <h1>Welcome!</h1>
    <%
        // Check if a query parameter is provided
        String userName = request.getParameter("name");
        if (userName != null && !userName.isEmpty()) {
    %>
        <p>Hello, <%= userName %>! Welcome to our site.</p>
    <%
        } else {
    %>
        <p>Please provide your name in the URL as a query parameter (e.g., ?name=John).</p>
    <%
        }
    %>
</body>
</html>
