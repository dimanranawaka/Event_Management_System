package dao;

import dao.DatabaseOperationsDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    public static boolean validateLogin(String mUserName, String mPassword) {

        boolean status = false;

        try {
            DatabaseOperationsDao dbOperations = new DatabaseOperationsDao();

            String query = "SELECT * FROM members WHERE mUserName=? AND mPassword=?";

            // Use the retrieveData method from DatabaseOperations
            ResultSet resultSet = dbOperations.retrieveData(query, mUserName, mPassword);

            status = resultSet.next(); // Go to next Position

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return status;
    }
}
