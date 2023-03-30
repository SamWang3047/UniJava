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
     * Getters and Setters
     */
    public int getSideWidth() {
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