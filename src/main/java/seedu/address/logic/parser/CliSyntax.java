package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    // ---------- FixedExpense Prefixes -------------//
    public static final Prefix PREFIX_AMOUNT = new Prefix("amount/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("description/");
    public static final Prefix PREFIX_CATEGORY = new Prefix("category/");

}
