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

@WebServlet(urlPatterns = "transactionManager")
public class TransactionManagerServlet extends HttpServlet {
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

            String query = "SELECT * FROM paymentDetails";

            ResultSet resultSet = databaseOperationsDao.retrieveData(query);

            writer.println("<center><h1>Transaction Details</h1></center>");
            writer.println("<br>");
            writer.println("<div>");

            writer.println("<center>");
            writer.println("<table border=1 width=50% height=50%>");
            writer.println("<tr><th>Event Name</th><th>Event Number</th><th>Card Number</th><th>Event Date</th><th>cardCVV</th><th>cHoldersName</th>");

            while (resultSet.next()){

                String eName = resultSet.getString("eName");
                String eNum = resultSet.getString("eNum");
                String cardNum = resultSet.getString("cardNum");
                String eDate = resultSet.getString("eDate");
                String cardCVV = resultSet.getString("cardCVV");
                String cHoldersName = resultSet.getString("cHoldersName");

                writer.println("<tr><td>" +eName+"</td><td>"+eNum+"</td><td>"+cardNum+"</td><td>"+eDate+"</td><td>"+cardCVV+"</td><td>"+cHoldersName+"</td></tr>");


            }

            writer.println("</table>");
            writer.println("</center>");
            writer.println("</body>");
            writer.println("</html>");

        }catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();

        }finally {
            writer.close();
        }

    }
}
