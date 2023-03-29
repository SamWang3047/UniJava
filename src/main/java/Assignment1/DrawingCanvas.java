package Assignment1;

import java.util.Scanner;

public class DrawingCanvas {
    private static final String[][] DEFAULT = {{"triangle", "rectangle"},
                                               {"height", "width"},
                                               {"large", "long"},
                                               {"Side length", "side length"}};
    private int canvasWidth;
    private int canvasHeight;
    private char backGroundChar;

    public DrawingCanvas(String[] args) {
        canvasWidth = Integer.parseInt(args[0]);
        canvasHeight = Integer.parseInt(args[1]);
        backGroundChar = args[2].charAt(0);
    }
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
    public void drawTriangle(Scanner sc) {
        int triSideLength = getTriSideLength(sc);

        System.out.println("Printing character:");
        char printingChar = sc.nextLine().charAt(0);

        Triangle triangle = new Triangle(triSideLength, printingChar);
        triangle.printTriangle(canvasWidth, canvasHeight, backGroundChar);

        zoomOrMoving(triangle, sc);
    }
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
    public void printDrawingSelection() {
        System.out.println("Please select an option. Type 4 to exit.\n" +
                "1. Draw triangles\n" +
                "2. Draw rectangles\n" +
                "3. Update drawing canvas settings\n" +
                "4. Exit");
    }

    public void printCurrentCanvasDetails() {
        System.out.println("Current drawing canvas settings:");
        System.out.println("- Width: " + canvasWidth);
        System.out.println("- Height: " + canvasHeight);
        System.out.println("- Background character: " + backGroundChar);
        System.out.println();
    }

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
        int minSide = Math.min(canvasWidth, canvasHeight);
        while (sideLength <= 0 || (sideLength > minSide)) {
            if (sideLength <= 0) {
                System.out.println("Error! The side length can not be 0 or negative");
            }
            if (sideLength > canvasWidth || sideLength >canvasHeight) {
                System.out.println("Error! The side length is too long (Current canvas size is "
                        + canvasWidth + "x" + canvasHeight + "). Please try again.");
            }
            System.out.println("Side length:");
            sideLength = Integer.parseInt(sc.nextLine());
        }
        return sideLength;
    }

    public int getRectangleSide(Scanner sc, int isWidth) {
        int recSideLen = 0;
        while (true) {
            try {
                System.out.println(DEFAULT[1][isWidth] + ":");
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
                System.out.println("Error! The " + DEFAULT[1][isWidth] + " can not be 0 or negative");
            }
            if (recSideLen > canvasSide) {
                System.out.println("Error! The " + DEFAULT[1][isWidth] + " is too large (Current canvas size is "
                        + canvasWidth + "x" + canvasHeight + "). Please try again.");
            }
            System.out.println(DEFAULT[1][isWidth] + ":");
            recSideLen = Integer.parseInt(sc.nextLine());
        }
        return recSideLen;
    }

    public void zoom(Triangle triangle, Scanner sc) {
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
        triangle.printTriangle(canvasWidth,canvasHeight, backGroundChar);
    }
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

    public void zoomOrMoving(Triangle triangle, Scanner sc) {
        System.out.println("Type Z/M for zooming/moving. Use other keys to quit the Zooming/Moving mode.");
        char zoomingOrMoving = sc.nextLine().toUpperCase().charAt(0);
        while (zoomingOrMoving == 'Z' || zoomingOrMoving == 'M') {
            triangle.printTriangle(canvasWidth, canvasHeight, backGroundChar);
            if (zoomingOrMoving == 'Z') {
                zoom(triangle, sc);
            } else {
                move(triangle, sc);
            }
            System.out.println("Type Z/M for zooming/moving. Use other keys to quit the Zooming/Moving mode.");
            zoomingOrMoving = sc.nextLine().toUpperCase().charAt(0);
        }
    }
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