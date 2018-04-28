<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Math Question Bank</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"/>
    <script type="text/javascript"
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.4/MathJax.js?config=TeX-MML-AM_CHTML"></script>
</head>
<body>
<h2>Math Question Bank</h2>
<p>Welcome to the math question bank! You may browse the stored problems, manage their categorization, or submit your
    own problems to the bank.</p>
<h4>Math Problems</h4>
<p>The following is a list of all math problems that match your criteria:</p>
<table class="table table-striped table-hover">
    <thead>
    <tr>
        <td>ID</td>
        <td>Content</td>
        <td>Actions</td>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="problems" type="java.util.List"--%>
    <c:forEach items="${problems}" var="prob">
        <tr>
            <td>${prob.pid}</td>
            <td>${prob.content}</td>
            <td>Action<br/>Action<br/>Action</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
