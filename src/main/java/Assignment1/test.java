package Assignment1;

public class test {
    public static void main(String[] args) {
        int canvasHeight = 6;
        int canvasWidth = 10;
        int startPrintPointX = 0;
        int startPrintPointY = 0;
        char backGroundChar = '-';
        char printingChar = '*';

        int sideWidth = 5;
        int sideHeight = 3;

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
}
