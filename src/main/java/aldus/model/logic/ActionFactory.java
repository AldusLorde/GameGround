package aldus.model.logic;

import aldus.model.logic.commands.ActionCommand;
import aldus.model.logic.commands.CommandEnum;
import aldus.model.logic.commands.EmptyCommand;
import aldus.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request){
        ActionCommand command = new EmptyCommand();
        String commandStr = request.getParameter("command");
        if(commandStr == null || commandStr.isEmpty()){
            return command;
        }
        else{
            try{
            CommandEnum commandEnum = CommandEnum.valueOf(commandStr.toUpperCase());
            command = commandEnum.getCommand();
            }
            catch (IllegalArgumentException e){
                request.setAttribute("wrongAction", command + MessageManager.getProperty("message.wrongaction"));
            }

        }
        return command;
    }
}
