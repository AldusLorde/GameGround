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
    }
    ;

    ActionCommand command;

    public ActionCommand getCommand() {
        return command;
    }
}
