package Interview;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(1);
        arr.add(0);
        arr.add(-1);
        arr.add(-1);
        plusMinus(arr);
        timeConversion("06:40:03AM");
    }

    public static void plusMinus(List<Integer> arr) {
        // Write your code here
        int n = arr.size();
        int pos = 0, neg = 0, zero = 0;
        for (int num : arr) {
            if (num > 0) {
                pos++;
            } else if (num < 0){
                neg++;
            } else {
                zero++;
            }
        }
        double posR = (double) pos / n, negR = (double) neg / n, zeroR = (double) zero / n;
        System.out.printf("%.6f", posR);
        System.out.println();
        System.out.printf("%.6f", negR);
        System.out.println();
        System.out.printf("%.6f", zeroR);

    }

    public static String timeConversion(String s) {
        // Write your code here
        StringBuilder sb = new StringBuilder();
        String time = s.substring(0, 8);
        int hours = Integer.parseInt(time.substring(0,2));
        if (s.charAt(s.length() - 2) == 'P') {
            if (hours != 12) {
                hours += 12;
            }
        } else {
            if (hours - 12 == 0) {
                hours -= 12;
            }
        }
        sb.append(hours);
        if (hours < 10) {
            sb.append(0);
        }
        sb.append(time.substring(2));
        return sb.toString();
    }

}
