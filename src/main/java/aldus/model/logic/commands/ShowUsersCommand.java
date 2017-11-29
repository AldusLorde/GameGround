package aldus.model.logic.commands;

import aldus.model.beans.User;
import aldus.model.logic.ShowUsersLogic;
import aldus.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class ShowUsersCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        String page = null;
        List<User> list = ShowUsersLogic.getAllUsers();
        request.setAttribute("users",list);
        page = ConfigurationManager.getProperty("path.page.users");
        return page;
    }
}
