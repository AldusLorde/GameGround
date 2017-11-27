package aldus.model.logic;

import aldus.dataBaseActions.ConnectionGetter;
import aldus.dataBaseActions.DAO.GameDAO;
import aldus.model.beans.Game;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateGameLogic {
    public static Game createGame(Game game) throws SQLException {
        Connection connection = ConnectionGetter.getConnection();
        GameDAO dao = new GameDAO(connection);
        dao.create(game);
        Game c = dao.findByName(game.getName());
        connection.close();
        return c;
    }

    public static boolean checkGame(Game game) throws SQLException {
        Connection connection = ConnectionGetter.getConnection();
        GameDAO gameDAO = new GameDAO(connection);
        Game a = gameDAO.findByName(game.getName());
        connection.close();
        return a!=null;
    }

}
