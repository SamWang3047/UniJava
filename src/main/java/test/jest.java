package test;

public class jest {

        public static void main (String[] args) {
            String s = "01000111";
            int res = 0;
            int zeros = 0, ones = 0;
            int left = -1, right = 0;

            while (right < s.length() - 1) {
                char c = s.charAt(right);
                char c1 = s.charAt(right + 1);
                if (c == '0') {
                    zeros++;
                } else {
                    ones++;
                }
                if (c1 == '0' && c == '1') {
                    int oneCnt = 0;
                    int tempcnt = right + 1;
                    char temp = s.charAt(tempcnt);
                    while(temp == '1') {
                        oneCnt++;
                        tempcnt++;
                        temp = s.charAt(tempcnt);
                    }
                    if (zeros == oneCnt) {
                        res = Math.max(res, Math.min(zeros, oneCnt) * 2);
                    }
                    zeros = 0;
                    ones = 0;
                }
                right++;
            }

        }
}
