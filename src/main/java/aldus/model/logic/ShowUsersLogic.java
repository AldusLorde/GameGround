package aldus.model.logic;

import aldus.dataBaseActions.ConnectionGetter;
import aldus.dataBaseActions.DAO.UserDAO;
import aldus.model.beans.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ShowUsersLogic {
    public static List<User> getAllUsers() throws SQLException {
        Connection connection = ConnectionGetter.getConnection();
        UserDAO userDAO = new UserDAO(connection);
        List<User> list = userDAO.findAll();
        connection.close();
        return list;
    }

    public static User getUser(int id) throws SQLException {
        User user = null;
        Connection connection = ConnectionGetter.getConnection();
        UserDAO userDAO = new UserDAO(connection);
        user = userDAO.findById(id);
        connection.close();
        return user;
    }
}
