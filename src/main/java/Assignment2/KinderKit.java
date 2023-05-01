package Assignment2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * COMP90041, Sem1, 2023: Assignment 2
 * @author: Zhiyuan Wang
 * @studentEmail: zhiyuanw6@student.unimelb.edu.au
 * @studentID: 1406985
 * @personalWebsite: www.hellosam.top
 *
*/
public class KinderKit {
    public static void main(String[] args) {
        //DON'T TOUCH LINES 8 to 59
        //Check program arguments
        if (args.length != 1) {
            System.out.println("This program takes exactly one argument!");
            return;
        }

        //Read sample drawing from file
        Scanner inputStream = null;
        char[][] bitmap = null;
        int rows = 0, cols = 0;
        char bgChar;

        try {
            inputStream = new Scanner(new FileInputStream(args[0]));
            String line;

            //Read the first line
            if (inputStream.hasNextLine()) {
                line = inputStream.nextLine();
                String[] tmps = line.split(",");
                if (tmps.length != 3) {
                    System.out.println("The given file is in wrong format!");
                    return;
                } else {
                    rows = Integer.parseInt(tmps[0]);
                    cols = Integer.parseInt(tmps[1]);
                    bgChar = tmps[2].charAt(0);
                    bitmap = new char[rows][cols];
                }
            } else {
                System.out.println("The given file seems empty!");
                return;
            }

            //Read the remaining lines
            int rowIndex = 0;
            while (inputStream.hasNextLine()) {
                line = inputStream.nextLine();
                String[] tmps = line.split(",");
                for(int i = 0; i < tmps.length; i++) {
                    bitmap[rowIndex][i] = tmps[i].charAt(0);
                }
                rowIndex++;
            }
            //Close the file input stream
            inputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("The given file is not found!");
            return;
        }

        //TODO: Write your code from here
        //Initiate a new drawing canvas using args
        DrawingCanvas drawingCanvas = new DrawingCanvas(cols, rows, bgChar, bitmap);

        Scanner sc = new Scanner(System.in);
        System.out.println("----DIGITAL KINDER KIT: LET'S PLAY & LEARN----");

        //drawing Options != 4, program
        while (true) {
            drawingCanvas.printDrawingSelection("1");
            int drawingOptions = -1;
            //try to get drawing options
            drawingOptions = drawingCanvas.getDrawingOption(sc, 3, "1", 1);
            //Quit
            if (drawingOptions == 3) {
                System.out.println("Goodbye! We hope you had fun :)");
                break;
            }
            drawingCanvas.drawOptions(drawingOptions, sc);
        }

    }
}
