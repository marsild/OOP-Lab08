package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {
    /**
     * 
     */
    public static final String FILE_OUTPUT = System.getProperty("user.home") + File.separator + "output.txt";
    private static final int MIN_INDEX = 0;
    private static final int MAX_INDEX = 1;
    private static final int ATTEMPTS_INDEX = 2;
    private final DrawNumber model;
    private final List<DrawNumberView> views = new LinkedList<>();

    /**
     * @param configName file configuration name containing min, max and attemps values.
     */
    public DrawNumberApp(final String configName) {
        final int[] configs = this.getConfig(configName);
        this.model = new DrawNumberImpl(configs[MIN_INDEX], configs[MAX_INDEX], configs[ATTEMPTS_INDEX]);
        final DrawNumberView view = new DrawNumberViewImpl();
        views.add(view);
        final DrawNumberView view2 = new DrawNumberViewFileImpl(FILE_OUTPUT);
        views.add(view2);
        final DrawNumberView view3 = new DrawNumberViewStdoutImpl();
        views.add(view3);
        for (final DrawNumberView v: views) {
            v.setObserver(this);
            v.start();
        }
    }
    private int[] getConfig(final String configName) {
        final InputStream in = ClassLoader.getSystemResourceAsStream(configName);
        int[] returnValues = new int[3];
        String line;
        int i = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            do {
                line = br.readLine();
                if (line != null) {
                    final StringTokenizer s = new StringTokenizer(line);
                    while (s.hasMoreElements()) {
                        s.nextElement();
                        final Integer id = Integer.parseInt(s.nextElement().toString());
                        returnValues[i++] = id;
                        }
                }
            } while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValues;
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView v: views) {
                v.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView v: views) {
                v.numberIncorrect();
            }
        } catch (AttemptsLimitReachedException e) {
            for (final DrawNumberView v: views) {
                v.limitsReached();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        new DrawNumberApp("config.yml");
    }

}
