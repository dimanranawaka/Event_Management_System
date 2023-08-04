package servlet;

import dao.DatabaseOperationsDao;
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

@WebServlet(urlPatterns = "/memberSignUp")
public class MemberSignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        // Fetching data from MemberSignUp page
        String mUserName = req.getParameter("mUserName");
        String mPassword = req.getParameter("mPassword");
        String cmPassword = req.getParameter("cmPassword");
        String mName = req.getParameter("name");

        if (mPassword.equals(cmPassword)) {

            try {
                DatabaseOperationsDao dbOperations = new DatabaseOperationsDao();

                String insertQuery = "INSERT INTO members(mUserName, mPassword, mName) VALUES(?,?,?)";

                // Use the insertData method from DatabaseOperations
                boolean insertionResult = dbOperations.insertData(insertQuery, mUserName, mPassword, mName);

                if (insertionResult) {
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("MemberLogin.html");
                    requestDispatcher.forward(req, resp);
                } else {
                    writer.println("<center><h1>!! Signup Failed !!</h1></center>");
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                writer.println("<center><h1>!! An error occurred !!</h1></center>");
            }

        } else {
            writer.println("<center><h1>!! Please Enter Password and Confirm Password</h1></center>");

            // If passwords don't match, forward back to MemberSignUp.html
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("MemberSignUp.html");
            requestDispatcher.include(req, resp);
        }
        writer.close();
    }
}
