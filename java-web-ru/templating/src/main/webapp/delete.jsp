<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<table>
    <h1>Do you have delete ${user.get("firstName")}</h1>
    <form action='/users/delete?id=${user.get("id")}' method="post">
        <button type="submit" class="btn btn-danger">Delete user</button>
    </form>
</table>
</body>
</html>
<!-- END -->
