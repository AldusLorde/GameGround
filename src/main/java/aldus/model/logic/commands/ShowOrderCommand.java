package aldus.model.logic.commands;

import aldus.model.beans.Order;
import aldus.model.logic.ShowOrderLogic;
import aldus.resource.ConfigurationManager;
import aldus.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ShowOrderCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        String page = null;
        int id = Integer.parseInt(request.getParameter("id"));
        Order order = ShowOrderLogic.getOrder(id);
        if (order == null) {
            page = ConfigurationManager.getProperty("path.page.user");
            request.setAttribute("Order_error", MessageManager.getProperty("message.incorrectorder"));
        } else {
            page = ConfigurationManager.getProperty("path.page.orderPage");
            request.setAttribute("order",order);
        }
        return page;
    }
}
