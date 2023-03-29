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

        drawingCanvas.printCurrentCanvasDetails();

        while (true) {
            drawingCanvas.printDrawingSelection();
            int drawingOptions = -1;
            while (true) {
                try {
                    drawingOptions = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
//                e.printStackTrace();
                    System.out.println("Invalid input!");
                    drawingCanvas.printCurrentCanvasDetails();
                }
            }
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

            char drawAnotherOne = 'Y';
            while (drawAnotherOne == 'Y') {
                drawingCanvas.drawOptions(drawingOptions, sc);
                if (drawingOptions == 1) {
                    System.out.println("Draw another triangle (Y/N)?");
                } else if (drawingOptions == 2) {
                    System.out.println("Draw another rectangle (Y/N)?");
                } else if (drawingOptions == 3){
                    System.out.println();
                    break;
                }
                drawAnotherOne = sc.nextLine().toUpperCase().charAt(0);
                while (drawAnotherOne != 'Y' && drawAnotherOne != 'N') {
                    System.out.println("Unsupported option. Please try again!");
                    System.out.println("Draw another triangle (Y/N)?");
                    drawAnotherOne = sc.nextLine().toUpperCase().charAt(0);
                }
            }
        }

    }
}
