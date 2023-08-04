package servlet;

import dao.DatabaseOperationsDao;
import db.DBConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/eventRegistrar")
public class EventRegistrarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        // Fetching User Inputs
        String eName = req.getParameter("eName");
        String eNum = req.getParameter("eNum");
        String cardNum = req.getParameter("cardNum");
        String eDate = req.getParameter("eDate");
        String cardCVV = req.getParameter("cardCVV");
        String cHoldersName = req.getParameter("cHoldersName");

        try {
            DatabaseOperationsDao dbOperations = new DatabaseOperationsDao();

            String insertQuery = "INSERT INTO paymentdetails(eName,eNum,cardNum,eDate,cardCVV,cHoldersName) VALUES (?,?,?,?,?,?)";

            // Use the insertData method from DatabaseOperations
            boolean insertionResult = dbOperations.insertData(insertQuery, eName, eNum, cardNum, eDate, cardCVV, cHoldersName);

            if (insertionResult) {
                writer.println("<center><h1>Payment Successfully Processed!</h1><center>");
            } else {
                writer.println("<center><h1>Payment Processing Failed!</h1><center>");
            }

            RequestDispatcher reqRequestDispatcher = req.getRequestDispatcher("EventRegistrar.html");
            reqRequestDispatcher.include(req, resp);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
}
