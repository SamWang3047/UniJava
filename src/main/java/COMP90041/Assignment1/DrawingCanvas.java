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

public class DrawingCanvas {
    //Use for combine duplicate print method like getLength
    private static final String[][] PRINT_STRINGS = {{"triangle", "rectangle"},
                                               {"height", "width"},
                                               {"large", "long"},
                                               {"Side length", "side length"}};

    private int canvasWidth;
    private int canvasHeight;
    private char backGroundChar;

    /**
     * Drawing Canvas constructor. Take args to initialize a DC object.
     * @param args Program arguments
     */
    public DrawingCanvas(String[] args) {
        canvasWidth = Integer.parseInt(args[0]);
        canvasHeight = Integer.parseInt(args[1]);
        backGroundChar = args[2].charAt(0);
    }

    /**
     * Distinguish the options
     * @param drawingOptions number of the option
     * @param sc only one scanner
     */
    public void drawOptions(int drawingOptions, Scanner sc) {
        switch (drawingOptions) {
            case 1://Draw triangles
                drawTriangle(sc);
                break;
            case 2:
                drawRectangle(sc);
                break;
            case 3:
                updateCanvas(sc);
                System.out.println();
                printCurrentCanvasDetails();
                break;
        }
    }

    /**
     * TODO: The process to draw triangles, first to get the side length of triangle using getTrisideLength method,
     *       then create a triangle object and pass into the printTriangle method. Last pass the triangle into the
     *       zoomOrMoving method.
     * @param sc
     */
    public void drawTriangle(Scanner sc) {
        int triSideLength = getTriSideLength(sc);

        System.out.println("Printing character:");
        char printingChar = sc.nextLine().charAt(0);

        Triangle triangle = new Triangle(triSideLength, printingChar);
        triangle.printTriangle(canvasWidth, canvasHeight, backGroundChar);

        triangle.zoomOrMoving(canvasWidth, canvasHeight, backGroundChar, sc);
    }

    /**
     * TODO: Same as the drawTriangle method. Simplified the getRectangleSide method, need to pass isWidth variable.
     * @param sc
     */
    public void drawRectangle(Scanner sc) {
//        int[] recSides = getRectangleSide(sc);
        int recWidth = getRectangleSide(sc, 1);
        int recHeight = getRectangleSide(sc, 0);

        System.out.println("Printing character:");
        char printingChar = sc.nextLine().charAt(0);
        Rectangle rectangle = new Rectangle(recWidth, recHeight, printingChar);
        rectangle.printRectangle(canvasWidth, canvasHeight, backGroundChar);

        rectangle.zoomOrMoving(canvasWidth, canvasHeight, backGroundChar, sc);
    }

    /**
     * TODO: Update the canvas attributes with correctness check.
     * @param sc
     */
    public void updateCanvas(Scanner sc) {
        int updateCanvasWidth = 0;
        int updateCanvasHeight = 0;
        char updateCanvasChar = '`';
        while (true) {
            try {
                System.out.print("Canvas width: ");
                updateCanvasWidth = Integer.parseInt(sc.nextLine());
                System.out.print("Canvas height: ");
                updateCanvasHeight = Integer.parseInt(sc.nextLine());
                System.out.print("Background character: ");
                updateCanvasChar = sc.nextLine().charAt(0);
                break;
            } catch (Exception e) {
//            e.printStackTrace();
                System.out.println("Invalid input!");
            }
        }
        canvasWidth = updateCanvasWidth;
        canvasHeight = updateCanvasHeight;
        backGroundChar = updateCanvasChar;

        System.out.println("Drawing canvas has been updated!");
    }

    /**
     * TODO: simply print the selection part.
     */
    public void printDrawingSelection() {
        System.out.println("Please select an option. Type 4 to exit.\n" +
                "1. Draw triangles\n" +
                "2. Draw rectangles\n" +
                "3. Update drawing canvas settings\n" +
                "4. Exit");
    }
    /**
     * TODO: simply print the canvas details part.
     */
    public void printCurrentCanvasDetails() {
        System.out.println("Current drawing canvas settings:");
        System.out.println("- Width: " + canvasWidth);
        System.out.println("- Height: " + canvasHeight);
        System.out.println("- Background character: " + backGroundChar);
        System.out.println();
    }

    /**
     * TODO: Get the triangle length with the correctness check.
     * @param sc
     * @return the user input side length of a triangle.
     */
    public int getTriSideLength(Scanner sc) {
        int sideLength = 0;
        while (true) {
            try {
                System.out.println("Side length:");
                sideLength = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
//            e.printStackTrace();
                System.out.println("Invalid input!");
            }
        }
        //notice that the triangle only need to be smaller than the smaller value of Canvas width and height.
        int minSide = Math.min(canvasWidth, canvasHeight);
        while (sideLength <= 0 || (sideLength > minSide)) {
            if (sideLength <= 0) {
                System.out.println("Error! The side length can not be 0 or negative");
            }
            if (sideLength > canvasWidth || sideLength > canvasHeight) {
                System.out.println("Error! The side length is too long (Current canvas size is "
                        + canvasWidth + "x" + canvasHeight + "). Please try again.");
            }
            System.out.println("Side length:");
            sideLength = Integer.parseInt(sc.nextLine());
        }
        return sideLength;
    }

    /**
     * TODO: Get the rectangle length with the correctness check. Use the PRINT_STRINGS to simplify the width and height.
     * @param sc
     * @param isWidth 1 means the side is width, and 0 means side is height(is not width).
     * @return side length
     */
    public int getRectangleSide(Scanner sc, int isWidth) {
        int recSideLen = 0;
        while (true) {
            try {
                System.out.println(PRINT_STRINGS[1][isWidth] + ":");
                recSideLen = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
//              e.printStackTrace();
            }
        }
        int canvasSide = 0;
        if (isWidth == 1) {
            canvasSide = canvasWidth;
        } else {
            canvasSide = canvasHeight;
        }
        while (recSideLen <= 0 || (recSideLen > canvasSide)) {
            if (recSideLen <= 0) {
                System.out.println("Error! The " + PRINT_STRINGS[1][isWidth] + " can not be 0 or negative");
            }
            if (recSideLen > canvasSide) {
                System.out.println("Error! The " + PRINT_STRINGS[1][isWidth] + " is too large (Current canvas size is "
                        + canvasWidth + "x" + canvasHeight + "). Please try again.");
            }
            System.out.println(PRINT_STRINGS[1][isWidth] + ":");
            recSideLen = Integer.parseInt(sc.nextLine());
        }
        return recSideLen;
    }

    /**
     * Get drawing options from user input, has error fixing method
     * @param sc
     * @return a new and right drawing option
     */
    public int getDrawingOption(Scanner sc) {
        int drawingOptions = 0;
        //try to get drawing options, catching the NumberFormat exception to prevent user input is not a number
        while (true) {
            try {
                drawingOptions = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
//                e.printStackTrace();
                System.out.println("Invalid input!");
                printCurrentCanvasDetails();
            }
        }
        //not in the correct field
        while (drawingOptions > 4 || drawingOptions < 1) {
            System.out.println("Unsupported option. Please try again!");
            printDrawingSelection();
            //another time
            while (true) {
                try {
                    drawingOptions = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
//                e.printStackTrace();
                    System.out.println("Invalid input!");
                    printCurrentCanvasDetails();
                }
            }
        }
        return drawingOptions;
    }

    /**
     * Decide whether user need to draw another one sphere
     * @param drawAnotherOne default is Y for the first time for drawing the first sphere.
     * @param drawingOptions from the top
     */

    public void drawAnotherOne(char drawAnotherOne, int drawingOptions, Scanner sc) {
        while (drawAnotherOne == 'Y') {
            drawOptions(drawingOptions, sc);
            //differentiate the triangle or rectangle
            if (drawingOptions == 1) {
                System.out.println("Draw another triangle (Y/N)?");
            } else if (drawingOptions == 2) {
                System.out.println("Draw another rectangle (Y/N)?");
                //future updates
            } else if (drawingOptions == 3){
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

    /**
     * Getters and Setters
     */
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