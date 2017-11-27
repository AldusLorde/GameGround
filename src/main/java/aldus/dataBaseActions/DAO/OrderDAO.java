package aldus.dataBaseActions.DAO;

import aldus.model.beans.Game;
import aldus.model.beans.Order;
import aldus.model.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends AbstractDAO<Integer, Order> {
    private static String SQL_SELECT_ALL = "SELECT orders.idOrders, users.name, games.name, orders.price, orders.accepted  FROM orders INNER JOIN games ON gameId = games.id INNER  JOIN users ON userId=users.id ";
    private static String SQL_SELECT_BY_ID = SQL_SELECT_ALL + " WHERE idOrders = ?";
    private static String SQL_SELECT_BY_ACCEPTION = SQL_SELECT_ALL + " WHERE accepted = ?";
    private static String SQL_UPDATE = "UPDATE orders SET accepted = ? WHERE idOrders = ?";
    private static String SQL_DELETE = "DELETE FROM orders WHERE idOrders = ?";
    private static String SQL_CREATE = "INSERT INTO orders(userId,gameId,price,accepted) VALUES(?,?,?,?)";

    public OrderDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Order> findAll() throws SQLException {
        List<Order> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            list.add(getOrder(resultSet));
        }
        closeResultSet(resultSet);
        closeStatement(preparedStatement);
        return list;
    }

    @Override
    public Order findById(Integer id) throws SQLException {
        Order order = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) order = getOrder(resultSet);
        closeStatement(preparedStatement);
        closeResultSet(resultSet);
        return order;
    }

    public List<Order> findByAcception(boolean b) throws SQLException {
        List<Order> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ACCEPTION);
        preparedStatement.setBoolean(1,b);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            list.add(getOrder(resultSet));
        }
        preparedStatement.close();
        resultSet.close();
        return list;
    }

    @Override
    public Order update(Integer id, Order entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
        preparedStatement.setBoolean(1,entity.isAccepted());
        preparedStatement.setInt(2,id);
        entity.setIdOrder(id);
        closeStatement(preparedStatement);
        return entity;
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        PreparedStatement preparedStatement =connection.prepareStatement(SQL_DELETE);
        preparedStatement.setInt(1,id);
        boolean b = preparedStatement.execute();
        preparedStatement.close();
        return b;
    }

    @Override
    public boolean deleteByEntity(Order entity) throws SQLException {
        return deleteById(entity.getIdOrder());
    }

    @Override
    public boolean create(Order entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE);
        preparedStatement.setInt(1,entity.getUser().getId());
        preparedStatement.setInt(2,entity.getGame().getId());
        preparedStatement.setInt(3,entity.getPrice());
        boolean c = preparedStatement.execute();
        closeStatement(preparedStatement);
        return c;
    }

    private Order getOrder(ResultSet set) throws SQLException {
        User user = new UserDAO(connection).findById(set.getInt("userId"));
        if (user==null) throw new SQLException("User wasn`t found");
        Game game = new GameDAO(connection).findById(set.getInt("gameId"));
        if (game == null) throw new SQLException("Game wasn`t found");
        Order order = new Order(set.getInt("idOrders"),user,game,set.getInt("price"),set.getBoolean("accepted"));
        return order;
    }
}
