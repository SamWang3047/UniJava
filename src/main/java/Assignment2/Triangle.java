package Assignment2;

import java.util.Scanner;
/**
 * COMP90041, Sem1, 2023: Assignment 2
 * @author: Zhiyuan Wang
 * @studentEmail: zhiyuanw6@student.unimelb.edu.au
 * @studentID: 1406985
 * @personalWebsite: www.hellosam.top
 *
 */

public class Triangle {
    private static final int DEFAULT_START_PRINT_POINT = 0;
    private int sideLength;
    private int startPrintPointX;
    private int startPrintPointY;
    private char printingChar;


    public Triangle(int sideLength, char printingChar) {
        this.sideLength = sideLength;
        this.printingChar = printingChar;
        this.startPrintPointX = DEFAULT_START_PRINT_POINT;
        this.startPrintPointY = DEFAULT_START_PRINT_POINT;
    }

    /**
     * TODO: print triangle to the console.
     * @param canvasWidth get the current width of the canvas
     * @param canvasHeight get the current height of the canvas
     * @param backGroundChar get the current bg char of the canvas
     */
    public void printTriangle(int canvasWidth, int canvasHeight, char backGroundChar) {
        //i for outer loop, controls rows = canvasHeight
        for (int i = 0; i < canvasHeight; i++) {
            //j for each row print, bond by canvas width.
            int j = 0;
            if (i >= startPrintPointY) {
                //before startX, print background char
                while (j < startPrintPointX) {
                    System.out.print(backGroundChar);
                    j++;
                }
                // -i to be a triangle -> sideLength - i + (startX + startY)
                while (j < sideLength - i + startPrintPointX + startPrintPointY) {
                    System.out.print(printingChar);
                    j++;
                }
            }
            //print the rest right half of the bg char
            while (j < canvasWidth) {
                System.out.print(backGroundChar);
                j++;
            }
            //next line
            System.out.println();
        }
    }

    public void printToBitMap(int canvasWidth, int canvasHeight, char[][] bitmap) {
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength - i; j++) {
                bitmap[i][j] = printingChar;
            }
        }
    }
    /**
     * Decide whether the triangle going to zoom or move
     * @param canvasWidth get the current width of the canvas
     * @param canvasHeight get the current height of the canvas
     * @param backGroundChar get the current bg char of the canvas
     */
    public void zoomMoveOrRotate(int canvasWidth, int canvasHeight, char backGroundChar, Scanner sc) {
        System.out.println("Type Z/M/R for zooming/moving/rotating. Use other keys to quit the Zooming/Moving/Rotating mode.");
        char zoomMoveOrRotate = sc.nextLine().toUpperCase().charAt(0);
        while (zoomMoveOrRotate == 'Z' || zoomMoveOrRotate == 'M' || zoomMoveOrRotate == 'R') {
            printTriangle(canvasWidth, canvasHeight, backGroundChar);
            if (zoomMoveOrRotate == 'Z') {
                zoom(canvasWidth, canvasHeight, backGroundChar, sc);
            } else if (zoomMoveOrRotate == 'M'){
                move(canvasWidth, canvasHeight, backGroundChar, sc);
            } else {

            }
            System.out.println("Type Z/M/R for zooming/moving/rotating. Use other keys to quit the Zooming/Moving/Rotating mode.");
            zoomMoveOrRotate = sc.nextLine().toUpperCase().charAt(0);
        }
    }


    /**
     * TODO: Make the triangle bigger or smaller.
     * @param canvasWidth get the current width of the canvas
     * @param canvasHeight get the current height of the canvas
     * @param backGroundChar get the current bg char of the canvas
     */
    public void zoom(int canvasWidth, int canvasHeight, char backGroundChar, Scanner sc) {
        System.out.println("Type I/O to zoom in/out. Use other keys to go back to the Zooming/Moving menu.");
        char inOrOut = sc.nextLine().toUpperCase().charAt(0);
        while (inOrOut == 'I' || inOrOut == 'O') {
            if (inOrOut == 'I') {
                if (sideLength + 1 > canvasWidth || sideLength + 1 > canvasHeight) {
                    System.out.println("This triangle reaches its limit. You cannot make it bigger!");
                } else {
                    sideLength = sideLength + 1;
                }
                printTriangle(canvasWidth,canvasHeight, backGroundChar);
            } else {
                if (sideLength - 1 < 0) {
                    System.out.println("This triangle reaches its limit. You cannot make it smaller!");
                } else {
                    sideLength = sideLength - 1;
                }
                printTriangle(canvasWidth,canvasHeight, backGroundChar);
            }
            System.out.println("Type I/O to zoom in/out. Use other keys to go back to the Zooming/Moving menu.");
            inOrOut = sc.nextLine().toUpperCase().charAt(0);
        }
        printTriangle(canvasWidth,canvasHeight, backGroundChar);
    }

    /**
     * Move triangle to different directions
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
            int triSideLength = sideLength;
            switch (direction) {
                case 'A':
                    if (startX - 1 < 0) {
                        System.out.println("You cannot move this triangle outside of the drawing canvas!");
                    } else {
                        startPrintPointX = startX - 1;
                    }
                    printTriangle(canvasWidth, canvasHeight, backGroundChar);
                    break;
                case 'S':
                    if (startX + triSideLength + 1 > canvasWidth) {
                        System.out.println("You cannot move this triangle outside of the drawing canvas!");
                    } else {
                        startPrintPointX = startX + 1;
                    }
                    printTriangle(canvasWidth, canvasHeight, backGroundChar);
                    break;
                case 'W':
                    if (startY - 1 < 0) {
                        System.out.println("You cannot move this triangle outside of the drawing canvas!");
                    } else {
                        startPrintPointY = startY - 1;
                    }
                    printTriangle(canvasWidth, canvasHeight, backGroundChar);
                    break;
                case 'Z':
                    if (startY + triSideLength + 1 > canvasHeight) {
                        System.out.println("You cannot move this triangle outside of the drawing canvas!");
                    } else {
                        startPrintPointY = startY + 1;
                    }
                    printTriangle(canvasWidth, canvasHeight, backGroundChar);
                    break;
            }
            System.out.println("Type A/S/W/Z to move left/right/up/down. Use other keys to go back to the Zooming/Moving menu.");
            direction = sc.nextLine().toUpperCase().charAt(0);
        }
        printTriangle(canvasWidth, canvasHeight, backGroundChar);
    }

    public void rotate(int canvasWidth, int canvasHeight, char backGroundChar, Scanner sc) {

    }
    /**
     * Getters and Setters
     */
    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }

    public char getPrintingChar() {
        return printingChar;
    }

    public void setPrintingChar(char printingChar) {
        this.printingChar = printingChar;
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

}