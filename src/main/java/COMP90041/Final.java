package COMP90041;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Final {
    public static void main(String[] args) {
        Base object = new Derived();
        object.methodOne();
    }
}
