package aldus.model.logic;

import aldus.dataBaseActions.ConnectionGetter;
import aldus.dataBaseActions.DAO.GameDAO;
import aldus.model.beans.Game;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowGameLogic {
    public static List<Game> getAllGames() throws SQLException {
        List<Game> list = new ArrayList<>();
        Connection connection = ConnectionGetter.getConnection();
        GameDAO gameDAO = new GameDAO(connection);
        list = gameDAO.findAll();
        connection.close();
        return list;
    }

    public static Game getGame(int id) throws SQLException {
        Game game = null;
        Connection connection = ConnectionGetter.getConnection();
        GameDAO gameDAO = new GameDAO(connection);
        game = gameDAO.findById(id);
        connection.close();
        return game;
    }
}
