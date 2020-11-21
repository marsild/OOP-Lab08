package it.unibo.oop.lab.mvcio;

import java.io.File;

/**
 * A simple controller responsible of I/O access.
 * It considers a single file at a time, and it is able to serialize objects in it.
 * It has methods for getting the file or path of the current file, 
 * and for setting it from an input file or path file.
 */
public class Controller {
    /**
     * String that represents the File separator of the system. 
     */
    private static final String SEP = File.separator;
    /**
     * Field holding the path of the output file. By default, output.txt in the user home.
     */
    private static final String FILE_OUTPUT = System.getProperty("user.home") + SEP + "output.txt";
    /**
     * Field holding the output file.
     */
    private File file = new File(FILE_OUTPUT);
    /**
     * A method for setting a File as current file.
     * @param f file to be set as current file
     */
    public void setFile(final File f) {
        this.file = f;
    }
    /**
     * A method for setting a File, from his path, as current file.
     * @param s file path, to be set as current file
     */
    public void setFileFromPath(final String s) {
        this.file = new File(s);
    }
    /**
     * A method for getting the current File.
     * @return the current file
     */
    public File getFile() {
        return this.file;
    }
    /**
     * A method for getting the path of the current File.
     * @return the path of the current file, in form of a String.
     */
    public String getPath() {
        return this.file.getPath();
    }
    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     * 
     * 2) A method for getting the current File
     * 
     * 3) A method for getting the path (in form of String) of the current File
     * 
     * 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     * 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */

}
