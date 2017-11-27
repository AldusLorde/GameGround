package aldus.model.logic;

import aldus.dataBaseActions.ConnectionGetter;
import aldus.dataBaseActions.DAO.UserDAO;
import aldus.model.beans.User;

import java.sql.Connection;
import java.sql.SQLException;

public class LogInLogic {
    public static boolean checkLogin(String name, String password) throws SQLException {
        Connection connection = ConnectionGetter.getConnection();
        User user = new UserDAO(connection).findByNameAndPassword(name,password);
        connection.close();
        return user!=null;
    }
}
