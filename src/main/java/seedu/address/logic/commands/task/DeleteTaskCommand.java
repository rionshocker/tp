package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.name.Name;
import seedu.address.model.task.TaskDescription;

/**
 * Adds the specified task from its associated event.
 */
public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delete_task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an existing task from the specified event. "
            + "Parameters: "
            + PREFIX_NAME + "TASK_DESCRIPTION "
            + PREFIX_EVENT + "EVENT \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Book Venue "
            + PREFIX_EVENT + "NUS Career Fair 2023";

    public static final String MESSAGE_SUCCESS = "Deleted task: %1$s from event: %2$s";
    public static final String MESSAGE_MISSING_TASK = "This task does not exist";
    private final TaskDescription taskDescription;
    private final Name associatedEventName;

    /**
     * Creates a DeleteTaskCommand to delete the specified {@code task}.
     */
    public DeleteTaskCommand(TaskDescription taskDescription, Name associatedEventName) {
        requireAllNonNull(taskDescription, associatedEventName);
        this.taskDescription = taskDescription;
        this.associatedEventName = associatedEventName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTask(taskDescription, associatedEventName)) {
            throw new CommandException(MESSAGE_MISSING_TASK);
        }

        model.deleteTask(taskDescription, associatedEventName);
        return new CommandResult(String.format(MESSAGE_SUCCESS, taskDescription, associatedEventName));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteTaskCommand)) {
            return false;
        }

        DeleteTaskCommand otherDeleteTaskCommand = (DeleteTaskCommand) other;
        return taskDescription.equals(otherDeleteTaskCommand.taskDescription)
                && associatedEventName.equals(otherDeleteTaskCommand.associatedEventName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toDelete", taskDescription)
                .add("associatedEvent", associatedEventName)
                .toString();
    }
}