package Assignment1;

import java.util.Objects;
import java.util.Scanner;

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
     * TODO: Drawing Canvas constructor. Take args to initialized a DC object.
     * @param args Program arguments
     */
    public DrawingCanvas(String[] args) {
        canvasWidth = Integer.parseInt(args[0]);
        canvasHeight = Integer.parseInt(args[1]);
        backGroundChar = args[2].charAt(0);
    }

    /**
     * TODO: Distinguish the options
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

        zoomOrMoving(triangle, sc);
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

        zoomOrMoving(rectangle, sc);
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
//            e.printStackTrace();
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
     * TODO: make the rectangle bigger or smaller
     * @param sc
     * @param rectangle
     */
    public void zoom(Rectangle rectangle, Scanner sc) {
        System.out.println("Type I/O to zoom in/out. Use other keys to go back to the Zooming/Moving menu.");
        char inOrOut = sc.nextLine().toUpperCase().charAt(0);
        while (inOrOut == 'I' || inOrOut == 'O') {
            int recSideWidth = rectangle.getSideWidth();
            int recSideHeight = rectangle.getSideHeight();

            if (inOrOut == 'I') {
                if (recSideWidth + 1 > canvasWidth || recSideHeight + 1 > canvasHeight) {
                    System.out.println("This rectangle reaches its limit. You cannot make it bigger!");
                } else {
                    rectangle.setSideWidth(recSideWidth + 1);
                    rectangle.setSideHeight(recSideHeight + 1);
                }
                rectangle.printRectangle(canvasWidth,canvasHeight, backGroundChar);
            } else {
                if (recSideWidth - 1 < 0 || recSideHeight - 1 < 0) {
                    System.out.println("This rectangle reaches its limit. You cannot make it smaller!");
                } else {
                    rectangle.setSideWidth(recSideWidth - 1);
                    rectangle.setSideHeight(recSideHeight - 1);
                }
                rectangle.printRectangle(canvasWidth,canvasHeight, backGroundChar);
            }
            System.out.println("Type I/O to zoom in/out. Use other keys to go back to the Zooming/Moving menu.");
            inOrOut = sc.nextLine().toUpperCase().charAt(0);
        }
        rectangle.printRectangle(canvasWidth,canvasHeight, backGroundChar);
    }

    /**
     * TODO: move the triangle to 4 directions
     * @param triangle
     * @param sc
     */
    public void move(Triangle triangle, Scanner sc) {
        System.out.println("Type A/S/W/Z to move left/right/up/down. Use other keys to go back to the Zooming/Moving menu.");
        char direction = sc.nextLine().toUpperCase().charAt(0);
        //A for left, S for right, W for up, Z for down
        while (direction == 'A' || direction == 'S' || direction == 'W' || direction == 'Z') {
            int startX = triangle.getStartPrintPointX(), startY = triangle.getStartPrintPointY();
            int triSideLength = triangle.getSideLength();
            switch (direction) {
                case 'A':
                    if (startX - 1 < 0) {
                        System.out.println("You cannot move this triangle outside of the drawing canvas!");
                    } else {
                        triangle.setStartPrintPointX(startX - 1);
                    }
                    triangle.printTriangle(canvasWidth, canvasHeight, backGroundChar);
                    break;
                case 'S':
                    if (startX + triSideLength + 1 > canvasWidth) {
                        System.out.println("You cannot move this triangle outside of the drawing canvas!");
                    } else {
                        triangle.setStartPrintPointX(startX + 1);
                    }
                    triangle.printTriangle(canvasWidth, canvasHeight, backGroundChar);
                    break;
                case 'W':
                    if (startY - 1 < 0) {
                        System.out.println("You cannot move this triangle outside of the drawing canvas!");
                    } else {
                        triangle.setStartPrintPointY(startY - 1);
                    }
                    triangle.printTriangle(canvasWidth, canvasHeight, backGroundChar);
                    break;
                case 'Z':
                    if (startY + triSideLength + 1 > canvasHeight) {
                        System.out.println("You cannot move this triangle outside of the drawing canvas!");
                    } else {
                        triangle.setStartPrintPointY(startY + 1);
                    }
                    triangle.printTriangle(canvasWidth, canvasHeight, backGroundChar);
                    break;
            }
            System.out.println("Type A/S/W/Z to move left/right/up/down. Use other keys to go back to the Zooming/Moving menu.");
            direction = sc.nextLine().toUpperCase().charAt(0);
        }
        triangle.printTriangle(canvasWidth, canvasHeight, backGroundChar);
    }

    /**
     * TODO: move rectangle to 4 directions
     * @param rectangle
     * @param sc
     */
    public void move(Rectangle rectangle, Scanner sc) {
        System.out.println("Type A/S/W/Z to move left/right/up/down. Use other keys to go back to the Zooming/Moving menu.");
        char direction = sc.nextLine().toUpperCase().charAt(0);
        //A for left, S for right, W for up, Z for down
        while (direction == 'A' || direction == 'S' || direction == 'W' || direction == 'Z') {
            int startX = rectangle.getStartPrintPointX(), startY = rectangle.getStartPrintPointY();

            int recSideWidth = rectangle.getSideWidth();
            int recSideHeight = rectangle.getSideHeight();

            switch (direction) {
                case 'A':
                    if (startX - 1 < 0) {
                        System.out.println("You cannot move this rectangle outside of the drawing canvas!");
                    } else {
                        rectangle.setStartPrintPointX(startX - 1);
                    }
                    rectangle.printRectangle(canvasWidth, canvasHeight, backGroundChar);
                    break;
                case 'S':
                    if (startX + recSideWidth + 1 > canvasWidth) {
                        System.out.println("You cannot move this rectangle outside of the drawing canvas!");
                    } else {
                        rectangle.setStartPrintPointX(startX + 1);
                    }
                    rectangle.printRectangle(canvasWidth, canvasHeight, backGroundChar);
                    break;
                case 'W':
                    if (startY - 1 < 0) {
                        System.out.println("You cannot move this rectangle outside of the drawing canvas!");
                    } else {
                        rectangle.setStartPrintPointY(startY - 1);
                    }
                    rectangle.printRectangle(canvasWidth, canvasHeight, backGroundChar);
                    break;
                case 'Z':
                    if (startY + recSideHeight + 1 > canvasHeight) {
                        System.out.println("You cannot move this rectangle outside of the drawing canvas!");
                    } else {
                        rectangle.setStartPrintPointY(startY + 1);
                    }
                    rectangle.printRectangle(canvasWidth, canvasHeight, backGroundChar);
                    break;
            }
            System.out.println("Type A/S/W/Z to move left/right/up/down. Use other keys to go back to the Zooming/Moving menu.");
            direction = sc.nextLine().toUpperCase().charAt(0);
        }
        rectangle.printRectangle(canvasWidth, canvasHeight, backGroundChar);
    }

    /**
     * TODO: Decide whether the triangle going to zoom or move
     * @param triangle
     * @param sc
     */
    public void zoomOrMoving(Triangle triangle, Scanner sc) {
        System.out.println("Type Z/M for zooming/moving. Use other keys to quit the Zooming/Moving mode.");
        char zoomingOrMoving = sc.nextLine().toUpperCase().charAt(0);
        while (zoomingOrMoving == 'Z' || zoomingOrMoving == 'M') {
            triangle.printTriangle(canvasWidth, canvasHeight, backGroundChar);
            if (zoomingOrMoving == 'Z') {
                triangle.zoom(canvasWidth, canvasHeight, backGroundChar, sc);
            } else {
                move(triangle, sc);
            }
            System.out.println("Type Z/M for zooming/moving. Use other keys to quit the Zooming/Moving mode.");
            zoomingOrMoving = sc.nextLine().toUpperCase().charAt(0);
        }
    }

    /**
     * TODO: Decide whether the rectangle going to zoom or move
     * @param rectangle
     * @param sc
     */
    public void zoomOrMoving(Rectangle rectangle, Scanner sc) {
        System.out.println("Type Z/M for zooming/moving. Use other keys to quit the Zooming/Moving mode.");
        char zoomingOrMoving = sc.nextLine().toUpperCase().charAt(0);
        while (zoomingOrMoving == 'Z' || zoomingOrMoving == 'M') {
            rectangle.printRectangle(canvasWidth, canvasHeight, backGroundChar);
            if (zoomingOrMoving == 'Z') {
                zoom(rectangle, sc);
            } else {
                move(rectangle, sc);
            }
            System.out.println("Type Z/M for zooming/moving. Use other keys to quit the Zooming/Moving mode.");
            zoomingOrMoving = sc.nextLine().toUpperCase().charAt(0);
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