package aldus.model.logic.commands;

import aldus.model.logic.AcceptOrderLogic;
import aldus.resource.ConfigurationManager;
import aldus.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class AcceptOrderCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean b =  AcceptOrderLogic.acceptOrder(id);
        if(b) {
            return new ShowOrderCommand().execute(request);
        }
        else {
            request.setAttribute("Order_error", MessageManager.getProperty("message.incorrectorder"));
            return ConfigurationManager.getProperty("path.page.orders");
        }
    }
}
