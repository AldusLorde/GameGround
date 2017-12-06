package aldus.model.logic.commands;

import aldus.model.beans.Developer;
import aldus.model.beans.Game;
import aldus.model.beans.Genre;
import aldus.model.logic.CreateGameLogic;
import aldus.resource.ConfigurationManager;
import aldus.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CreateGameCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        String page = null;
        String name = request.getParameter("name");
        Developer developer = new Developer(request.getParameter("developer"));
        Genre genre = new Genre(request.getParameter("genre"));
        String image = request.getParameter("image");
        int year = Integer.parseInt(request.getParameter("year"));
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));
        int discount = Integer.parseInt(request.getParameter("discount"));
        Game game = new Game(developer,genre,name,year,description,price,discount,image);
        if(!CreateGameLogic.checkGame(game)) game = CreateGameLogic.createGame(game);
        else page = null;
        if(game == null){
            request.setAttribute("creationError", MessageManager.getProperty("message.creationerror"));
            page = ConfigurationManager.getProperty("path.page.createGame");
        }
        else
        {
            page = ConfigurationManager.getProperty("path.page.gamePage");
            request.setAttribute("game", game);
        }
        return page;
    }
}
