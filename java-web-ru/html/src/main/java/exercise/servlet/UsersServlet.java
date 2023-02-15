package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;


public class UsersServlet extends HttpServlet {

    public static final String htmlTable = """
            <html>
            <head>
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
            </head>
            <style>
            table, th, td {
              border: 1px solid black;
              border-collapse: collapse;
            }
            </style>
            <body>
            <table>
                %s
            <table>
            </body>
            </html>
            """;

    public static final String htmlUserRow = """
            <tr>
                <td>%s</td>
                <td><a href=\"/users/%s\">%s</a></td>
            </tr>
            """;

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);

    }

    private static List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        Path filePath = Paths.get("src", "main", "resources", "users.json")
                .toAbsolutePath().normalize();

        String data = Files.readString(filePath);

        ObjectMapper dataMapper = new ObjectMapper();
        return dataMapper.readValue(data, dataMapper.getTypeFactory().constructCollectionType(List.class, Map.class));
        // END
    }

    private static void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {
        PrintWriter pw = response.getWriter();

        // BEGIN
        List<Map<String, String>> users = getUsers();
        StringBuilder userRows = new StringBuilder("");
        userRows.append("""
                <thead>
                    <tr>
                        <th colspan="2">List of users</th>
                    </tr>
                </thead>
                <tr>
                    <th>ID</th>
                    <th>Users full Name</th>
                </tr>
                """);

        for (Map<String, String> user : users) {
            String fullName = user.get("firstName") + " " + user.get("lastName");
            userRows.append(htmlUserRow.formatted(user.get("id"), user.get("id"), fullName));
        }
        pw.println(htmlTable.formatted(userRows));
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN

//        response.sendError(404, "");
        PrintWriter pw = response.getWriter();
        List<Map<String, String>> users = getUsers();

        StringBuilder userRows = new StringBuilder("");
        userRows.append("""
                <thead>
                    <tr>
                        <th colspan="2">Users Data</th>
                    </tr>
                </thead>
                <tr>
                    <th>Param</th>
                    <th>Value</th>
                </tr>
                """);

//        long res = users.stream().filter(i -> i.containsValue(
//        id)).count();
        if (users.stream().noneMatch(i -> i.containsValue(id))) {
            response.sendError(404);
        }

        users.stream()
                .forEach(m -> {
                    if (m.containsValue(id)) {
                        for (String data : m.keySet()) {
                            userRows.append("""
                             <tr>
                                <td>%s</td>
                                <td>%s</td>
                            </tr>
                            """.formatted(data, m.get(data)));
                        }
                    }
                });



        pw.println(htmlTable.formatted(userRows));
        // END
    }
}
