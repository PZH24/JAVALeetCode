package easymodule;
import java.util.LinkedList;
import java.util.List;
/**
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，
 * 我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 *
 *  示例:
 * 输入: S = "a1b2"
 * 输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
 * @author pzh
 * @date 2020-06-15
 */
public class LetterCasePermutation {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.letterCasePermutation("a1b2"));

    }
    static class Solution {
        public List<String> letterCasePermutation(String S) {
            //大小写判断 (s.charAt(i)<='Z' && s.charAt(i)>='A')||(s.charAt(i)<='z'&&s.charAt(i)>='a')

                char[] s = S.toCharArray();
                List<String> list = new LinkedList<>();
                talGo(s, 0, list);
                return list;
            }
            private static void talGo(char[] s, int i, List<String> list) {
                if (i == s.length) {
                    list.add(new String(s));
                    return;
                }
                talGo(s, i+1, list);
                if (s[i] >= '0' && s[i] <= '9') {
                    return;
                }
                //相互转换。大写转小写，小写转大写
                if (s[i] >= 'a' && s[i] <= 'z') {
                    //变大写
                    s[i] = (char)(s[i] - 'a' + 'A');
                } else {
                    //变小写
                    s[i] = (char)(s[i] - 'A' + 'a');
                }
                talGo(s, i+1, list);
            }
    }

}
