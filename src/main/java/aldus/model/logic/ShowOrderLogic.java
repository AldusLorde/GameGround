package aldus.model.logic;

import aldus.dataBaseActions.ConnectionGetter;
import aldus.dataBaseActions.DAO.OrderDAO;
import aldus.model.beans.Game;
import aldus.model.beans.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ShowOrderLogic {
    public static Order getOrder(int id) throws SQLException {
        Order order = null;
        Connection connection = ConnectionGetter.getConnection();
        OrderDAO orderDAO = new OrderDAO(connection);
        order = orderDAO.findById(id);
        return order;
    }

    public static List<Order> getOrders() throws SQLException {
        List<Order> list;
        Connection connection = ConnectionGetter.getConnection();
        OrderDAO orderDAO = new OrderDAO(connection);
        list = orderDAO.findAll();
        return list;
    }

}
