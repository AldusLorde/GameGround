package aldus.controller;

import aldus.dataBaseActions.ConnectionGetter;
import aldus.model.logic.ActionFactory;
import aldus.model.logic.commands.ActionCommand;
import aldus.resource.ConfigurationManager;
import aldus.resource.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Controller", value = "/controller")
public class Controller extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String page = null;
        ActionFactory factory = new ActionFactory();
        ActionCommand command = factory.defineCommand(request);
        page = command.execute(request);
        if(page!=null){
            request.getRequestDispatcher(page).forward(request,response);
        }
        else {
            page = ConfigurationManager.getProperty("path.page.index");
            request.setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            request.getRequestDispatcher(page).forward(request,response);
        }
    }
}
