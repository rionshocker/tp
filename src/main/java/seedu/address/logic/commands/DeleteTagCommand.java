package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * Deletes a tag from the JobFestGo.
 */
public class DeleteTagCommand extends Command {

    public static final String COMMAND_WORD = "delete_tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": deletes an existing tag from the tag list. "
            + "Parameters: "
            + PREFIX_TAG + "TAG\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "vendor ";

    public static final String MESSAGE_SUCCESS = "Deleted tag: %1$s";
    public static final String MESSAGE_MISSING_TAG = "This tag does not exist";
    private final Tag toDelete;

    /**
     * Creates a DeleteTagCommand to delete the specified {@code tag}.
     */
    public DeleteTagCommand(Tag tag) {
        requireNonNull(tag);
        this.toDelete = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTag(toDelete)) {
            throw new CommandException(MESSAGE_MISSING_TAG);
        }

        model.deleteTag(toDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteTagCommand)) {
            return false;
        }

        DeleteTagCommand otherDeleteTagCommand = (DeleteTagCommand) other;
        return toDelete.equals(otherDeleteTagCommand.toDelete);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toDelete", toDelete)
                .toString();
    }
}