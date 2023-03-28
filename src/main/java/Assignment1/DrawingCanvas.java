package Assignment1;

import java.util.Scanner;

public class DrawingCanvas {
    private int canvasWidth;
    private int canvasHeight;
    private char backGroundChar;

    public DrawingCanvas(String[] args) {
        canvasWidth = Integer.parseInt(args[0]);
        canvasHeight = Integer.parseInt(args[1]);
        backGroundChar = args[2].charAt(0);
    }
    public void drawOptions(int drawingOptions) {
        switch (drawingOptions) {
            case 1://Draw triangles
                drawing();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }
    public void drawing() {

        System.out.println("Side length:");
        Scanner sc = new Scanner(System.in);
        int sideLength = Integer.parseInt(sc.nextLine());
        while (sideLength <= 0 || sideLength > canvasWidth) {
            if (sideLength <= 0) {
                System.out.println("Error! The side length can not be 0 or negative");
            }
            if (sideLength > canvasWidth) {
                System.out.println("Error! The side length is too long (Current canvas size is "
                        + canvasWidth + "x" + canvasHeight + "). Please try again.");
            }
            System.out.println("Side length:");
            sideLength = Integer.parseInt(sc.nextLine());
        }
        System.out.println("Printing character:");
        char printingChar = sc.nextLine().charAt(0);
        Triangle triangle = new Triangle(sideLength, printingChar);
        triangle.printTriangle(canvasWidth, canvasHeight, backGroundChar);

        System.out.println("Type Z/M for zooming/moving. Use other keys to quit the Zooming/Moving mode.");
        char zoomingOrMoving = sc.nextLine().toUpperCase().charAt(0);
        while (zoomingOrMoving == 'Z' || zoomingOrMoving == 'M') {
            triangle.printTriangle(canvasWidth, canvasHeight, backGroundChar);
            if (zoomingOrMoving == 'Z') {
                System.out.println("Type I/O to zoom in/out. Use other keys to go back to the Zooming/Moving menu.");
                char inOrOut = sc.nextLine().toUpperCase().charAt(0);
                while (inOrOut == 'I' || inOrOut == 'O') {
                    int triSideLen = triangle.getSideLength();
                    if (inOrOut == 'I') {
                        if (triSideLen + 1 > canvasWidth || triSideLen + 1 > canvasHeight) {
                            System.out.println("This triangle reaches its limit. You cannot make it bigger!");
                        } else {
                            triangle.setSideLength(triSideLen + 1);
                        }
                        triangle.printTriangle(canvasWidth,canvasHeight, backGroundChar);
                    } else {
                        if (triSideLen - 1 < 0) {
                            System.out.println("This triangle reaches its limit. You cannot make it smaller!");
                        } else {
                            triangle.setSideLength(triSideLen - 1);
                        }
                        triangle.printTriangle(canvasWidth,canvasHeight, backGroundChar);
                    }
                    System.out.println("Type I/O to zoom in/out. Use other keys to go back to the Zooming/Moving menu.");
                    inOrOut = sc.nextLine().toUpperCase().charAt(0);
                }
            } else {
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
                            if (startY - 1 > canvasHeight) {
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
            }
            System.out.println("Type Z/M for zooming/moving. Use other keys to quit the Zooming/Moving mode.");
            zoomingOrMoving = sc.nextLine().toUpperCase().charAt(0);
        }
    }
    public void printDrawingSelection() {
        System.out.println("Please select an option. Type 4 to exit.\n" +
                "1. Draw triangles\n" +
                "2. Draw rectangles\n" +
                "3. Update drawing canvas settings\n" +
                "4. Exit");
    }

    public void getCurrentCanvasDetail() {
        System.out.println("Current drawing canvas settings:");
        System.out.println("- Width: " + canvasWidth);
        System.out.println("- Height: " + canvasHeight);
        System.out.println("- Background character: " + backGroundChar);
    }

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