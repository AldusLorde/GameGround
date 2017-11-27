package aldus.model.logic.commands;

import aldus.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ToCreationCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        return ConfigurationManager.getProperty("path.page.register");
    }
}
