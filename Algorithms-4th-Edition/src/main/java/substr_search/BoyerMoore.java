package substr_search;

import edu.princeton.cs.algs4.StdOut;

public class BoyerMoore {
    private final int R;     // 基数
    private int[] right;     // 坏字符跳转数组

    private char[] pattern;  // 将模式存储为字符数组
    private String pat;      // 或作为字符串

    /**
     * 预处理模式字符串。
     *
     * @param pat 模式字符串
     */
    public BoyerMoore(String pat) {
        this.R = 256;
        this.pat = pat;

        // c在模式中最右出现的位置
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < pat.length(); j++)
            right[pat.charAt(j)] = j;
    }

    /**
     * 预处理模式字符串。
     *
     * @param pattern 模式字符串
     * @param R 字母表大小
     */
    public BoyerMoore(char[] pattern, int R) {
        this.R = R;
        this.pattern = new char[pattern.length];
        for (int j = 0; j < pattern.length; j++)
            this.pattern[j] = pattern[j];

        // c在模式中最右出现的位置
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < pattern.length; j++)
            right[pattern[j]] = j;
    }

    /**
     * 返回模式字符串在文本字符串中第一次出现的索引。
     *
     * @param  txt 文本字符串
     * @return 模式字符串在文本字符串中第一次出现的索引；
     *         如果没有匹配，则为n
     */
    public int search(String txt) {
        int m = pat.length();
        int n = txt.length();
        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m-1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i+j)) {
                    skip = Math.max(1, j - right[txt.charAt(i+j)]);
                    break;
                }
            }
            if (skip == 0) return i;    // 找到
        }
        return n;                       // 未找到
    }


    /**
     * 返回模式字符串在文本字符串中第一次出现的索引。
     *
     * @param  text 文本字符串
     * @return 模式字符串在文本字符串中第一次出现的索引；
     *         如果没有匹配，则为n
     */
    public int search(char[] text) {
        int m = pattern.length;
        int n = text.length;
        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m-1; j >= 0; j--) {
                if (pattern[j] != text[i+j]) {
                    skip = Math.max(1, j - right[text[i+j]]);
                    break;
                }
            }
            if (skip == 0) return i;    // 找到
        }
        return n;                       // 未找到
    }


    /**
     * 作为命令行参数接受一个模式字符串和一个输入字符串；
     * 在文本字符串中搜索模式字符串；并打印
     * 模式字符串在文本字符串中的第一次出现。
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {

        // 示例:
        String pat = "abracadabra";
        String txt = "abacadabrabracabracadabrabrabracad";

        char[] pattern = pat.toCharArray();
        char[] text    = txt.toCharArray();

        BoyerMoore boyermoore1 = new BoyerMoore(pat);
        BoyerMoore boyermoore2 = new BoyerMoore(pattern, 256);
        int offset1 = boyermoore1.search(txt);
        int offset2 = boyermoore2.search(text);

        // 打印结果
        StdOut.println("text:    " + txt);

        StdOut.print("pattern: ");
        for (int i = 0; i < offset1; i++)
            StdOut.print(" ");
        StdOut.println(pat);

        StdOut.print("pattern: ");
        for (int i = 0; i < offset2; i++)
            StdOut.print(" ");
        StdOut.println(pat);
    }
}
