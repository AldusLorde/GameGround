package aldus.model.logic.commands;

import javax.servlet.http.HttpServletRequest;

public class
EmptyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
