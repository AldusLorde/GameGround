package aldus.dataBaseActions.DAO;

import aldus.model.beans.Genre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO extends  AbstractDAO<Integer, Genre> {

    private static String SQL_SELECT_ALL = "SELECT * FROM genres";
    private static String SQL_SELECT_BY_ID = SQL_SELECT_ALL + " WHERE id = ?";
    private static String SQL_SELECT_BY_GENRE = SQL_SELECT_ALL + " WHERE genre = ?";
    private static String SQL_UPDATE = "UPDATE genres SET genre = ? WHERE id = ?";
    private static String SQL_DELETE = "DELETE FROM genres WHERE id = ?";
    private static String SQL_CREATE = "INSERT INTO genres(genre) VALUES(?)";

    public GenreDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Genre> findAll() throws SQLException {
        List<Genre> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            list.add(new Genre(resultSet.getInt("id"),resultSet.getString("genre")));
        }
        closeResultSet(resultSet);
        closeStatement(preparedStatement);
        return list;
    }

    @Override
    public Genre findById(Integer id) throws SQLException {
        Genre genre = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) genre = getGenre(resultSet);
        closeStatement(preparedStatement);
        closeResultSet(resultSet);
        return genre;
    }

    public Genre findByGenre(String genre) throws SQLException {
        Genre genreR = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_GENRE);
        preparedStatement.setString(1,genre);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) genreR = getGenre(resultSet);
        closeStatement(preparedStatement);
        closeResultSet(resultSet);
        return genreR;
    }

    @Override
    public Genre update(Integer id, Genre entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
        preparedStatement.setString(1,entity.getGenre());
        preparedStatement.setInt(2,id);
        preparedStatement.execute();
        closeStatement(preparedStatement);
        return findById(id);
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        boolean b = false;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
        preparedStatement.setInt(1,id);
        b = preparedStatement.execute();
        closeStatement(preparedStatement);
        return b;
    }

    @Override
    public boolean deleteByEntity(Genre entity) throws SQLException {
        return deleteById(entity.getId());
    }

    @Override
    public boolean create(Genre entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE);
        preparedStatement.setString(1,entity.getGenre());
        boolean b = preparedStatement.execute();
        closeStatement(preparedStatement);
        return b;
    }

    private Genre getGenre(ResultSet resultSet) throws SQLException {
        Genre genre = new Genre(resultSet.getInt("id"),resultSet.getString("genre"));
        closeResultSet(resultSet);
        return genre;
    }
}
