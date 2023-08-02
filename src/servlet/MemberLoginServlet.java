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
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/memberLogin")

public class MemberLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        String userName = req.getParameter("mUserName");
        String userPassword = req.getParameter("mPassword");

        PrintWriter writer = resp.getWriter();

        try {

            DBConnection instance = DBConnection.getInstance();

            String sql = "SELECT mUserName,mPassword From members";

            PreparedStatement preparedStatement = instance.getConnection().prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            String uS = resultSet.getString(1);
            String uP = resultSet.getString(2);

            if (userName.equals(uS) && userPassword.equals(uP)){

                RequestDispatcher requestDispatcher = req.getRequestDispatcher("MemberEvent.html");
                requestDispatcher.forward(req,resp);

                preparedStatement.close();
                instance.getConnection().close();


            }else {

                writer.println("<center><h1>!! Username or Password Invalid !!</h1></center>");

                RequestDispatcher requestDispatcher = req.getRequestDispatcher("MemberLogin.html");

                requestDispatcher.include(req,resp);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
        writer.close();
    }
}
