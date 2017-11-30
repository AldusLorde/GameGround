package aldus.model.logic.commands;

import aldus.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PutGameToBoxCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        String id = request.getParameter("id");
        HttpSession session = request.getSession();
        Object o = session.getAttribute("listGame");
        if(o!=null){
            List<Integer> set = (List<Integer>)o;
            if(!set.contains(Integer.valueOf(id))) set.add(Integer.valueOf(id));
        }
        else{
            List<Integer> set = new ArrayList<>();
            set.add(Integer.valueOf(id));
            session.setAttribute("listGame",set);
        }
        return ConfigurationManager.getProperty("path.page.games");
    }
}
