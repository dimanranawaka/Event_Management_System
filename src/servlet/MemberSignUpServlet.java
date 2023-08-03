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

            try{

                DBConnection instance = DBConnection.getInstance();

                String query = "INSERT INTO members(mUserName, mPassword, mName) VALUES(?,?,?)";

                PreparedStatement preparedStatement = instance.getConnection().prepareStatement(query);

                preparedStatement.setString(1, mUserName);
                preparedStatement.setString(2, mPassword);
                preparedStatement.setString(3, mName);

                preparedStatement.executeUpdate();

                RequestDispatcher requestDispatcher = req.getRequestDispatcher("MemberLogin.html");
                requestDispatcher.forward(req, resp);


//                writer.println("<center><h1>!! Signup Successful!!</h1></center>");
//                writer.println("<center><a href='MemberLogin.html'>Login</a></center>");

                preparedStatement.close();
//                instance.getConnection().close();
//                writer.close();


            }catch(Exception e){

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
