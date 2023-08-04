package servlet;

import dao.DatabaseOperationsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/eventViewer")

public class EventViewerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Event Viewer</title>");
        writer.println("<link rel=\"stylesheet\" href=\"css/style.css\">");
        writer.println("<link href=\"https://fonts.googleapis.com/css2?family=Balsamiq+Sans&display=swap\" rel=\"stylesheet\">");
        writer.println("/head");
        writer.println("<body>");

        try {

            DatabaseOperationsDao databaseOperationsDao = new DatabaseOperationsDao();

            resp.setContentType("text/html");

            String query = "SELECT * FROM eventDetails";

            ResultSet resultSet = databaseOperationsDao.retrieveData(query);

            writer.println("<center><h1>Event Details</h1></center>");
            writer.println("<br>");
            writer.println("<div>");

            writer.println("<center>");
            writer.println("<table border=1 width=50% height=50%>");
            writer.println("<tr><th>Event Number</th><th>Event Name</th><th>Coordinator</th><th>Co Contact</th><th>Fee</th><th>Venue</th><th>Date</th>");

            while (resultSet.next()){

                String eNumber = resultSet.getString("eNumber");
                String eName = resultSet.getString("eName");
                String cName = resultSet.getString("cName");
                String cNumber = resultSet.getString("cNumber");
                String eFee = resultSet.getString("eFee");
                String eVenue = resultSet.getString("eVenue");
                String eDate = resultSet.getString("eDate");
                writer.println("<tr><td>" +eNumber+"</td><td>"+eName+"</td><td>"+cName+"</td><td>"+cNumber+"</td><td>"+eFee+"</td><td>"+eVenue+"</td><td>"+eDate+"</td></tr>");


            }
            writer.println("</table>");
            writer.println("</center>");
            writer.println("</body>");
            writer.println("</html>");


        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();

        }finally {
            writer.close();
        }
    }
}
