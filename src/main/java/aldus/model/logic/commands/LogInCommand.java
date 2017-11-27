package aldus.model.logic.commands;

import aldus.dataBaseActions.ConnectionGetter;
import aldus.dataBaseActions.DAO.UserDAO;
import aldus.model.beans.User;
import aldus.model.logic.CommonLogic;
import aldus.model.logic.LogInLogic;
import aldus.resource.ConfigurationManager;
import aldus.resource.MessageManager;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

public class LogInCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        String page = null;
        String login = request.getParameter("name");
        String password = request.getParameter("password");
        if(LogInLogic.checkLogin(login,password)){
            User user = CommonLogic.getUser(login,password);
            request.getSession().setAttribute("user",user);
            CommonLogic.updateLastActivity(user,new Timestamp(System.currentTimeMillis()));
            request.getSession().removeAttribute("errorLoginPassMessage");
            page = ConfigurationManager.getProperty("path.page.main");
        }
        else{
            request.getSession().setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
