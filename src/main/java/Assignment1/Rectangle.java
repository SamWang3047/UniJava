package Assignment1;

import java.util.Scanner;

public class Rectangle {
    private static final int DEFAULT_START_PRINT_POINT = 0;
    private int sideWidth;
    private int sideHeight;
    private int startPrintPointX;
    private int startPrintPointY;
    private char printingChar;

    public Rectangle(int sideWidth, int sideHeight, char printingChar) {
        this.sideWidth = sideWidth;
        this.sideHeight = sideHeight;
        this.printingChar = printingChar;
        this.startPrintPointX = DEFAULT_START_PRINT_POINT;
        this.startPrintPointY = DEFAULT_START_PRINT_POINT;
    }

    /**
     * TODO: print rectangle to the console.
     * @param canvasWidth get the current width of the canvas
     * @param canvasHeight get the current height of the canvas
     * @param backGroundChar get the current bg char of the canvas
     */
    public void printRectangle(int canvasWidth, int canvasHeight, char backGroundChar) {
        //i for outer loop, controls rows = canvasHeight
        for (int i = 0; i < canvasHeight; i++) {
            //j for each row print, bond by canvas width.
            int j = 0;
            //this if print the left half of line, until the last char of print char.
            if (i < sideHeight + startPrintPointY) {
                //before the startY, print bg char, so jump to the last while
                //after the startY, print regularly
                if (i >= startPrintPointY) {
                    //before the startX, print bg char
                    while (j < startPrintPointX) {
                        System.out.print(backGroundChar);
                        j++;
                    }
                    //from the startX, print char
                    while (j < sideWidth + startPrintPointX) {
                        System.out.print(printingChar);
                        j++;
                    }
                }
            }
            //print the rest right half of the bg char
            while (j < canvasWidth) {
                System.out.print(backGroundChar);
                j++;
            }
            //next line.
            System.out.println();
        }
    }

    /**
     * Decide whether the rectangle going to zoom or move
     * @param canvasWidth get the current width of the canvas
     * @param canvasHeight get the current height of the canvas
     * @param backGroundChar get the current bg char of the canvas
     */
    public void zoomOrMoving(int canvasWidth, int canvasHeight, char backGroundChar, Scanner sc) {
        System.out.println("Type Z/M for zooming/moving. Use other keys to quit the Zooming/Moving mode.");
        char zoomingOrMoving = sc.nextLine().toUpperCase().charAt(0);
        while (zoomingOrMoving == 'Z' || zoomingOrMoving == 'M') {
            printRectangle(canvasWidth, canvasHeight, backGroundChar);
            if (zoomingOrMoving == 'Z') {
                zoom(canvasWidth, canvasHeight, backGroundChar, sc);
            } else {
                move(canvasWidth, canvasHeight, backGroundChar, sc);
            }
            System.out.println("Type Z/M for zooming/moving. Use other keys to quit the Zooming/Moving mode.");
            zoomingOrMoving = sc.nextLine().toUpperCase().charAt(0);
        }
    }


    /**
     * Make the rectangle bigger or smaller
     * @param canvasWidth get the current width of the canvas
     * @param canvasHeight get the current height of the canvas
     * @param backGroundChar get the current bg char of the canvas
     */
    public void zoom(int canvasWidth, int canvasHeight, char backGroundChar, Scanner sc) {
        System.out.println("Type I/O to zoom in/out. Use other keys to go back to the Zooming/Moving menu.");
        char inOrOut = sc.nextLine().toUpperCase().charAt(0);
        while (inOrOut == 'I' || inOrOut == 'O') {
            int recSideWidth = sideWidth;
            int recSideHeight = sideHeight;

            if (inOrOut == 'I') {
                if (recSideWidth + 1 > canvasWidth || recSideHeight + 1 > canvasHeight) {
                    System.out.println("This rectangle reaches its limit. You cannot make it bigger!");
                } else {
                    sideWidth = recSideWidth + 1;
                    sideHeight = recSideHeight + 1;
                }
                printRectangle(canvasWidth,canvasHeight, backGroundChar);
            } else {
                if (recSideWidth - 1 < 0 || recSideHeight - 1 < 0) {
                    System.out.println("This rectangle reaches its limit. You cannot make it smaller!");
                } else {
                    sideWidth = recSideWidth - 1;
                    sideHeight = recSideHeight - 1;
                }
                printRectangle(canvasWidth,canvasHeight, backGroundChar);
            }
            System.out.println("Type I/O to zoom in/out. Use other keys to go back to the Zooming/Moving menu.");
            inOrOut = sc.nextLine().toUpperCase().charAt(0);
        }
        printRectangle(canvasWidth,canvasHeight, backGroundChar);
    }

    /**
     * Move rectangle to 4 directions
     * @param canvasWidth get the current width of the canvas
     * @param canvasHeight get the current height of the canvas
     * @param backGroundChar get the current bg char of the canvas
     */
    public void move(int canvasWidth, int canvasHeight, char backGroundChar, Scanner sc) {
        System.out.println("Type A/S/W/Z to move left/right/up/down. Use other keys to go back to the Zooming/Moving menu.");
        char direction = sc.nextLine().toUpperCase().charAt(0);
        //A for left, S for right, W for up, Z for down
        while (direction == 'A' || direction == 'S' || direction == 'W' || direction == 'Z') {
            int startX = startPrintPointX, startY = startPrintPointY;

            int recSideWidth = sideWidth;
            int recSideHeight = sideHeight;

            switch (direction) {
                case 'A':
                    if (startX - 1 < 0) {
                        System.out.println("You cannot move this rectangle outside of the drawing canvas!");
                    } else {
                        startPrintPointX  = startX - 1;
                    }
                    printRectangle(canvasWidth, canvasHeight, backGroundChar);
                    break;
                case 'S':
                    if (startX + recSideWidth + 1 > canvasWidth) {
                        System.out.println("You cannot move this rectangle outside of the drawing canvas!");
                    } else {
                        startPrintPointX  = startX + 1;
                    }
                    printRectangle(canvasWidth, canvasHeight, backGroundChar);
                    break;
                case 'W':
                    if (startY - 1 < 0) {
                        System.out.println("You cannot move this rectangle outside of the drawing canvas!");
                    } else {
                        startPrintPointY = startY - 1;
                    }
                    printRectangle(canvasWidth, canvasHeight, backGroundChar);
                    break;
                case 'Z':
                    if (startY + recSideHeight + 1 > canvasHeight) {
                        System.out.println("You cannot move this rectangle outside of the drawing canvas!");
                    } else {
                        startPrintPointY = startY + 1;
                    }
                    printRectangle(canvasWidth, canvasHeight, backGroundChar);
                    break;
            }
            System.out.println("Type A/S/W/Z to move left/right/up/down. Use other keys to go back to the Zooming/Moving menu.");
            direction = sc.nextLine().toUpperCase().charAt(0);
        }
        printRectangle(canvasWidth, canvasHeight, backGroundChar);
    }

    /**
     * Getters and Setters
     */
    public int SideWidth() {
        return sideWidth;
    }

    public void setSideWidth(int sideWidth) {
        this.sideWidth = sideWidth;
    }

    public int getSideHeight() {
        return sideHeight;
    }

    public void setSideHeight(int sideHeight) {
        this.sideHeight = sideHeight;
    }

    public int getStartPrintPointX() {
        return startPrintPointX;
    }

    public void setStartPrintPointX(int startPrintPointX) {
        this.startPrintPointX = startPrintPointX;
    }

    public int getStartPrintPointY() {
        return startPrintPointY;
    }

    public void setStartPrintPointY(int startPrintPointY) {
        this.startPrintPointY = startPrintPointY;
    }

    public char getPrintingChar() {
        return printingChar;
    }

    public void setPrintingChar(char printingChar) {
        this.printingChar = printingChar;
    }
}