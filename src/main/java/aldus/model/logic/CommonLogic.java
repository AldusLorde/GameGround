package aldus.model.logic;

import aldus.dataBaseActions.ConnectionGetter;
import aldus.dataBaseActions.DAO.UserDAO;
import aldus.model.beans.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CommonLogic {
    public static User getUser(String login, String password) throws SQLException {
        Connection connection = ConnectionGetter.getConnection();
        UserDAO userDAO = new UserDAO(connection);
        User user = userDAO.findByNameAndPassword(login,password);
        connection.close();
        return user;
    }
    public static void updateLastActivity(User user, Timestamp timestamp) throws SQLException {
        Connection connection = ConnectionGetter.getConnection();
        UserDAO userDAO = new UserDAO(connection);
        userDAO.updateLastActivity(user.getId(),timestamp);
        connection.close();
    }
}
