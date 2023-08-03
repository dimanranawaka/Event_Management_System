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
import java.sql.PreparedStatement;
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

            DBConnection instance = DBConnection.getInstance();

            String sql = "INSERT INTO paymentdetails(eName,eNum,cardNum,eDate,cardCVV,cHoldersName) VALUES (?,?,?,?,?,?)";

            PreparedStatement preparedStatement = instance.getConnection().prepareStatement(sql);

                preparedStatement.setString(1, eName);
                preparedStatement.setString(2, eNum);
                preparedStatement.setString(3, cardNum);
                preparedStatement.setString(4, eDate);
                preparedStatement.setString(5, cardCVV);
                preparedStatement.setString(6, cHoldersName);

                preparedStatement.executeUpdate();
                writer.println("<h1>Payment Successfully Processed!</h1>");
                RequestDispatcher reqRequestDispatcher = req.getRequestDispatcher("EventRegistrar.html");
                reqRequestDispatcher.include(req, resp);

                preparedStatement.close();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        writer.close();
    }
}
