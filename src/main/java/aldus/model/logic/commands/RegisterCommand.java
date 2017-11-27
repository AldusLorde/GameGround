package aldus.model.logic.commands;

import aldus.model.beans.User;
import aldus.model.logic.RegisterLogic;
import aldus.resource.ConfigurationManager;
import aldus.resource.MessageManager;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;

public class RegisterCommand implements ActionCommand {
    private static final String admin = "105321";
    private static final String manager = "10532112";
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        String page = null;
        String name = request.getParameter("name");
        if(RegisterLogic.checkName(name)){
            page = ConfigurationManager.getProperty("path.page.register");
            request.getSession().setAttribute("registerError", MessageManager.getProperty("message.registererror.login"));
            return page;
        }
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        if(RegisterLogic.checkEmail(email)){
            page = ConfigurationManager.getProperty("path.page.register");
            request.getSession().setAttribute("registerError", MessageManager.getProperty("message.registererror.email"));
            return page;
        }
        Date birth = Date.valueOf(request.getParameter("birthDay"));
        int role;
        String c = request.getParameter("code");
        if(c == null||!c.isEmpty()) role =3;
        else
        switch(c){
            case admin:
                role = 1;
                break;
            case manager:
                role = 2;
                break;
            default:
                role = 3;
                break;
        }
        User user = RegisterLogic.createUser(name,password,email,role,birth);
        request.getSession().setAttribute("user",user);
        request.getSession().removeAttribute("registerError");
        return page = ConfigurationManager.getProperty("path.page.main");
    }
}
