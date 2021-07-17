import java.util.*;

/**
 * 面试题 10.02. 变位词组
 * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
 *
 * 注意：本题相对原题稍作修改
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 *
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 */

public class GroupAnagrams {
    public static void main(String[] args) {

    }

    static List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();

        for (String s: strs) {
            // 对字符串排序，找出key
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);

            // 拥有相同字母的(key相同)，放入map中同一个key指向的List
            List<String> list = map.get(key);
            if (list == null) {
                list = new ArrayList<>();
                list.add(s);
                map.put(key, list);
                result.add(list); // 创建List的同时，也放入需要返回的List中
            } else {
                list.add(s);
            }
        }

        return result;
    }

    static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Set<String> keys = new HashSet<>();
        Map<String, List<String>> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        for (String s: strs) {
            // 归类：最大堆或最小堆来归类拥有相同字母的字符串
            PriorityQueue<Character> queue = new PriorityQueue<>(new Comparator<Character>() {
                @Override
                public int compare(Character o1, Character o2) {
                    return o1 - o2;
                }
            });

            for (int i=0; i<s.length(); i++) {
                char c = s.charAt(i);
                queue.add(c);
            }

            sb.delete(0, sb.length());
            while (queue.peek() != null) {
                sb.append(queue.poll());
            }
            // 使用归类的字符串作为key，来标记拥有相同字母的所有String
            String key = sb.toString();
            keys.add(sb.toString());

            List<String> list = map.get(key);
            if (list == null) {
                list = new ArrayList<>();
                list.add(s);
                map.put(key, list);
                result.add(list); // 创建List的同时，也放入需要返回的List中
            } else {
                list.add(s);
            }
        }

        return result;
    }
}
