package aldus.model.logic;

import aldus.dataBaseActions.ConnectionGetter;
import aldus.dataBaseActions.DAO.UserDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteUserLogic {
    public static void DeleteUser(int id) throws SQLException {
        Connection connection = ConnectionGetter.getConnection();
        UserDAO userDAO = new UserDAO(connection);
        userDAO.deleteById(id);
        connection.close();
    }
}
