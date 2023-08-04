package servlet;

import dao.DatabaseOperationsDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/EventCreator")
public class EventCreatorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();

        // Fetching data from user inputs
        String eNumber = req.getParameter("eNumber");
        String eName = req.getParameter("eName");
        String cName = req.getParameter("cName");
        String cNumber = req.getParameter("cNumber");
        String eFee = req.getParameter("eFee");
        String eVenue = req.getParameter("eVenue");
        String eDate = req.getParameter("eDate");

        try {
            DatabaseOperationsDao dbOperations = new DatabaseOperationsDao();

            String insertQuery = "INSERT INTO eventDetails(eNumber,eName,cName,cNumber,eFee,eVenue,eDate) VALUES (?,?,?,?,?,?,?)";

            // Use the insertData method from DatabaseOperations
            boolean insertionResult = dbOperations.insertData(insertQuery, eNumber, eName, cName, cNumber, eFee, eVenue, eDate);

            if (insertionResult) {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("EventCreator.html");
                requestDispatcher.include(req, resp);
                writer.println("<center><h1>!! Event Created !!</h1></center>");
                System.out.println("Event added to Database!");
            } else {
                writer.println("<center><h1>!! Event Creation Failed !!</h1></center>");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }

        writer.close();
    }
}
