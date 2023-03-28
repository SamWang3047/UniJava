package Assignment1;

import java.util.Scanner;

/**
 * COMP90041, Sem1, 2023: Assignment 1
 * @author: Zhiyuan Wang
*/
public class KinderKit {
    public static void main(String[] args) {
        DrawingCanvas drawingCanvas = new DrawingCanvas(args);
        Scanner sc = new Scanner(System.in);
        System.out.println("----DIGITAL KINDER KIT: LET'S PLAY & LEARN----\n" +
                            "Current drawing canvas settings:");
        System.out.println("- Width: " + drawingCanvas.getCanvasWidth());
        System.out.println("- Height: " + drawingCanvas.getCanvasHeight());
        System.out.println("- Background character: " + drawingCanvas.getBackGroundChar());


        drawingCanvas.printDrawingSelection();
        int drawingOptions = Integer.parseInt(sc.nextLine());
        while (drawingOptions > 4 || drawingOptions < 1) {
            System.out.println("Unsupported option. Please try again!");
            drawingCanvas.printDrawingSelection();
            drawingOptions = Integer.parseInt(sc.nextLine());
        }
        drawingCanvas.drawing(drawingOptions);

        System.out.println("Draw another triangle (Y/N)?");


    }
}
