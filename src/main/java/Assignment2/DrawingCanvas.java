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
    private static final int PREDEFINED_OBJECT = 1,
            FREESTYLE = 2,
            PREVIEW_SAMPLE_DRAWING = 1,
            PREDEFINED_START_EDIT_CANVAS = 2,
            CHECK_RESULT = 3,
            PREDEFINED_GO_BACK = 4,
            FREESTYLE_START_EDIT_CANVAS = 1,
            FREESTYLE_SHARE_DRAWING = 2,
            FREESTYLE_GO_BACK = 3,
            ADD_NEW_TRIANGLE = 1,
            EDIT_TRIANGLE = 2,
            REMOVE_TRIANGLE = 3,
            GO_BACK = 4;

    private final char fileBGChar;
    private final char[][] fileBitmap;

    private int canvasWidth;
    private int canvasHeight;
    private char backGroundChar;
    private char[][] canvasBitmap;
    private ArrayList<Triangle> triangleList;


    public DrawingCanvas(int canvasWidth, int canvasHeight, char backGroundChar, char[][] fileBitmap) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.backGroundChar = backGroundChar;
        this.fileBitmap = fileBitmap;

        fileBGChar = backGroundChar;
        triangleList = new ArrayList<>();
        canvasBitmap = new char[canvasHeight][canvasWidth];
        clearCanvas();
    }

    /**
     * Distinguish the options, for predefined object, we need to take file bitmap as canvas width and height, and
     * initialize other attributes. For Freestyle, update canvas and triangle list then execute the corresponding code.
     * @param drawingOptions number of the option
     * @param sc only one scanner
     */
    public void drawOptions(int drawingOptions, Scanner sc) {
        switch (drawingOptions) {
            case PREDEFINED_OBJECT:
                //change canvas same as the file
                canvasWidth = fileBitmap[0].length;
                canvasHeight = fileBitmap.length;
                backGroundChar = fileBGChar;
                canvasBitmap = new char[canvasHeight][canvasWidth];
                //clear the canvas and triangle list
                triangleList.clear();
                clearCanvas();
                
                drawPredefinedObject(sc);
                break;
            case FREESTYLE:
                //change canvas into user defined canvas
                updateCanvas(sc);
                //clear the canvas and triangle list
                triangleList.clear();
                clearCanvas();
                
                drawFreestyleDrawing(sc);
                break;
        }
    }

    /**
     * The process to draw triangles, first to get the side length of triangle using getTrisideLength method,
     * then print all triangles again, Last pass the triangle into the zoomMoveOrRotate method.
     * @param sc
     */
    public void drawTriangle(Scanner sc) {
        int triSideLength = getTriSideLength(sc);

        System.out.println("Printing character:");
        char printingChar = sc.nextLine().charAt(0);

        Triangle triangle = new Triangle(triSideLength, printingChar);
        triangleList.add(triangle);

        //print all triangle
        printTriangle();

        printBitmap(canvasBitmap);
        triangle.zoomMoveOrRotate(canvasWidth, canvasHeight, backGroundChar, sc, triangleList, canvasBitmap);
    }

    /**
     * Update the canvas attributes with correctness check.
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
        canvasBitmap = new char[canvasHeight][canvasWidth];
        clearCanvas();
    }

    /**
     * TODO: Get the triangle length with the correctness check.
     * @param sc
     * @return the right user input side length of a triangle.
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
     * Get drawing options from user input
     * @param sc 
     * @param numOption the max number of options in total
     * @param choice to decide which drawing selection that need to print
     * @param isExclamatoryMark to decide whether end with exclamatory mark or not
     * @return the right drawing options
     */
    public int getDrawingOption(Scanner sc, int numOption, String choice, boolean isExclamatoryMark) {
        int drawingOptions = 0;
        //try to get drawing options, catching the NumberFormat exception to prevent user input is not a number
        while (true) {
            try {
                drawingOptions = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
//                e.printStackTrace();
                System.out.println("Invalid input!");
            }
        }
        //not in the correct field
        while (drawingOptions > numOption || drawingOptions < 1) {
            //
            if (isExclamatoryMark) {
                System.out.println("Unsupported option. Please try again!");
            }
            else  {
                System.out.println("Unsupported option. Please try again.");
            }

            printDrawingSelection(choice);
            //another time
            while (true) {
                try {
                    drawingOptions = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
//                e.printStackTrace();
                    System.out.println("Invalid input!");
                }
            }
        }
        return drawingOptions;
    }

    /**
     * draw predefined object, four cases in total.
     * @param sc
     */
     public void drawPredefinedObject(Scanner sc) {
        boolean startWhile = true;
        while (startWhile) {
            printDrawingSelection("Predefined");
            int option = getDrawingOption(sc, 4, "Predefined", false);
            switch (option) {
                case PREVIEW_SAMPLE_DRAWING:
                    System.out.println("This is your task. Just try to draw the same object. Enjoy!");
                    printBitmap(fileBitmap);
                    break;
                case PREDEFINED_START_EDIT_CANVAS:
                    printBitmap(canvasBitmap);
                    addEditOrRemove(sc);
                    break;
                case CHECK_RESULT:
                    checkResult();
                    break;
                case PREDEFINED_GO_BACK:
                    startWhile = false;
                    break;
            }
        }

     }

    /**
     * draw in free style, three cases in total.
     * @param sc
     */
     public void drawFreestyleDrawing(Scanner sc) {
         boolean startWhile = true;
         while (startWhile) {
             printDrawingSelection("Freestyle");
             int option = getDrawingOption(sc, 3, "Freestyle", false);
             switch (option) {
                 case FREESTYLE_START_EDIT_CANVAS:
                     printBitmap(canvasBitmap);
                     addEditOrRemove(sc);
                     break;
                 case FREESTYLE_SHARE_DRAWING:
                     shareResult();
                     break;
                 case FREESTYLE_GO_BACK:
                     startWhile = false;
                     break;
             }
         }

     }

    /**
     *
     * @param sc
     */
     public void addEditOrRemove(Scanner sc) {
        boolean startWhile = true;
        while (startWhile) {
            printDrawingSelection("Triangle");
            int option = getDrawingOption(sc, 4, "Triangle", false);
            switch (option) {
                case ADD_NEW_TRIANGLE:
                    drawTriangle(sc);
                    break;
                case EDIT_TRIANGLE:
                    editTriangle(sc);
                    break;
                case REMOVE_TRIANGLE:
                    removeTriangle(sc);
                    break;
                case GO_BACK:
                    startWhile = false;
                    break;
            }
        }

    }

    /**
     *
     * @param sc
     */
    public void editTriangle(Scanner sc) {
        if (triangleList.isEmpty()) {
            System.out.println("The current canvas is clean; there are no shapes to edit!");
            printBitmap(canvasBitmap);
        } else {
            int shapeIndex = getTriangleIndex(sc, false);
            if (shapeIndex == -1) {
                printBitmap(canvasBitmap);
                return;
            }
            //edit the triangle
            printBitmap(canvasBitmap);
            triangleList.get(shapeIndex).zoomMoveOrRotate(canvasWidth, canvasHeight, backGroundChar, sc, triangleList, canvasBitmap);
        }
    }

    /**
     *
     * @param sc
     */
    public void removeTriangle(Scanner sc) {
        if (triangleList.isEmpty()) {
            System.out.println("The current canvas is clean; there are no shapes to remove!");
            printBitmap(canvasBitmap);
        } else {
            int shapeIndex = getTriangleIndex(sc, true);
            if (shapeIndex == -1) {
                printBitmap(canvasBitmap);
                return;
            }
            //remove the triangle
            triangleList.remove(shapeIndex);
            printTriangle();
            printBitmap(canvasBitmap);
        }
    }

    /**
     * get the right index of the triangle which is going to be removed/edited.
     * @param sc
     * @param isRemove to define whether remove triangle or edit triangle
     * @return the right index of triangle list, if the user input is bigger than list or smaller than 0,
     *         it will return -1
     */
    public int getTriangleIndex(Scanner sc, Boolean isRemove) {
        for (int i = 0; i < triangleList.size(); i++) {
            System.out.println("Shape #" + (i + 1) +
                    " - Triangle: xPos = " + triangleList.get(i).getStartPrintPointX() +
                    ", yPos = "+ triangleList.get(i).getStartPrintPointY() +
                    ", tChar = " + triangleList.get(i).getPrintingChar());
        }

        int shapeIndex = -1;
        while (true) {
            try {
                if (isRemove) {
                    System.out.println("Index of the shape to remove:");
                } else {
                    System.out.println("Index of the shape:");
                }
                shapeIndex = Integer.parseInt(sc.nextLine()) - 1;
                break;
            } catch (NumberFormatException e) {
//                e.printStackTrace();
                System.out.println("Invalid input!");
            }
        }
        if (shapeIndex >= triangleList.size() || shapeIndex < 0) {
            System.out.println("The shape index cannot be larger than the number of shapes!");
            shapeIndex = -1;
        }
        return shapeIndex;
    }

    /**
     * check whether the current canvas drawing is same with the file.
     */
    public void checkResult() {
        boolean isCorrect = true;
        while (isCorrect) {
            for (int i = 0; i < canvasHeight; i++) {
                for (int j = 0; j < canvasWidth; j++) {
                    if (fileBitmap[i][j] != canvasBitmap[i][j]) {
                        isCorrect = false;
                        break;
                    }
                }
            }
            break;
        }
        if (isCorrect) {
            System.out.println("You successfully complete the drawing task. Congratulations!!");
        } else {
            System.out.println("Not quite! Please edit your canvas and check the result again.");
        }
        System.out.println("This is the sample drawing:");
        printBitmap(fileBitmap);
        System.out.println("And this is your drawing:");
        printBitmap(canvasBitmap);
    }

    /**
     * print the triangles into the canvas bitmap in the order that they have been added.
     */
    public void printTriangle() {
        clearCanvas();
        for (int i = 0; i < triangleList.size(); i++) {
            triangleList.get(i).printToBitMap(canvasBitmap);
        }
    }

    public void shareResult() {
        System.out.println("This is the detail of your current drawing");
        System.out.println(canvasHeight + "," + canvasWidth + "," + backGroundChar);
        for (int i = 0; i < canvasBitmap.length; i++) {
            for (int j = 0; j < canvasBitmap[0].length; j++) {
                System.out.print(canvasBitmap[i][j]);
                if (j < canvasBitmap[0].length - 1) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }
    }

    /**
     * Print a bitmap into console.
     * @param bitmap the bitmap that need to be printed
     */
    public void printBitmap(char[][] bitmap) {
        for (int i = 0; i < bitmap.length; i++) {
            for (int j = 0; j < bitmap[0].length; j++) {
                System.out.print(bitmap[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Clear the canvas, fill the canvas with the current background character.
     */
    public void clearCanvas() {
        for (int i = 0; i < canvasHeight; i++) {
            for (int j = 0; j < canvasWidth; j++) {
                canvasBitmap[i][j] = backGroundChar;
            }
        }
    }

    /**
     * print the selection words into console.
     * @param choice
     */
    public void printDrawingSelection(String choice) {
        switch (choice) {
            case "Main Menu":
                System.out.println("Please select an option. Type 3 to exit.\n" +
                        "1. Draw a predefined object\n" +
                        "2. Freestyle Drawing\n" +
                        "3. Exit");
                break;
            case "Predefined":
                System.out.println("Please select an option. Type 4 to go back to the main menu.\n" +
                        "1. Preview the sample drawing\n" +
                        "2. Start/edit the current canvas\n" +
                        "3. Check result\n" +
                        "4. Go back to the main menu");
                break;
            case "Freestyle":
                System.out.println("Please select an option. Type 3 to go back to the main menu.\n" +
                        "1. Start/edit your current canvas\n" +
                        "2. Share your current drawing\n" +
                        "3. Go back to the main menu");
                break;
            case "Triangle":
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