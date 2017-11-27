package aldus.model.logic.commands;

import aldus.controller.SessionRequestContent;
import aldus.resource.ConfigurationManager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return ConfigurationManager.getProperty("path.page.index");
    }
}
