package it.unibo.oop.lab.mvc;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final class ControllerImpl implements Controller {
    private final List<String> printedStringsHistory = new LinkedList<>();
    private String nextString;

    @Override
    public void setNextString(final String s) {
        this.nextString = Objects.requireNonNull(s);
    }

    @Override
    public String getNextString() {
        return this.nextString;
    }

    @Override
    public String[] getPrintedStringsHistory() {
        final String[] returnArray = new String[printedStringsHistory.size()];
        int i = 0;
        final Iterator<String> iterator = printedStringsHistory.iterator();
        while (iterator.hasNext()) {
            returnArray[i++] = iterator.next();
        }
        return returnArray;
    }

    @Override
    public void printCurrentString() {
        if (this.nextString != null) {
            this.printedStringsHistory.add(this.nextString);
            System.out.println(this.nextString);
        } else {
            throw new IllegalStateException();
        }
    }

}
