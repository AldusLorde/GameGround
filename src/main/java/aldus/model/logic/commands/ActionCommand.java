package aldus.model.logic.commands;

import aldus.controller.SessionRequestContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public interface ActionCommand {
    String execute(HttpServletRequest request) throws SQLException;
}
