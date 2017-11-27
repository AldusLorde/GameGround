package aldus.dataBaseActions.DAO;

import aldus.model.beans.Developer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDAO extends AbstractDAO<Integer, Developer> {

    private static String SQL_SELECT_ALL = "SELECT * FROM developers";
    private static String SQL_SELECT_BY_ID = SQL_SELECT_ALL + " WHERE id = ?";
    private static String SQL_SELECT_BY_DEVELOPER = SQL_SELECT_ALL + " WHERE developer = ?";
    private static String SQL_UPDATE = "UPDATE developers SET developer = ? WHERE id = ?";
    private static String SQL_DELETE = "DELETE FROM developers WHERE id = ?";
    private static String SQL_CREATE = "INSERT INTO developers(developer) VALUES(?)";

    public DeveloperDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Developer> findAll() throws SQLException {
        List<Developer> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            list.add(getDeveloper(resultSet));
        }
        closeResultSet(resultSet);
        closeStatement(preparedStatement);
        return list;
    }

    @Override
    public Developer findById(Integer id) throws SQLException {
        Developer developer = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) developer = getDeveloper(resultSet);
        return developer;
    }

    public Developer findByDeveloper(String name) throws SQLException {
        Developer developer = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_DEVELOPER);
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) developer = getDeveloper(resultSet);
        closeStatement(preparedStatement);
        closeResultSet(resultSet);
        return developer;
    }

    @Override
    public Developer update(Integer id, Developer entity) throws SQLException {
        Developer developer = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
        preparedStatement.setString(1,entity.getDeveloper());
        preparedStatement.setInt(2,id);
        preparedStatement.execute();
        closeStatement(preparedStatement);
        entity.setId(id);
        return entity;
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        boolean b;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
        preparedStatement.setInt(1,id);
        b = preparedStatement.execute();
        closeStatement(preparedStatement);
        return b;
    }

    @Override
    public boolean deleteByEntity(Developer entity) throws SQLException {
        return deleteById(entity.getId());
    }

    @Override
    public boolean create(Developer entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE);
        preparedStatement.setString(1,entity.getDeveloper());
        boolean b = preparedStatement.execute();
        closeStatement(preparedStatement);
        return b;

    }

    private Developer getDeveloper(ResultSet resultSet) throws SQLException {
        return new Developer(resultSet.getInt("id"),resultSet.getString("developer"));
    }

}
