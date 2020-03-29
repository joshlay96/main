package team.easytravel.logic.parser.fixedexpense;

import static team.easytravel.logic.parser.CliSyntax.PREFIX_AMOUNT_OTHR;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import team.easytravel.commons.core.Messages;
import team.easytravel.logic.commands.fixedexpense.AddFixedExpenseCommand;
import team.easytravel.logic.parser.ArgumentMultimap;
import team.easytravel.logic.parser.ArgumentTokenizer;
import team.easytravel.logic.parser.ParserUtil;
import team.easytravel.logic.parser.exceptions.ParseException;
import team.easytravel.model.listmanagers.fixedexpense.Amount;
import team.easytravel.model.listmanagers.fixedexpense.Description;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpense;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpenseCategory;

/**
 * Parses input arguments and creates a new AddFixedExpenseCommand object
 */
public class AddOverseasFixedExpenseCommandParser extends AddFixedExpenseCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddFixedExpenseCommand
     * and returns an AddFixedExpenseCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddFixedExpenseCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_AMOUNT_OTHR, PREFIX_DESCRIPTION, PREFIX_CATEGORY);

        if (!arePrefixesPresent(argMultimap, PREFIX_AMOUNT_OTHR, PREFIX_DESCRIPTION, PREFIX_CATEGORY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddFixedExpenseCommand.MESSAGE_USAGE));
        }


        Amount amountOthr = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT_OTHR).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        FixedExpenseCategory fixedExpenseCategory =
                ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get());

        FixedExpense fixedExpense = new FixedExpense(amountOthr, description, fixedExpenseCategory);

        return new AddFixedExpenseCommand(fixedExpense, true);
    }



}
