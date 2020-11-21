package it.unibo.oop.lab.advanced;

import java.io.FileNotFoundException;
import java.io.PrintStream;
/**
 * {@link DrawNumberView} implementation.
 * Prints on a file which path is given to the constructor.
 */
public final class DrawNumberViewFileImpl implements DrawNumberView {

    private PrintStream file;
    /**
     * Constructor.
     * @param filePath String representation of the output file-path.
     */
    public DrawNumberViewFileImpl(final String filePath) {
        try {
            this.file = new PrintStream(filePath);
        } catch (FileNotFoundException e) {
            this.displayError("FileNotFoundException");
        }
    }
    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
    }
    @Override
    public void start() {
    }

    @Override
    public void numberIncorrect() {
        file.println("Number Incorrect");
    }

    @Override
    public void result(final DrawResult res) {
        file.println(res.getDescription());
    }

    @Override
    public void limitsReached() {
        file.println("You lost");
    }

    @Override
    public void displayError(final String message) {
        file.println(message);
    }

}
