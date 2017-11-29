package aldus.model.logic.commands;

import aldus.model.beans.Game;
import aldus.model.logic.ShowGameLogic;
import aldus.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class ShowGamesCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        List<Game> list = ShowGameLogic.getAllGames();
        request.setAttribute("gamesList",list);
        return ConfigurationManager.getProperty("path.page.games");
    }
}
