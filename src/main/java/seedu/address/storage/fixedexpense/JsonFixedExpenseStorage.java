package seedu.address.storage.fixedexpense;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.listmanager.ReadOnlyFixedExpenseManager;

/**
 * A class to access FixedExpenseManager data stored as a json file on the hard disk.
 */
public class JsonFixedExpenseStorage implements FixedExpenseStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFixedExpenseStorage.class);

    private Path filePath;

    public JsonFixedExpenseStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFixedExpenseStorageFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFixedExpenseManager> readFixedExpenses() throws DataConversionException {
        return readFixedExpenses(filePath);
    }

    /**
     * Similar to {@link #readFixedExpenses()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFixedExpenseManager> readFixedExpenses(
            Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFixedExpenseManager> jsonfixedExpenseManager = JsonUtil.readJsonFile(
                filePath, JsonSerializableFixedExpenseManager.class);
        if (jsonfixedExpenseManager.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonfixedExpenseManager.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFixedExpenses(ReadOnlyFixedExpenseManager fixedExpenseManager) throws IOException {
        saveFixedExpenses(fixedExpenseManager, filePath);
    }

    /**
     * Similar to {@link #saveFixedExpenses(ReadOnlyFixedExpenseManager)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFixedExpenses(
            ReadOnlyFixedExpenseManager fixedExpenseManager, Path filePath) throws IOException {
        requireNonNull(fixedExpenseManager);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFixedExpenseManager(fixedExpenseManager), filePath);
    }

}
