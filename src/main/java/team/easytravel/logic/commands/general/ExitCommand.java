package team.easytravel.logic.commands.general;

import static team.easytravel.logic.commands.CommandResult.Action.EXIT;

import team.easytravel.logic.commands.Command;
import team.easytravel.logic.commands.CommandResult;
import team.easytravel.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Quits the app.";
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Easy Travel as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, EXIT);
    }

}
