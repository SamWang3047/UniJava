package COMP90041.Assignment2;

public class test {
    public static void main(String[] args) {
        int canvasHeight = 6;
        int canvasWidth = 10;
        int sideLength = 8;
        int startPrintPointX = 0;
        int startPrintPointY = 0;
        char backGroundChar = '-';
        char printingChar = '*';

        int sideWidth = 5;
        int sideHeight = 3;

        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength - i; j++) {
                System.out.print("-");
            }
            System.out.println();
        }



    }
}
