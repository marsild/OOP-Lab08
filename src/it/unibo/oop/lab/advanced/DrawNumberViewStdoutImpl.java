package it.unibo.oop.lab.advanced;
/**
 * {@link DrawNumberView} implementation.
 * Prints on standard output.
 */
public final class DrawNumberViewStdoutImpl implements DrawNumberView {

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
    }
    @Override
    public void start() {
    }

    @Override
    public void numberIncorrect() {
        System.out.println("Number Incorrect");
    }

    @Override
    public void result(final DrawResult res) {
        System.out.println(res.getDescription());
    }

    @Override
    public void limitsReached() {
        System.out.println("You lost");
    }

    @Override
    public void displayError(final String message) {
        System.out.println(message);
    }

}
