package AlgoAssignment2;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Problem1 {
    public static void main(String[] args) {
        String[] a = {"abc", "bc", "d"};
        String[] b = {"abc", "d", "efg"};
        int n = 3;

        double res = solution(a, b, n);
        System.out.println(res);
    }
    public static double solution (String[] a, String[] b, int n) {
        HashMap<String, Integer> map = new HashMap<>();

        /**
         * getOrDefault在key不存在时,返回一个defaultValue。在没有该方法前需要这样写：
         *
         *     Integer bbValue = hashMap.containsKey("bb")?hashMap.get("bb"):-1;
         *
         * 有了getOrDefault可以这样写：
         *
         *     getOrDefault("aa",-1)//key=aa不存在，所以返回默认value -1
         */
        for (int i = 0; i < n; i++) {
            map.put(a[i], map.getOrDefault(a[i],0) + 1);
            map.put(b[i], map.getOrDefault(b[i],0) + 1);
        }

        int cnt = 0;

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            System.out.println(key+"="+value);

            if (value >= 2) {
                cnt++;
            }
        }

        return (double) cnt / map.size();
    }
}
