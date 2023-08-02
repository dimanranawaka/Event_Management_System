package servlet;

import dao.LoginDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/memberLogin")

public class MemberLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();

        // Fetching user input username and password

        String mUserName = req.getParameter("mUserName");
        String mPassword = req.getParameter("mPassword");

        if (LoginDao.validateLogin(mUserName,mPassword)){

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("MemberEvent.html");

            requestDispatcher.forward(req,resp);

        }else {

            writer.println("<center><h1>!! Username or Password Invalid !!</h1></center>");

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("MemberLogin.html");

            requestDispatcher.include(req,resp);

        }

        writer.close();

    }
}
