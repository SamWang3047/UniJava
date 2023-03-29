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
        System.out.println("----DIGITAL KINDER KIT: LET'S PLAY & LEARN----");

        drawingCanvas.getCurrentCanvasDetail();


        while (true) {
            drawingCanvas.printDrawingSelection();
            int drawingOptions = Integer.parseInt(sc.nextLine());
            while (drawingOptions > 4 || drawingOptions < 1) {
                System.out.println("Unsupported option. Please try again!");
                drawingCanvas.printDrawingSelection();
                drawingOptions = Integer.parseInt(sc.nextLine());
            }

            //Quit the program
            if (drawingOptions == 4) {
                System.out.println("Goodbye! We hope you had fun :)");
                break;
            }

            char drawAnotherTri = 'Y';
            while (drawAnotherTri == 'Y') {
                drawingCanvas.drawOptions(drawingOptions);
                System.out.println("Draw another triangle (Y/N)?");
                drawAnotherTri = sc.nextLine().toUpperCase().charAt(0);
                while (drawAnotherTri != 'Y' && drawAnotherTri != 'N') {
                    System.out.println("Unsupported option. Please try again!");
                    System.out.println("Draw another triangle (Y/N)?");
                    drawAnotherTri = sc.nextLine().toUpperCase().charAt(0);
                }
            }
        }

    }
}
