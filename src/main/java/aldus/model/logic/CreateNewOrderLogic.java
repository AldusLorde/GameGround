package aldus.model.logic;

import aldus.dataBaseActions.ConnectionGetter;
import aldus.dataBaseActions.DAO.OrderDAO;
import aldus.model.beans.Game;
import aldus.model.beans.Order;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateNewOrderLogic {
    public static double calculatePrice(Game[] games){
        double p = 0;
        for(Game game:games) {
            p+=(game.getPrice() - game.getPrice()*game.getDiscount()/100.0);
        }
        return p;
    }

    public static void createOrder(Order order) throws SQLException {
        Connection connection = ConnectionGetter.getConnection();
        OrderDAO orderDAO = new OrderDAO(connection);
        orderDAO.create(order);
    }

}
