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

public class DrawingCanvas {
    //Use for combine duplicate print method like getLength
    private static final String[][] PRINT_STRINGS = {{"triangle", "rectangle"},
                                               {"height", "width"},
                                               {"large", "long"},
                                               {"Side length", "side length"}};

    private int canvasWidth;
    private int canvasHeight;
    private char backGroundChar;

    private char[][] fileBitmap;
    private char[][] canvasBitmap;

    private ArrayList<Triangle> triangleList;


    public DrawingCanvas(int canvasWidth, int canvasHeight, char backGroundChar, char[][] fileBitmap) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.backGroundChar = backGroundChar;
        this.fileBitmap = fileBitmap;

        triangleList = new ArrayList<>();
        canvasBitmap = new char[canvasHeight][canvasWidth];
        canvasInit();

    }

    /**
     * Distinguish the options
     * @param drawingOptions number of the option
     * @param sc only one scanner
     */
    public void drawOptions(int drawingOptions, Scanner sc) {
        switch (drawingOptions) {
            case 1:
                drawPredefinedObject(sc);
                break;
            case 2:
                updateCanvas(sc);
                drawFreestyleDrawing(sc);
                break;
        }
    }

    /**
     * TODO: The process to draw triangles, first to get the side length of triangle using getTrisideLength method,
     *       then create a triangle object and pass into the printTriangle method. Last pass the triangle into the
     *       zoomOrMoving method.
     * @param sc
     */
    public void drawTriangle(Scanner sc) {
        int triSideLength = getTriSideLength(sc);

        System.out.println("Printing character:");
        char printingChar = sc.nextLine().charAt(0);

        Triangle triangle = new Triangle(triSideLength, printingChar);
        triangleList.add(triangle);
        //这个地方需要重新打印所有的三角形
        for (int i = 0; i < triangleList.size(); i++) {
            triangleList.get(i).printToBitMap(canvasBitmap);
        }
        showBitmap(canvasBitmap);
        triangle.zoomMoveOrRotate(canvasWidth, canvasHeight, backGroundChar, sc, triangleList, canvasBitmap);
    }

    /**
     * TODO: Update the canvas attributes with correctness check.
     * @param sc
     */
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
    }
    /**
     * TODO: simply print the canvas details part.
     */
    public void printCurrentCanvasDetails() {
        System.out.println("Current drawing canvas settings:");
        System.out.println("- Width: " + canvasWidth);
        System.out.println("- Height: " + canvasHeight);
        System.out.println("- Background character: " + backGroundChar);
        System.out.println();
    }

    /**
     * TODO: Get the triangle length with the correctness check.
     * @param sc
     * @return the user input side length of a triangle.
     */
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
        //notice that the triangle only need to be smaller than the smaller value of Canvas width and height.
        int minSide = Math.min(canvasWidth, canvasHeight);
        while (sideLength <= 0 || (sideLength > minSide)) {
            if (sideLength <= 0) {
                System.out.println("Error! The side length can not be 0 or negative");
            }
            if (sideLength > canvasWidth || sideLength > canvasHeight) {
                System.out.println("Error! The side length is too long (Current canvas size is "
                        + canvasWidth + "x" + canvasHeight + "). Please try again.");
            }
            System.out.println("Side length:");
            sideLength = Integer.parseInt(sc.nextLine());
        }
        return sideLength;
    }

    /**
     * Get drawing options from user input, has error fixing method
     * @param sc
     * @return a new and right drawing option
     */
    public int getDrawingOption(Scanner sc, int numOption, String choice) {
        int drawingOptions = 0;
        //try to get drawing options, catching the NumberFormat exception to prevent user input is not a number
        while (true) {
            try {
                drawingOptions = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
//                e.printStackTrace();
                System.out.println("Invalid input!");
                printCurrentCanvasDetails();
            }
        }
        //not in the correct field
        while (drawingOptions > numOption || drawingOptions < 1) {
            System.out.println("Unsupported option. Please try again!");
            printDrawingSelection(choice);
            //another time
            while (true) {
                try {
                    drawingOptions = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
//                e.printStackTrace();
                    System.out.println("Invalid input!");
                    printCurrentCanvasDetails();
                }
            }
        }
        return drawingOptions;
    }

    /**
     * Decide whether user need to draw another one sphere
     * @param drawAnotherOne default is Y for the first time for drawing the first sphere.
     * @param drawingOptions from the top
     */

    public void drawAnotherOne(char drawAnotherOne, int drawingOptions, Scanner sc) {
        while (drawAnotherOne == 'Y') {
            drawOptions(drawingOptions, sc);
            //differentiate the triangle or rectangle
            if (drawingOptions == 1) {
                System.out.println("Draw another triangle (Y/N)?");
            } else if (drawingOptions == 2) {
                System.out.println("Draw another rectangle (Y/N)?");
                //future updates
            } else if (drawingOptions == 3){
                break;
            }
            drawAnotherOne = sc.nextLine().toUpperCase().charAt(0);
            while (drawAnotherOne != 'Y' && drawAnotherOne != 'N') {
                System.out.println("Unsupported option. Please try again!");
                System.out.println("Draw another triangle (Y/N)?");
                drawAnotherOne = sc.nextLine().toUpperCase().charAt(0);
            }
        }
    }
     public void drawPredefinedObject(Scanner sc) {

        boolean startWhile = true;
        while (startWhile) {
            printDrawingSelection("P");
            int option = getDrawingOption(sc, 4, "P");
            switch (option) {
                case 1:
                    System.out.println("This is your task. Just try to draw the same object. Enjoy!");
                    showBitmap(fileBitmap);
                    break;
                case 2:
                    printCanvas();
                    printTriangleSelection(sc);
                    break;
                case 3:

                    break;
                case 4:
                    startWhile = false;
                    break;
            }
        }

     }

     public void drawFreestyleDrawing(Scanner sc) {
         boolean startWhile = true;
         while (startWhile) {
             printDrawingSelection("F");
             int option = getDrawingOption(sc, 3, "F");
             switch (option) {
                 case 1:
                     printCanvas();
                     printTriangleSelection(sc);
                     break;
                 case 2:
                     break;
                 case 3:
                     startWhile = false;
                     break;
             }
         }

     }

     public void printCanvas() {
         for (int i = 0; i < canvasHeight; i++) {
             for (int j = 0; j < canvasWidth; j++) {
                 System.out.print(backGroundChar);
             }
             System.out.println();
          }
     }
    public void printTriangleSelection(Scanner sc) {
        boolean startWhile = true;
        while (startWhile) {
            printDrawingSelection("P1");
            int option = getDrawingOption(sc, 4, "P1");
            switch (option) {
                case 1:
                    drawTriangle(sc);
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    startWhile = false;
                    break;
            }
        }

    }
    public void showBitmap(char[][] bitmap) {
        for (int i = 0; i < bitmap.length; i++) {
            for (int j = 0; j < bitmap[0].length; j++) {
                System.out.print(bitmap[i][j]);
            }
            System.out.println();
        }
    }

    public void canvasInit() {
        for (int i = 0; i < canvasHeight; i++) {
            for (int j = 0; j < canvasWidth; j++) {
                canvasBitmap[i][j] = backGroundChar;
            }
        }
    }

    /**
     * TODO: simply print the selection part.
     */
    public void printDrawingSelection(String choice) {
        switch (choice) {
            case "1":
                System.out.println("Please select an option. Type 3 to exit.\n" +
                        "1. Draw a predefined object\n" +
                        "2. Freestyle Drawing\n" +
                        "3. Exit");
                break;
            case "P":
                System.out.println("Please select an option. Type 4 to go back to the main menu.\n" +
                        "1. Preview the sample drawing\n" +
                        "2. Start/edit the current canvas\n" +
                        "3. Check result\n" +
                        "4. Go back to the main menu");
                break;
            case "F" :
                System.out.println("Please select an option. Type 3 to go back to the main menu.\n" +
                        "1. Start/edit your current canvas\n" +
                        "2. Share your current drawing\n" +
                        "3. Go back to the main menu");
                break;
            case "P1" :
                System.out.println("Please select an option. Type 4 to go back to the previous menu.\n" +
                        "1. Add a new Triangle\n" +
                        "2. Edit a triangle\n" +
                        "3. Remove a triangle\n" +
                        "4. Go back");
                break;
        }

    }

    /**
     * Getters and Setters
     */
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

    public char[][] getFileBitmap() {
        return fileBitmap;
    }

    public void setFileBitmap(char[][] fileBitmap) {
        this.fileBitmap = fileBitmap;
    }

    public char[][] getCanvasBitmap() {
        return canvasBitmap;
    }

    public void setCanvasBitmap(char[][] canvasBitmap) {
        this.canvasBitmap = canvasBitmap;
    }

    public ArrayList<Triangle> getTriangleList() {
        return triangleList;
    }

    public void setTriangleList(ArrayList<Triangle> triangleList) {
        this.triangleList = triangleList;
    }


}