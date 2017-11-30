package aldus.model.logic.commands;

import aldus.resource.ConfigurationManager;
import aldus.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

public class PopGameFromTheBoxCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        String id = request.getParameter("id");
        HttpSession session = request.getSession();
        Object o = session.getAttribute("listGame");
        if(o!=null){
            List<Integer> set = (List<Integer>)o;
            set.remove(Integer.valueOf(id));
        }
        else {
            request.setAttribute("Empty_List", MessageManager.getProperty("message.emptylist"));
        }
        return ConfigurationManager.getProperty("path.page.userBox");
    }
}
