package Assignment1;

import java.util.Scanner;

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

    /**
     * TODO: make the triangle bigger or smaller.
     * @param canvasWidth
     * @param canvasHeight
     * @param backGroundChar
     * @param sc
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