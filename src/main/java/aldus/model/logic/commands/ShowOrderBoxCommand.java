package aldus.model.logic.commands;

import aldus.model.beans.Game;
import aldus.model.logic.ShowGameLogic;
import aldus.resource.ConfigurationManager;
import aldus.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ShowOrderBoxCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        String page = ConfigurationManager.getProperty("path.page.userBox");;
        HttpSession session = request.getSession();
        Object o = session.getAttribute("listGame");
        if(o!=null){
            List<Integer> listId = (List<Integer>) o;
            List<Game> list = new ArrayList<>();
            for(int i = 0; i<listId.size();i++){
                list.add(ShowGameLogic.getGame(listId.get(i)));
            }
            request.setAttribute("list",list);
        }
        else{
            request.setAttribute("Empty_Box", MessageManager.getProperty("message.emptylist"));
        }
        return page;
    }
}
