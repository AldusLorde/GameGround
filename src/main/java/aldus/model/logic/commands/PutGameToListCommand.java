package aldus.model.logic.commands;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class PutGameToListCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        String id = request.getParameter("id");
        
    }
}
