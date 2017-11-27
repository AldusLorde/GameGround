package aldus.model.logic.commands;

import aldus.model.beans.User;
import aldus.model.logic.DeleteUserLogic;
import aldus.model.logic.ShowUsersLogic;
import aldus.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class DeleteUserCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        String page = null;
        String id = request.getParameter("id");
        DeleteUserLogic.DeleteUser(Integer.parseInt(id));
        page = ConfigurationManager.getProperty("path.page.users");
        List<User> list;
        list = ShowUsersLogic.getAllUsers();
        request.getSession().setAttribute("users",list);
        return page;
    }
}
