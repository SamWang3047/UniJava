package Interview;

import week4.Person;

import java.util.*;

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
        List<String> list = new ArrayList<>();
        list.add("kc");
        list.add("iu");
        gridChallenge(list);
        superDigit("9857", 4);
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);list1.add(2);list1.add(5);list1.add(3);list1.add(7);list1.add(8);list1.add(6);list1.add(4);
        minimumBribes(list1);
    }

    public static void plusMinus(List<Integer> arr) {
        // Write your code here
        int n = arr.size();
        int pos = 0, neg = 0, zero = 0;
        for (int num : arr) {
            if (num > 0) {
                pos++;
            } else if (num < 0) {
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
        int hours = Integer.parseInt(time.substring(0, 2));
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

    public static int findMedian(List<Integer> arr) {
        // Write your code here
        Collections.sort(arr);
        int res = 0;
        int n = arr.size();
        if (n % 2 == 0) {
            res = arr.get(n / 2);
            res += arr.get((n + 1) / 2);
            res /= 2;
        } else {
            res = arr.get(n / 2);
        }
        return res;
    }

    public static int lonelyinteger(List<Integer> a) {
        // Write your code here
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.size(); i++) {
            int num = a.get(i);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public static int diagonalDifference(List<List<Integer>> arr) {
        // Write your code here
        int left = 0;
        int right = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.get(0).size(); j++) {
                if (i == j) {
                    left += arr.get(i).get(j);
                }
                if (j == arr.size() - i - 1) {
                    right += arr.get(i).get(j);
                }
            }
        }
        return Math.abs(left - right);
    }

    public static List<Integer> countingSort(List<Integer> arr) {
        // Write your code here
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.size(); i++) {
            map.put(arr.get(i), map.getOrDefault(arr.get(i), 0) + 1);
        }
        for (int i = 0; i < 100; i++) {
            if (map.get(i) == null) {
                res.add(0);
            } else {
                res.add(map.get(i));
            }
        }
        return res;
    }

    public static int flippingMatrix(List<List<Integer>> matrix) {
        // Write your code here
        int length = matrix.size();
        int size = length / 2;
        int max = 0;
        int total = 0;
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                max = Integer.MIN_VALUE;
                if (matrix.get(row).get(column) > max)
                    max = matrix.get(row).get(column);
                if (matrix.get(row).get(length - column - 1) > max)
                    max = matrix.get(row).get(length - column - 1);
                if (matrix.get(length - row - 1).get(column) > max)
                    max = matrix.get(length - row - 1).get(column);
                if (matrix.get (length - row - 1).get(length - column - 1) > max)
                    max = matrix.get (length - row - 1).get(length - column - 1);
                total += max;
            }
        }
        return total;
    }

    public static int towerBreakers(int n, int m) {
        // Write your code here
        if (n % 2 == 1 && m != 1) {
            return 1;
        }

        return 2;
    }

    public static String caesarCipher(String s, int k) {
        // Write your code here
        int a = 'a';
        int z = 'z';
        int A = 'A';
        int Z = 'Z';
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            if (ch >= a && ch <= z) {
                ch += k % 26;
                if (ch >= a && ch <= z) {
                    char chara = (char) ch;
                    sb.append(chara);
                } else {
                    char chara = (char) (ch - 26);
                    sb.append(chara);
                }
            } else if (ch >= A && ch <= Z) {
                ch += k % 26;
                if (ch >= A && ch <= Z) {
                    char chara = (char) ch;
                    sb.append(chara);
                } else {
                    char chara = (char) (ch - 26);
                    sb.append(chara);
                }
            } else {
                sb.append((char) ch);
            }
        }
        return sb.toString();
    }
    public static int palindromeIndex(String s) {
        // Write your code here
        int start = 0;
        int end = s.length() - 1;
        for (; start < s.length() / 2; start++, end--) {
            if (s.charAt(start) != s.charAt(end)) {
                break;
            }
        }

        if (start == s.length() / 2) {
            return -1;
        } else if (isPalindrome(start, end - 1, s)) {
            return end;
        } else if (isPalindrome(start + 1, end, s)) {
            return start;
        } else {
            return -1;
        }
    }

    private static boolean isPalindrome(int start, int end, String s) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return  false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static String gridChallenge(List<String> grid) {
        List<String> sortGrid = new ArrayList<>();
        String y = "YES", n = "NO";
        // Write your code here
        if (grid.size() <= 1) {
            return y;
        }
        for (int i = 0; i < grid.size(); i++) {
            char[] temp = grid.get(i).toCharArray();
            Arrays.sort(temp);
            sortGrid.add(new String(temp));
        }
        for (int i = grid.size() - 1; i > 0; --i) {
            for (int j = 0; j < grid.get(0).length(); j++) {
                if (grid.get(i).charAt(j) < grid.get(i - 1).charAt(j)) {
                    return n;
                }
            }
        }
        return y;
    }
    public static int superDigit(String n, int k) {
        // Write your code here
        long sum = 0;
        for (int i = 0; i < n.length(); i++) {
            sum += n.charAt(i) - '0';
        }
        sum *= k;
        while (sum > 9) {
            sum = count(sum);
        }
        return (int) sum;
    }
    private static long count(long num) {
        int res = 0;
        while (num != 0) {
            res += num % 10;
            num /= 10;
        }
        return res;
    }
    public static void minimumBribes(List<Integer> q) {
        // Write your code here
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        boolean flag = false;
        for (int i = 0; i < q.size() - 1; i++) {
            if (flag) i--;
            flag = false;
            int first = q.get(i);
            for (int j = i + 1; j < q.size(); j++) {
                int second = q.get(j);
                if (first > second) {
                    map.put(first, map.getOrDefault(first,0) + 1);
                    if (map.get(first) > 2) {
                        System.out.println("Too chaotic");
                        return;
                    }
                    swap(q, i, j);
                    res++;
                    flag = true;
                    break;
                }
            }
        }
        System.out.println(res);
    }
    private static void swap(List<Integer> q, int i, int j) {
        int temp = q.get(j);
        q.set(j, q.get(i));
        q.set(i, temp);
    }
}
