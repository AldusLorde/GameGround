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

    private final static String SQL_SELECT_ALL = "SELECT orders.idOrders, users.name, orders.price, orders.accepted, orders.time  FROM orders INNER  JOIN users ON userId=users.id ";
    private final static String SQL_SELECT_BY_ID = SQL_SELECT_ALL + " WHERE idOrders = ?";
    private final static String SQL_SELECT_ID = "SELECT idOrders FROM orders WHERE userId = ? AND time = ?";
    private final static String SQL_SELECT_BY_ACCEPTION = SQL_SELECT_ALL + " WHERE accepted = ?";
    private final static String SQL_UPDATE = "UPDATE orders SET accepted = ? WHERE idOrders = ?";
    private final static String SQL_DELETE = "DELETE FROM orders WHERE idOrders = ?";
    private final static String SQL_CREATE = "INSERT INTO orders(userId,price, time) VALUES(?,?,?)";
    private final static String SQL_SUB_CREATE = "INSERT INTO order_game(idOrder, idGame) VALUES(?,?)";
    private final static String SQL_DUB_SELECT = "SELECT * FROM order_game WHERE idOrder = ?";

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
        preparedStatement.setDouble(2,entity.getPrice());
        preparedStatement.setTimestamp(3,entity.getTime());
        boolean c = preparedStatement.execute();
        closeStatement(preparedStatement);
        PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_SELECT_ID);
        preparedStatement1.setInt(1,new UserDAO(connection).findIdByName(entity.getUser().getName()));
        preparedStatement.setTimestamp(2,entity.getTime());
        ResultSet resultSet = preparedStatement1.executeQuery();
        int id = -1;
        if (resultSet.next()) id = resultSet.getInt("idOrders");
        else throw new SQLException();
        PreparedStatement preparedStatement2 = connection.prepareStatement(SQL_SUB_CREATE);
        preparedStatement2.setInt(1,id);
        Game[] list = entity.getGame();
        for(int i = 0;i<list.length;i++){
            preparedStatement2.setInt(2,list[i].getId());
            preparedStatement.execute();
        }
        return c;
    }

    private Order getOrder(ResultSet set) throws SQLException {
        User user = new UserDAO(connection).findById(set.getInt("userId"));
        if (user==null) throw new SQLException("User wasn`t found");
        Game game = new GameDAO(connection).findById(set.getInt("gameId"));
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DUB_SELECT);
        preparedStatement.setInt(1,set.getInt("idOrders"));
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Integer> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(resultSet.getInt("idGame"));
        }
        closeStatement(preparedStatement);
        closeResultSet(resultSet);
        Game[] games = new Game[list.size()];
        GameDAO gameDAO = new GameDAO(connection);
        for(int i = 0; i<list.size();i++){
            games[i]=gameDAO.findById(list.get(i));
        }
        Order order = new Order(user,games,set.getInt("price"),set.getBoolean("accepted"),set.getTimestamp("time"));
        return order;
    }
}
