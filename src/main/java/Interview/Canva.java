package Interview;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Canva {
    public static void main(String[] args) {

    }
    public static int getMaxDeletions(String s) {
        int x = 0, y = 0, count = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'R') {
                x++;
                count++;
            } else if (ch == 'L') {
                x--;
                count++;
            } else if (ch == 'D') {
                y++;
                count++;
            } else if (ch == 'U') {
                y--;
                count++;
            }
        }
        return count - Math.abs(x) - Math.abs(y);
    }

    public static int minimumSteps(List<String> loggedMoves) {
        int res = 0;
        for (String s : loggedMoves) {
            if (res != 0 && s.equals("../")) {
                res--;
            } else if (s.equals("./")) {
                continue;
            } else {
                res++;
            }
        }
        return res;
    }

    public static int getMinOperations(String sequence) {
        // Write your code here
        Deque<Character> stack = new LinkedList<>();
        stack.push(sequence.charAt(0));
        for (int i = 1; i < sequence.length(); i++) {
            char ch = sequence.charAt(i);
            if (!stack.isEmpty()) {
                if (stack.peek().equals('(') && ch == ')') {
                    stack.pop();
                } else {
                    stack.push(ch);
                }
            } else {
                stack.push(ch);
            }
        }
        return stack.size() / 2;
    }
}
