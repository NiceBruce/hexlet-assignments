package exercise.servlet;

import exercise.Data;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;


@WebServlet("/companies")
public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        List<String> companies = getCompanies();
        PrintWriter out = response.getWriter();

        if (request.getQueryString() != null && request.getQueryString().contains("search")) {
            List<String> sortedCompanies = companies.stream()
                    .filter(i -> i.contains(request.getParameter("search")))
                    .collect(Collectors.toList());
            if (sortedCompanies.size() == 0) {
                out.println("Companies not found");
            } else {
                for (var company: sortedCompanies) {
                    out.println(company);
                }
            }
        } else {
            for (var company: companies) {
                out.println(company);
            }
        }
        // END
    }
}
