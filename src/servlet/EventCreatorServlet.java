package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/EventCreator")

public class EventCreatorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Fetching data from user inputs

        String eNumber = req.getParameter("eNumber");
        String eName = req.getParameter("eName");
        String cName = req.getParameter("cName");
        String cNumber = req.getParameter("cNumber");
        String eFee = req.getParameter("eFee");
        String eVenue = req.getParameter("eVenue");
        String eDate = req.getParameter("eDate");

    }
}
