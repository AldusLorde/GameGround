package aldus.dataBaseActions.DAO;

import aldus.model.beans.Developer;
import aldus.model.beans.Game;
import aldus.model.beans.Genre;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class GameDAO extends AbstractDAO<Integer, Game> {
    private final static String SQL_SELECT_ALL = "SELECT games.id,games.name,games.description,games.price,games.discount,games.year, genres.genre, developers.developer, games.image FROM games INNER JOIN developers ON developers.id = games.developerId INNER JOIN genres ON genres.id = games.genreId";
    private final static String SQL_SELECT_BY_NAME = SQL_SELECT_ALL + " WHERE name = ?";
    private final static String SQL_SELECT_BY_ID = SQL_SELECT_ALL + " WHERE id = ?";
    private final static String SQL_SELECT_BY_GENRE = SQL_SELECT_ALL + " WHERE genreId = ?";
    private final static String SQL_SELECT_BY_DEVELOPER = SQL_SELECT_ALL + " WHERE developerId = ?";
    private final static String SQL_SELECT_BY_YEAR = SQL_SELECT_ALL + " WHERE year = ?";
    private final static String SQL_UPDATE ="UPDATE games SET developerId = ?, genreId = ?, name = ?, year = ?, description = ?, price = ?, discount = ?, image = ? WHERE id = ?";
    private final static String SQL_DELETE = "DELETE FROM games WHERE id = ?";
    private final static String SQL_CREATE = "INSERT INTO games(developerID,genreId, name, year, description, price, discount, image) VALUES(?,?,?,?,?,?,?,?)";


    public GameDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Game> findAll() throws SQLException {
        List<Game> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
        list = getListByExecuting(preparedStatement);
        closeStatement(preparedStatement);
        return list;
    }

    @Override
    public Game findById(Integer id) throws SQLException {
        Game game = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())game = getGame(resultSet);
        closeResultSet(resultSet);
        closeStatement(preparedStatement);
        return game;
    }

    public Game findByName(String name) throws SQLException {
        Game game = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_NAME);
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) game = getGame(resultSet);
        closeStatement(preparedStatement);
        closeResultSet(resultSet);
        return game;
    }

    public List<Game> findByGenre(String genre) throws SQLException {
        List<Game> list = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_GENRE);
        preparedStatement.setString(1,genre);
        list = getListByExecuting(preparedStatement);
        closeStatement(preparedStatement);
        return  list;
    }

    public List<Game> findByYear(Integer year) throws SQLException {
        List<Game> list = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_YEAR);
        preparedStatement.setInt(1,year);
        list = getListByExecuting(preparedStatement);
        closeStatement(preparedStatement);
        return list;
    }



    public List<Game> findByDeveloper(String developer) throws SQLException {
        List<Game> list = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_DEVELOPER);
        preparedStatement.setString(1,developer);
        list = getListByExecuting(preparedStatement);
        closeStatement(preparedStatement);
        return list;
    }

    @Override
    public Game update(Integer id, Game entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
        Developer developer = entity.getDeveloper();
        Genre genre = entity.getGenre();
        int developerId;
        if(!checkDeveloper(developer)) developerId = createNewDeveloper(developer);
        else developerId = new DeveloperDAO(connection).findByDeveloper(developer.getDeveloper()).getId();
        int genreId;
        if(!checkGenre(genre)) genreId = createNewGenre(genre);
        else genreId = new GenreDAO(connection).findByGenre(genre.getGenre()).getId();
        preparedStatement.setInt(1,developerId);
        preparedStatement.setInt(2,genreId);
        preparedStatement.setString(3,entity.getName());
        preparedStatement.setInt(4,entity.getYear());
        preparedStatement.setString(5,entity.getDescription());
        preparedStatement.setDouble(6,entity.getPrice());
        preparedStatement.setInt(7,entity.getDiscount());
        preparedStatement.setString(8,entity.getImage());
        preparedStatement.setInt(9,id);
        preparedStatement.execute();
        entity.setId(id);
        return entity;
    }
//Do with duplicate/changeAbstractDao
    @Override
    public boolean deleteById(Integer id) throws SQLException {
        boolean c;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
        preparedStatement.setInt(1,id);
        c = preparedStatement.execute();
        closeStatement(preparedStatement);
        return c;
    }

    @Override
    public boolean deleteByEntity(Game entity) throws SQLException {
        return deleteById(entity.getId());
    }

    @Override
    public boolean create(Game entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE);
        Developer developer = entity.getDeveloper();
        Genre genre = entity.getGenre();
        int developerId;
        if(!checkDeveloper(developer)) developerId = createNewDeveloper(developer);
        else developerId = new DeveloperDAO(connection).findByDeveloper(developer.getDeveloper()).getId();
        int genreId;
        if(!checkGenre(genre)) genreId = createNewGenre(genre);
        else genreId = new GenreDAO(connection).findByGenre(genre.getGenre()).getId();
        preparedStatement.setInt(1,developerId);
        preparedStatement.setInt(2,genreId);
        preparedStatement.setString(3,entity.getName());
        preparedStatement.setInt(4,entity.getYear());
        preparedStatement.setString(5,entity.getDescription());
        preparedStatement.setDouble(6,entity.getPrice());
        preparedStatement.setInt(7,entity.getDiscount());
        preparedStatement.setString(8,entity.getImage());
        boolean c = preparedStatement.execute();
        closeStatement(preparedStatement);
        return c;
    }

    private Game getGame(ResultSet resultSet) throws SQLException {
        String dev = resultSet.getString("developer");
        String gen = resultSet.getString("genre");
        Genre genre = new GenreDAO(connection).findByGenre(gen);
        Developer developer = new DeveloperDAO(connection).findByDeveloper(dev);
        return new Game(resultSet.getInt("id"),developer
                ,genre,resultSet.getString("name")
                ,resultSet.getDate("year").toLocalDate().getYear()
                ,resultSet.getString("description"),resultSet.getDouble("price")
                ,resultSet.getInt("discount"),resultSet.getString("image"));
    }

    private List<Game> getListByExecuting(PreparedStatement preparedStatement) throws SQLException {
        List<Game> list = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            list.add(getGame(resultSet));
        }
        closeResultSet(resultSet);
        return list;
    }

    private boolean checkDeveloper(Developer developer) throws SQLException {
        return new  DeveloperDAO(connection).findByDeveloper(developer.getDeveloper()) != null;
    }
//Create Abstract Bean
    private int createNewDeveloper(Developer developer) throws SQLException {
        int i = 0;
        DeveloperDAO developerDAO =  new DeveloperDAO(connection);
        developerDAO.create(developer);
        i = developerDAO.findByDeveloper(developer.getDeveloper()).getId();
        return i;
    }

    private boolean checkGenre(Genre genre) throws SQLException {
        return new GenreDAO(connection).findByGenre(genre.getGenre())!=null;
    }

    private  int createNewGenre(Genre genre) throws SQLException {
        int i = 0;
        GenreDAO genreDAO = new GenreDAO(connection);
        genreDAO.create(genre);
        i = genreDAO.findByGenre(genre.getGenre()).getId();
        return i;
    }

}
