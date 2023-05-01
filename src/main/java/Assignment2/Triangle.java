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
    private int[] position;
    private int angle;

    public Triangle(int sideLength, char printingChar) {
        this.sideLength = sideLength;
        this.printingChar = printingChar;
        this.startPrintPointX = DEFAULT_START_PRINT_POINT;
        this.startPrintPointY = DEFAULT_START_PRINT_POINT;
        position = new int[]{DEFAULT_START_PRINT_POINT, DEFAULT_START_PRINT_POINT};
    }


    public void printToBitMap(char[][] bitmap) {
        switch (angle) {
            case 0:
                for (int i = startPrintPointY; i < sideLength + startPrintPointY; i++) {
                    for (int j = startPrintPointX; j < sideLength - i + startPrintPointX + startPrintPointY; j++) {
                        bitmap[i][j] = printingChar;
                    }
                }
                break;
            case 90:
                for (int i = startPrintPointY; i < sideLength + startPrintPointY; i++) {
                    for (int j = sideLength + startPrintPointX - 1; j >= startPrintPointX + i - startPrintPointY; j--) {
                        bitmap[i][j] = printingChar;
                    }
                }
                break;
            case 180:
                for (int i = startPrintPointY; i < sideLength + startPrintPointY; i++) {
                    for (int j = sideLength + startPrintPointX - 1; j >= startPrintPointX + sideLength - i - 1 + startPrintPointY; j--) {
                        bitmap[i][j] = printingChar;
                    }
                }
                break;
            case 270:
                for (int i = startPrintPointY; i < sideLength + startPrintPointY; i++) {
                    for (int j = startPrintPointX; j < startPrintPointX + i - startPrintPointY + 1; j++) {
                        bitmap[i][j] = printingChar;
                    }
                }
                break;
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
            printTriangleArrayList(triangleList, canvasBitmap, canvasHeight, canvasWidth, backGroundChar);
            if (zoomMoveOrRotate == 'Z') {
                zoom(canvasWidth, canvasHeight, backGroundChar, sc, triangleList, canvasBitmap);
            } else if (zoomMoveOrRotate == 'M'){
                move(canvasWidth, canvasHeight, backGroundChar, sc, triangleList, canvasBitmap);
            } else {
                rotate(canvasWidth, canvasHeight, backGroundChar, sc, triangleList, canvasBitmap);
            }
            System.out.println("Type Z/M/R for zooming/moving/rotating. Use other keys to quit the Zooming/Moving/Rotating mode.");
            zoomMoveOrRotate = sc.nextLine().toUpperCase().charAt(0);
        }
        printTriangleArrayList(triangleList, canvasBitmap, canvasHeight, canvasWidth, backGroundChar);
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
        System.out.println("Type I/O to zoom in/out. Use other keys to go back to the Zooming/Moving/Rotating menu.");
        char inOrOut = sc.nextLine().toUpperCase().charAt(0);
        while (inOrOut == 'I' || inOrOut == 'O') {
            if (inOrOut == 'I') {
                if (sideLength + startPrintPointX + 1 > canvasWidth || sideLength +startPrintPointY + 1 > canvasHeight) {
                    System.out.println("This triangle reaches its limit. You cannot make it bigger!");
                } else {
                    sideLength = sideLength + 1;
                }
                printTriangleArrayList(triangleList, canvasBitmap, canvasHeight, canvasWidth, backGroundChar);
            } else {
                if (sideLength - 1 <= 0) {
                    System.out.println("This triangle reaches its limit. You cannot make it smaller!");
                } else {
                    sideLength = sideLength - 1;
                }
                printTriangleArrayList(triangleList, canvasBitmap, canvasHeight, canvasWidth, backGroundChar);
            }
            System.out.println("Type I/O to zoom in/out. Use other keys to go back to the Zooming/Moving/Rotating menu.");
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
        System.out.println("Type A/S/W/Z to move left/right/up/down. Use other keys to go back to the Zooming/Moving/Rotating menu.");
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

            System.out.println("Type A/S/W/Z to move left/right/up/down. Use other keys to go back to the Zooming/Moving/Rotating menu.");
            direction = sc.nextLine().toUpperCase().charAt(0);
        }
        printTriangleArrayList(triangleList, canvasBitmap, canvasHeight, canvasWidth, backGroundChar);
    }

    public void rotate(int canvasWidth, int canvasHeight, char backGroundChar, Scanner sc,
                       ArrayList<Triangle> triangleList, char[][] canvasBitmap) {
        System.out.println("Type R/L to rotate clockwise/anti-clockwise. Use other keys to go back to the Zooming/Moving/Rotating menu.");
        char clockWise = sc.nextLine().toUpperCase().charAt(0);
        while (clockWise == 'R' || clockWise == 'L') {
            switch (clockWise) {
                case 'R':
                    if (angle + 90 >= 360) {
                        angle = 0;
                    } else {
                        angle += 90;
                    }
                    break;
                case 'L':
                    if (angle - 90 < 0) {
                        angle = 270;
                    } else {
                        angle -= 90;
                    }
                    break;
            }
            printTriangleArrayList(triangleList, canvasBitmap, canvasHeight, canvasWidth, backGroundChar);

            System.out.println("Type R/L to rotate clockwise/anti-clockwise. Use other keys to go back to the Zooming/Moving/Rotating menu.");
            clockWise = sc.nextLine().toUpperCase().charAt(0);
        }
        printTriangleArrayList(triangleList, canvasBitmap, canvasHeight, canvasWidth, backGroundChar);
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

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }
    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}