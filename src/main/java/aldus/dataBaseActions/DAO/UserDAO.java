package aldus.dataBaseActions.DAO;

import aldus.model.beans.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO<Integer,User> {
    private final String SQL_SELECT_ALL = "SELECT * FROM users";
    private final String SQL_SELECT_USER_BY_PASSWORD_AND_NAME = "SELECT * FROM users Where password = ? AND name = ?";
    private final String SQL_SELECT_USER_BY_ID = "SELECT * FROM users Where id = ?";
    private final String SQL_UPDATE_BY_ID = "UPDATE users SET roleId = ?, name = ?, password = ?, email = ?, birthDay = ? WHERE id = ?";
    private final String SQL_DELETE_BY_ID = "DELETE FROM users WHERE id = ?";
    private final String SQL_CREATE_USER = "INSERT INTO users (roleId,name,password,creationTime,lastActivity,email,birthDay) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String SQL_UPDATE_ACTIVITY_TIME = "UPDATE users SET lastActivity = ? WHERE id =?";
    private final String SQL_SELECT_ID_BY_NAME = "SELECT id FROM users Where name = ?";
    private final String SQL_SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private final String SQL_SELECT_USER_BY_NAME = "SELECT *  FROM users WHERE name = ?";

    public UserDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            list.add(getUser(resultSet));
        }
        closeStatement(preparedStatement);
        closeResultSet(resultSet);
        return list;
    }

    @Override
    public User findById(Integer id) throws SQLException {
        User user = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            user = getUser(resultSet);
        }
        closeStatement(preparedStatement);
        closeResultSet(resultSet);
        return user;
    }

    public User findByNameAndPassword(String name, String password) throws SQLException {
        User user =null;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_PASSWORD_AND_NAME);
        preparedStatement.setString(1,password);
        preparedStatement.setString(2,name);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.first()){
            user = getUser(resultSet);
        }
        closeStatement(preparedStatement);
        closeResultSet(resultSet);
        return user;
    }
    @Override
    public User update(Integer id, User entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BY_ID);
        preparedStatement.setInt(1,entity.getRole());
        preparedStatement.setString(2,entity.getName());
        preparedStatement.setString(3,entity.getPassword());
        preparedStatement.setString(4,entity.getEmail());
        preparedStatement.setDate(5,entity.getBirthDay());
        preparedStatement.setInt(6,id);
        preparedStatement.executeUpdate();
        closeStatement(preparedStatement);
        return findById(id);
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        PreparedStatement preparedStatement =connection.prepareStatement(SQL_DELETE_BY_ID);
        preparedStatement.setInt(1,id);
        boolean b = preparedStatement.execute();
        closeStatement(preparedStatement);
        return b;
    }

    @Override
    public boolean deleteByEntity(User entity) throws SQLException {
        return deleteById(entity.getId());
    }
    @Override
    public boolean create(User entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_USER);
        preparedStatement.setInt(1,entity.getRole());
        preparedStatement.setString(2,entity.getName());
        preparedStatement.setString(3,entity.getPassword());
        preparedStatement.setTimestamp(4,entity.getCreationTime());
        preparedStatement.setTimestamp(5,entity.getLastActivity());
        preparedStatement.setString(6,entity.getEmail());
        preparedStatement.setDate(7,entity.getBirthDay());
        boolean b = preparedStatement.execute();
        closeStatement(preparedStatement);
        return b;
    }

    public void updateLastActivity(int id, Timestamp date) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ACTIVITY_TIME);
        preparedStatement.setTimestamp(1,date);
        preparedStatement.setInt(2,id);
        preparedStatement.execute();
        closeStatement(preparedStatement);
    }
    public int findIdByName(String name) throws SQLException {
        int i = -1;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ID_BY_NAME);
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.first()){
            i = resultSet.getInt("id");
        }
        closeResultSet(resultSet);
        closeStatement(preparedStatement);
        return i;
    }
    public User findUserByEmail(String email) throws SQLException {
        User user = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_EMAIL);
        preparedStatement.setString(1,email);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.first()){
            user = getUser(resultSet);
        }
        return user;
    }
    public User findUserByName(String name) throws SQLException {
        User user = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_NAME);
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.first()) user = getUser(resultSet);
        return user;

    }
    private User getUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int roleId = resultSet.getInt("roleId");
        String name = resultSet.getString("name");
        String password = resultSet.getString("password");
        Timestamp creationTime = resultSet.getTimestamp("creationTime");
        Timestamp lastActivity = resultSet.getTimestamp("lastActivity");
        String email = resultSet.getString("email");
        Date birth = resultSet.getDate("birthDay");
        return new User(id,name,password,roleId,creationTime,lastActivity,email,birth);
    }
}