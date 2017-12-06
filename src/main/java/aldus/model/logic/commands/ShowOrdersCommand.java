package aldus.model.logic.commands;

import aldus.model.beans.Order;
import aldus.model.logic.ShowOrderLogic;
import aldus.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class ShowOrdersCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        String page = null;
        List<Order> list = ShowOrderLogic.getOrders();
        request.setAttribute("orders",list);
        page = ConfigurationManager.getProperty("path.page.orders");
        return page;
    }
}
