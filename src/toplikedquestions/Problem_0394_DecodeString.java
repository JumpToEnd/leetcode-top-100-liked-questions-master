package toplikedquestions;

/**
 * 括号优先级的递归套路
 */
public class Problem_0394_DecodeString {

    public static String decodeString(String s) {
        char[] str = s.toCharArray();
        return process(str, 0).ans;
    }

    public static class Info {
        // 结果
        public String ans;
        // 处理到了什么位置
        public int end;

        public Info(String a, int e) {
            ans = a;
            end = e;
        }
    }

    // s[i....]  何时停？遇到   ']'  或者遇到 s的终止位置，停止
    // 返回Info
    public static Info process(char[] s, int i) {
        StringBuilder ans = new StringBuilder();
        int times = 0;
        while (i < s.length && s[i] != ']') {
            if ((s[i] >= 'a' && s[i] <= 'z') || (s[i] >= 'A' && s[i] <= 'Z')) {
                ans.append(s[i++]);
            } else if (s[i] >= '0' && s[i] <= '9') {
                // 计算频率值，考虑可能有多位数字
                times = times * 10 + s[i++] - '0';
            }
            // 遇到了'['，交给子过程去计算
            // 计算完取得 end + 1，继续再进行计算
            else { // str[index] = '['
                Info next = process(s, i + 1);
                ans.append(timesString(times, next.ans));
                times = 0;
                i = next.end + 1;
            }
        }
        return new Info(ans.toString(), i);
    }

    public static String timesString(int times, String str) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < times; i++) {
            ans.append(str);
        }
        return ans.toString();
    }

}
