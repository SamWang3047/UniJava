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

    public void printRectangle(int canvasWidth, int canvasHeight, char backGroundChar) {
        //i for outer loop, controls rows = canvasHeight
        for (int i = 0; i < canvasHeight; i++) {
            //j for each row print.
            int j = 0;
            if (i < sideHeight) {
                while (j < startPrintPointX) {
                    System.out.print(backGroundChar);
                    j++;
                }
                while (j < sideWidth + startPrintPointX + startPrintPointY) {
                    System.out.print(printingChar);
                    j++;
                }
            }
            while (j < canvasWidth) {
                System.out.print(backGroundChar);
                j++;
            }
            System.out.println();
        }
    }

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