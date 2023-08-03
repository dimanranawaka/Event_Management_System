package servlet;

import db.DBConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

            DBConnection instance = DBConnection.getInstance();

            String sql = "INSERT INTO eventDetails(eNumber,eName,cName,cNumber,eFee,eVenue,eDate) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = instance.getConnection().prepareStatement(sql);

            preparedStatement.setString(1,eNumber);
            preparedStatement.setString(2,eName);
            preparedStatement.setString(3,cName);
            preparedStatement.setString(4,cNumber);
            preparedStatement.setString(5,eFee);
            preparedStatement.setString(6,eVenue);
            preparedStatement.setString(7,eDate);

            preparedStatement.executeUpdate();

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("EventCreator.html");
            requestDispatcher.include(req,resp);

            writer.println("<center><h1>!! Event Created !!</h1></center>");

            System.out.println("Event added to Database!");

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException e) {

            System.out.println(e);

        }
        writer.close();
    }
}
