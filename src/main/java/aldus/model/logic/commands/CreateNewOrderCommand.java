package aldus.model.logic.commands;

import aldus.model.beans.Game;
import aldus.model.beans.Order;
import aldus.model.beans.User;
import aldus.model.logic.ShowGameLogic;
import aldus.model.logic.ShowUsersLogic;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreateNewOrderCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        String page = null;
        Object o = request.getSession().getAttribute("listGame");
        if (o!=null){
            List<Integer> list = (List<Integer>)o;
            request.getSession().removeAttribute("listGame");
            List<Game> games = new ArrayList<>();
            for(int i = 0;i<list.size();i++){
                games.add(ShowGameLogic.getGame(list.get(i)));
            }
            Game[] array = games.toArray(new Game[1]);
            User user = (User) request.getSession().getAttribute("User");
            Order order = new Order(user,games,)
        }
    }
}
