package aldus.model.logic.commands;

import aldus.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return ConfigurationManager.getProperty("path.page.index");
    }
}
