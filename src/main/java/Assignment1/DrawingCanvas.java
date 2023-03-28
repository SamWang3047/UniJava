package Assignment1;

import java.util.Scanner;

public class DrawingCanvas {
    private int canvasWidth;
    private int canvasHeight;
    private char backGroundChar;

    public DrawingCanvas(String[] args) {
        canvasWidth = Integer.parseInt(args[0]);
        canvasHeight = Integer.parseInt(args[1]);
        backGroundChar = args[2].charAt(0);
    }

    public void drawing(int drawingOptions) {

        switch (drawingOptions) {
            case 1://Draw triangles
                System.out.println("Side length:");
                Scanner sc = new Scanner(System.in);
                int sideLength = Integer.parseInt(sc.nextLine());
                while (sideLength <= 0 || sideLength > canvasWidth) {
                    if (sideLength <= 0) {
                        System.out.println("Error! The side length can not be 0 or negative");
                    }
                    if (sideLength > canvasWidth) {
                        System.out.println("Error! The side length is too long (Current canvas size is "
                                            + canvasWidth + "x" + canvasHeight + "). Please try again.");
                    }
                    System.out.println("Side length:");
                    sideLength = Integer.parseInt(sc.nextLine());
                }
                System.out.println("Printing character:");
                char printingChar = sc.nextLine().charAt(0);
                Triangle triangle = new Triangle(sideLength, printingChar);
                triangle.printTriangle(canvasWidth,canvasHeight, backGroundChar);

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }
    public void printDrawingSelection() {
        System.out.println("Please select an option. Type 4 to exit.\n" +
                "1. Draw triangles\n" +
                "2. Draw rectangles\n" +
                "3. Update drawing canvas settings\n" +
                "4. Exit");
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public void setCanvasWidth(int canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    public void setCanvasHeight(int canvasHeight) {
        this.canvasHeight = canvasHeight;
    }

    public char getBackGroundChar() {
        return backGroundChar;
    }

    public void setBackGroundChar(char backGroundChar) {
        this.backGroundChar = backGroundChar;
    }


}