package aldus.model.logic.commands;

import aldus.model.beans.Game;
import aldus.model.logic.ShowGameLogic;
import aldus.resource.ConfigurationManager;
import aldus.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ShowGameCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        String id = request.getParameter("id");
        Game game = ShowGameLogic.getGame(Integer.parseInt(id));
        if(game == null){
            request.getSession().setAttribute("incorrectId", MessageManager.getProperty("message.incorrectgameid"));
            return ConfigurationManager.getProperty("path.page.games");
        }
        request.getSession().setAttribute("game",game);
        return ConfigurationManager.getProperty("path.page.gamePage");
    }
}
