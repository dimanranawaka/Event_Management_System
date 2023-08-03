package dao;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    public static boolean validateLogin(String mUserName,String mPassword){

        boolean status = false;

        try {

            Connection connection = DBConnection.getInstance().getConnection();

            // The Question mark(?) Specifies the line number of members table

            String sql = "SELECT * FROM members WHERE mUserName=? AND mPassword=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,mUserName);
            preparedStatement.setString(2,mPassword);

            ResultSet resultSet = preparedStatement.executeQuery();

            status = resultSet.next(); // Go to next Position

//            connection.close();



        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();

        }

        return status;

    }

}
