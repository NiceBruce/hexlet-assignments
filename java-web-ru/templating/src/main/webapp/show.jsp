<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<style type="text/css">
    table, th, td {
        margin: auto;
        text-align: center;
        width: 600px;
        border: 1px solid black;
        border-collapse: collapse;
    }
    TH {
        background: #fc0;
        height: 40px;
        vertical-align: center;
        padding: 0;
    }
</style>
<body>
<table>
    <thead>
    <tr>
        <th colspan="2">Users Data</th>
    </tr>
    </thead>
    <tr>
        <th>Param</th>
        <th>Value</th>
        <th><a href='/users/delete?id=${user.get("id")}'>Delete this User?</a></th>
    </tr>
    <c:forEach var="data" items="${user.keySet()}">
        <tr>
            <td>${data}</td>
            <td>${user.get(data)}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
<!-- END -->

