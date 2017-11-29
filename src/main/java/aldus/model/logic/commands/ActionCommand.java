package aldus.model.logic.commands;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public interface ActionCommand {
    String execute(HttpServletRequest request) throws SQLException;
}
