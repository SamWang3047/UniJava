package COMP90041.Assignment1;

import java.util.Scanner;

/**
 * COMP90041, Sem1, 2023: Assignment 1
 * @author: Zhiyuan Wang
 * @studentEmail: zhiyuanw6@student.unimelb.edu.au
 * @studentID: 1406985
 * @personalWebsite: www.hellosam.top
 *
*/
public class KinderKit {
    public static void main(String[] args) {
        //Initiate a new drawing canvas using args
        DrawingCanvas drawingCanvas = new DrawingCanvas(args);
        Scanner sc = new Scanner(System.in);
        System.out.println("----DIGITAL KINDER KIT: LET'S PLAY & LEARN----");

        drawingCanvas.printCurrentCanvasDetails();

        //drawing Options != 4, program
        while (true) {
            drawingCanvas.printDrawingSelection();
            int drawingOptions = -1;

            //try to get drawing options
            drawingOptions = drawingCanvas.getDrawingOption(sc);

            //Quit the program
            if (drawingOptions == 4) {
                System.out.println("Goodbye! We hope you had fun :)");
                break;
            }

            //draw the first one sphere, so the default set is Y
            char drawAnotherOne = 'Y';
            drawingCanvas.drawAnotherOne(drawAnotherOne, drawingOptions, sc);
        }

    }
}
