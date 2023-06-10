package COMP90041.Week10;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Appender {
    public static void main (String[] args) throws IOException {
        System.out.println("Type a sequence of non-empty lines");
        System.out.println("To clear the file and start again, type 'reset'.");
        String filename = "output.txt";

        // open filename as outStream;
        PrintWriter outStream = newFileOrExit(filename);
        Scanner kbd = new Scanner(System.in);
        try {
            boolean done = false;
            while (!done) {
                // Read a string from the keyboard
                String str = kbd.nextLine();
                if (str.length() == 0) {
                    done = true;
                } else if (str.equals("reset")) {
                    resetFile(outStream, filename);
                } else {
                    appendToFile(outStream, str);
                }
            }
        } catch (IOException e) {
            System.out.println("Error opening " + filename + " for writing.");
            System.exit(1);
        }
        outStream.close();
    }

    // Convenience function, since this is done twice.
    private static PrintWriter newFileOrExit (String filename) throws IOException {
        // Open a PrintWriter to overwrite filename,
        // or exit with status 1 and an error message
        // "Error opening " + filename + " for writing."
        // if an exception is thrown.
        try {
            return new PrintWriter(new FileWriter(filename, true));
        } catch (IOException e) {
            System.out.println("Error opening " + filename + " for writing.");
            throw e;
        }
    }

    private static void resetFile(PrintWriter outStream, String filename) throws IOException {
        outStream = new PrintWriter(new FileWriter(filename));
        outStream.flush();
        outStream.close();
    }

    private static void appendToFile(PrintWriter outStream, String line) {
        outStream.println(line);
        outStream.flush(); // Flush the stream to ensure the text is written immediately
    }
}