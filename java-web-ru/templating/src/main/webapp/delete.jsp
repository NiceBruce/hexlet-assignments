<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<style type="text/css">
    button {
        background-color: lightseagreen;
        border: none;
        color: white;
        padding: 30px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 25px;
        margin: 50px 600px;
        cursor: pointer;
    }
    .button:hover {
        background: red;
        font-size: 30px;
    }

    .button:focus {
        outline: none;
        box-shadow: 0 0 0 4px darkgrey;
    }
</style>
<body>
<table>
    <h1 align="center">Hm....Do you have delete ${user.get("firstName")}?</h1>
    <form action='/users/delete?id=${user.get("id")}' method="post">
        <button type="submit" class="button">Delete user</button>
    </form>
</table>
</body>
</html>
<!-- END -->
