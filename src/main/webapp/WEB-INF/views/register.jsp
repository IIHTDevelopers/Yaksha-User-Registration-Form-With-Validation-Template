<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration</title>
</head>
<body>
    <h1>User Registration</h1>

    <!-- Display form errors if any -->
    <c:if test="${not empty org.springframework.validation.BindingResult.user}">
        <div style="color: red;">
            <ul>
                <c:forEach var="error" items="${org.springframework.validation.BindingResult.user.allErrors}">
                    <li>${error.defaultMessage}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>

    <!-- Start of Spring Form Tag -->
    <form:form action="/user/register" modelAttribute="user" method="POST">
        
        <!-- Name field -->
        <label for="name">Name:</label>
        <form:input type="text" path="name" id="name" required="true"/>
        <form:errors path="name" cssClass="error"/><br><br>

        <!-- Email field -->
        <label for="email">Email:</label>
        <form:input type="text" path="email" id="email" required="true"/>
        <form:errors path="email" cssClass="error"/><br><br>

        <!-- Password field -->
        <label for="password">Password:</label>
        <form:password path="password" id="password" required="true"/>
        <form:errors path="password" cssClass="error"/><br><br>

        <!-- Submit button -->
        <button type="submit">Register</button>
    </form:form>
</body>
</html>
