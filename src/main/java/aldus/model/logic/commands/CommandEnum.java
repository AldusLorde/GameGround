package aldus.model.logic.commands;

public enum CommandEnum {
    LOGIN{
        {
            command = new LogInCommand();
        }
    },
    LOGOUT{
        {
            command = new LogOutCommand();
        }
    },
    REGISTER{
        {
            command = new RegisterCommand();
        }
    },
    TOCREATION{
        {
            command = new ToCreationCommand();
        }
    },
    SHOWUSERS{
        {
            command = new ShowUsersCommand();
        }
    },
    DELETEUSER{
        {
            command = new DeleteUserCommand();
        }
    },
    CREATEGAME{
        {
            command = new CreateGameCommand();
        }
    },
    SHOWGAME{
        {
            command = new ShowGameCommand();
        }
    },
    SHOWGAMES{
        {
            command = new ShowGamesCommand();
        }
    },
    SHOWUSER{
        {
            command = new ShowUserCommand();
        }
    }
    ;

    ActionCommand command;

    public ActionCommand getCommand() {
        return command;
    }
}
