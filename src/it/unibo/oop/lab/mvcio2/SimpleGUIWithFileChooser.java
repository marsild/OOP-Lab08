package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private final JFrame frame = new JFrame("Esercizio 3");
    private final Controller controller = new Controller();
    /**
     * builds a new {@link SimpleGUI}.
     */
    public SimpleGUIWithFileChooser() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
    }
    /**
     * Method which creates the frame contents.
     */
    private void createframe() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        /*
         *  1) Add a JTextField and a button "Browse..." on the upper part of the
         * graphical interface.
         * Suggestion: use a second JPanel with a second BorderLayout, put the panel
         * in the North of the main panel, put the text field in the center of the
         * new panel and put the button in the line_end of the new panel.
         *
         * 2) The JTextField should be non modifiable. And, should display the
         * current selected file.
         */ 
        final JPanel panel2 = new JPanel(new BorderLayout());
        final JTextField textfield = new JTextField();
        textfield.setEditable(false);
        textfield.setText(controller.getPath());
        final JButton button2 = new JButton("Browse...");
        panel2.add(textfield, BorderLayout.CENTER);
        panel2.add(button2, BorderLayout.LINE_END);
        /* 3) On press, the button should open a JFileChooser. The program should
         * use the method showSaveDialog() to display the file chooser, and if the
         * result is equal to JFileChooser.APPROVE_OPTION the program should set as
         * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
         * then the program should do nothing. Otherwise, a message dialog should be
         * shown telling the user that an error has occurred (use
         * JOptionPane.showMessageDialog()).
         * 4) When in the controller a new File is set, also the graphical interface
         * must reflect such change. Suggestion: do not force the controller to
         * update the UI: in this example the UI knows when should be updated, so
         * try to keep things separated.
         */
        button2.addActionListener(new ActionListener() {

            public void actionPerformed(final ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                final int value = fc.showOpenDialog(button2);
                if (value == JFileChooser.APPROVE_OPTION) {
                    controller.setFile(fc.getSelectedFile());
                    textfield.setText(controller.getPath());
                } else if (value == JFileChooser.CANCEL_OPTION) {
                    //do nothing
                } else {
                    JOptionPane.showMessageDialog(frame, "An error has occurred", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        final JTextArea textarea = new JTextArea();
        panel.add(textarea);
        final JButton button = new JButton("Save");
        button.addActionListener(new ActionListener() {

            public void actionPerformed(final ActionEvent e) {
                try (PrintStream ps = new PrintStream(controller.getPath())) {
                    ps.print(textarea.getText());
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });
        panel.add(panel2, BorderLayout.NORTH);
        panel.add(button, BorderLayout.SOUTH);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    /**
     * @param argv ignored
     */
    public static void main(final String[] argv) {
        new SimpleGUIWithFileChooser().createframe();
    }

}
