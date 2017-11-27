package aldus.model.logic;

import aldus.dataBaseActions.ConnectionGetter;
import aldus.dataBaseActions.DAO.UserDAO;
import aldus.model.beans.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

public class RegisterLogic {
    public static User createUser(String name, String password, String email, int role, Date birth) throws SQLException {
        User user = new User(0,name,password,role,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),email,birth);
        Connection connection = ConnectionGetter.getConnection();
        UserDAO userDAO = new UserDAO(connection);
        boolean b = userDAO.create(user);
        user.setId(userDAO.findIdByName(name));
        connection.close();
        return user;
    }
    public static boolean checkName(String name) throws SQLException {
        Connection connection = ConnectionGetter.getConnection();
        UserDAO userDAO = new UserDAO(connection);
        boolean b = userDAO.findUserByName(name)!=null;
        connection.close();
        return b;
    }
    public static boolean checkEmail(String email) throws SQLException {
        Connection connection = ConnectionGetter.getConnection();
        UserDAO userDAO = new UserDAO(connection);
        boolean b = userDAO.findUserByEmail(email)!=null;
        connection.close();
        return b;
    }
}
