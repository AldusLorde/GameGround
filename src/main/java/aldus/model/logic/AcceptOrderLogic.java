package aldus.model.logic;

import aldus.dataBaseActions.ConnectionGetter;
import aldus.dataBaseActions.DAO.OrderDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class AcceptOrderLogic {
    public static boolean acceptOrder(int id) throws SQLException {
        Connection connection = ConnectionGetter.getConnection();
        OrderDAO orderDAO = new OrderDAO(connection);
        if(orderDAO.findById(id)!= null) {
            orderDAO.accept(id);
            return true;
        } else {
            return false;
        }
    }
}
