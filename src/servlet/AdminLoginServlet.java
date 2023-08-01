package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/adminLogin")

public class AdminLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();

        String adminUserName = req.getParameter("AdminUserName"); // For Fetch AdminUserName
        String adminPassword = req.getParameter("AdminPassword"); // For Fetch AdminPassword

        resp.setContentType("text/html");

        // Pre-Defined Admin Usernames and Passwords

        String adminName1 = "Admin1";
        String adminPassword1 = "1234";

        String adminName2 = "Admin2";
        String adminPassword2 = "1234";

        String adminName3 = "Admin3";
        String adminPassword3 = "1234";

        String adminName4 = "Admin4";
        String adminPassword4 = "1234";

        // if-else ladder to verify login

        if(adminUserName.equals(adminName1) && adminPassword.equals(adminPassword1)){

            takeRequestDispatcher(req,resp);

        } else if (adminUserName.equals(adminName2) && adminPassword.equals(adminPassword2)) {

            takeRequestDispatcher(req,resp);

        } else if (adminUserName.equals(adminName3) && adminPassword.equals(adminPassword3)) {

            takeRequestDispatcher(req,resp);

        } else if (adminUserName.equals(adminName4) && adminPassword.equals(adminPassword4)) {

            takeRequestDispatcher(req,resp);

        }else{

            writer.println("<center><h1>!! Please Enter Valid Username & Password !!</h1></center>");
            RequestDispatcher rd = req.getRequestDispatcher("Admin-login.html");
            rd.include(req,resp); // This will make sure if the adminUserName and adminPassword are wrong client will stay the same page

        }

    }
    public void takeRequestDispatcher(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {

        // For reduce boiler-plate codes
        RequestDispatcher rd = req.getRequestDispatcher("AdminEvent.html");
        rd.forward(req,resp);

    }
}
