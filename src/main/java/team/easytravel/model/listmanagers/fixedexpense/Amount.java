package team.easytravel.model.listmanagers.fixedexpense;

import static java.util.Objects.requireNonNull;

import team.easytravel.commons.util.AppUtil;

/**
 * Represents a FixedExpense's amount in the FixedExpenseManager.
 * Guarantees: immutable; is valid as declared in {@link #isValidAmount(String)}
 */
public class Amount {
    public static final String MESSAGE_CONSTRAINTS = "Amount must be a valid number with at most 2 decimal places and "
            + "less than 10,000,000,000 both before and after currency conversion.";

    // Allows 10 digits, and at most 2 decimal places.
    public static final String VALIDATION_REGEX = "^([\\d]{1,10})(\\.[\\d]{1,2})?$";
    public static final String CURRENCY_SGD = "sgd";
    public static final String CURRENCY_OTHER = "other";
    public final String value;

    public Amount(String amount) {
        requireNonNull(amount);
        AppUtil.checkArgument(isValidAmount(amount), MESSAGE_CONSTRAINTS);
        value = String.format("%.2f", Double.parseDouble(amount));
    }

    /**
     * Returns true if a given string is a valid amount.
     */
    public static boolean isValidAmount(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.format("%.2f", Double.parseDouble(value));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Amount // instanceof handles nulls
                && value.equals(((Amount) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
