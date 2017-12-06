package aldus.model.logic.commands;

import aldus.model.beans.User;
import aldus.model.logic.ShowUsersLogic;
import aldus.resource.ConfigurationManager;
import aldus.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ShowUserCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        String page = null;
        String id  = request.getParameter("id");
        User user = ShowUsersLogic.getUser(Integer.valueOf(id));
        if(user == null){
            request.setAttribute("IncorrectId", MessageManager.getProperty("message.wronguserid"));
            page = ConfigurationManager.getProperty("path.page.users");
        }
        else {
            request.setAttribute("userL",user);
            page = ConfigurationManager.getProperty("path.page.user");
        }
        return page;
    }
}
