package Assignment2;

import java.util.ArrayList;
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

    public void printToBitMap(char[][] bitmap) {
        for (int i = startPrintPointY; i < sideLength + startPrintPointY; i++) {
            for (int j = startPrintPointX; j < sideLength - i + startPrintPointX + startPrintPointY; j++) {
                bitmap[i][j] = printingChar;
            }
        }
    }

    public void printTriangleArrayList(ArrayList<Triangle> triangleList, char[][] canvasBitmap, int canvasHeight, int canvasWidth, char BGChar) {
        canvasInit(canvasWidth, canvasHeight, canvasBitmap, BGChar);
        for (int i = 0; i < triangleList.size(); i++) {
            triangleList.get(i).printToBitMap(canvasBitmap);
        }
        showBitmap(canvasBitmap);
    }
    /**
     * Decide whether the triangle going to zoom or move
     * @param canvasWidth get the current width of the canvas
     * @param canvasHeight get the current height of the canvas
     * @param backGroundChar get the current bg char of the canvas
     */
    public void zoomMoveOrRotate(int canvasWidth, int canvasHeight, char backGroundChar, Scanner sc,
                                 ArrayList<Triangle> triangleList, char[][] canvasBitmap) {
        System.out.println("Type Z/M/R for zooming/moving/rotating. Use other keys to quit the Zooming/Moving/Rotating mode.");
        char zoomMoveOrRotate = sc.nextLine().toUpperCase().charAt(0);
        while (zoomMoveOrRotate == 'Z' || zoomMoveOrRotate == 'M' || zoomMoveOrRotate == 'R') {
            //Print all the triangles
            printTriangleArrayList(triangleList, canvasBitmap, canvasHeight, canvasWidth, backGroundChar );
            if (zoomMoveOrRotate == 'Z') {
                zoom(canvasWidth, canvasHeight, backGroundChar, sc, triangleList, canvasBitmap);
            } else if (zoomMoveOrRotate == 'M'){
                move(canvasWidth, canvasHeight, backGroundChar, sc, triangleList, canvasBitmap);
            } else {
                rotate(canvasWidth, canvasHeight, backGroundChar, sc);
            }
            System.out.println("Type Z/M/R for zooming/moving/rotating. Use other keys to quit the Zooming/Moving/Rotating mode.");
            zoomMoveOrRotate = sc.nextLine().toUpperCase().charAt(0);
        }
    }


    /**
     * TODO: Make the triangle bigger or smaller.
     *
     * @param canvasWidth    get the current width of the canvas
     * @param canvasHeight   get the current height of the canvas
     * @param backGroundChar get the current bg char of the canvas
     * @param triangleList
     * @param canvasBitmap
     */
    public void zoom(int canvasWidth, int canvasHeight, char backGroundChar, Scanner sc, ArrayList<Triangle> triangleList, char[][] canvasBitmap) {
        System.out.println("Type I/O to zoom in/out. Use other keys to go back to the Zooming/Moving menu.");
        char inOrOut = sc.nextLine().toUpperCase().charAt(0);
        while (inOrOut == 'I' || inOrOut == 'O') {
            if (inOrOut == 'I') {
                if (sideLength + 1 > canvasWidth || sideLength + 1 > canvasHeight) {
                    System.out.println("This triangle reaches its limit. You cannot make it bigger!");
                } else {
                    sideLength = sideLength + 1;
                }
                printTriangleArrayList(triangleList, canvasBitmap, canvasHeight, canvasWidth, backGroundChar);
            } else {
                if (sideLength - 1 < 0) {
                    System.out.println("This triangle reaches its limit. You cannot make it smaller!");
                } else {
                    sideLength = sideLength - 1;
                }
                printTriangleArrayList(triangleList, canvasBitmap, canvasHeight, canvasWidth, backGroundChar);
            }
            System.out.println("Type I/O to zoom in/out. Use other keys to go back to the Zooming/Moving menu.");
            inOrOut = sc.nextLine().toUpperCase().charAt(0);
        }
        printTriangleArrayList(triangleList, canvasBitmap, canvasHeight, canvasWidth, backGroundChar);
    }

    /**
     * Move triangle to different directions
     * @param canvasWidth get the current width of the canvas
     * @param canvasHeight get the current height of the canvas
     * @param backGroundChar get the current bg char of the canvas
     */
    public void move(int canvasWidth, int canvasHeight, char backGroundChar, Scanner sc,
                     ArrayList<Triangle> triangleList, char[][] canvasBitmap) {
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
                    break;
                case 'S':
                    if (startX + triSideLength + 1 > canvasWidth) {
                        System.out.println("You cannot move this triangle outside of the drawing canvas!");
                    } else {
                        startPrintPointX = startX + 1;
                    }
                    break;
                case 'W':
                    if (startY - 1 < 0) {
                        System.out.println("You cannot move this triangle outside of the drawing canvas!");
                    } else {
                        startPrintPointY = startY - 1;
                    }
                    break;
                case 'Z':
                    if (startY + triSideLength + 1 > canvasHeight) {
                        System.out.println("You cannot move this triangle outside of the drawing canvas!");
                    } else {
                        startPrintPointY = startY + 1;
                    }
                    break;
            }

            printTriangleArrayList(triangleList, canvasBitmap, canvasHeight, canvasWidth, backGroundChar);

            System.out.println("Type A/S/W/Z to move left/right/up/down. Use other keys to go back to the Zooming/Moving menu.");
            direction = sc.nextLine().toUpperCase().charAt(0);
        }
        printTriangleArrayList(triangleList, canvasBitmap, canvasHeight, canvasWidth, backGroundChar);
    }

    public void rotate(int canvasWidth, int canvasHeight, char backGroundChar, Scanner sc) {

    }

    public void showBitmap(char[][] bitmap) {

        for (int i = 0; i < bitmap.length; i++) {
            for (int j = 0; j < bitmap[0].length; j++) {
                System.out.print(bitmap[i][j]);
            }
            System.out.println();
        }
    }
    public void canvasInit(int canvasWidth, int canvasHeight, char[][]canvasBitmap, char backGroundChar) {
        for (int i = 0; i < canvasHeight; i++) {
            for (int j = 0; j < canvasWidth; j++) {
                canvasBitmap[i][j] = backGroundChar;
            }
        }
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