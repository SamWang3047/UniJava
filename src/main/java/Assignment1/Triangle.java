package Assignment1;

import java.util.Scanner;

public class Triangle {
    private int sideLength;
    private char printingChar;

    public Triangle(int sideLength, char printingChar) {
        this.sideLength = sideLength;
        this.printingChar = printingChar;
    }

    public void printTriangle(int canvasWidth, int canvasHeight, char backGroundChar) {
        for (int i = 0; i < canvasHeight; i++) {
            int j = 0;
            while (j < sideLength - i) {
                System.out.print(printingChar);
                j++;
            }
            while (j < canvasWidth) {
                System.out.print(backGroundChar);
                j++;
            }
            System.out.println();
        }
    }

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
}