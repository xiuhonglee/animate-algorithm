package symbol_table.search_st;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestSequentialSearchST {
    // 不允许实例化此类。
    private TestSequentialSearchST() { }

    /**
     * 打印出最频繁出现的长度超过阈值的单词，以及长度超过阈值的单词数量和不同单词数量。
     * @param args 命令行参数
     */
    public static void main(String[] args) {

        int distinct = 0, words = 0;
        int minlen = Integer.parseInt(args[0]);
        String fileName = args[1]; // 文件路径作为第二个参数
        ST<String, Integer> st = new ST<String, Integer>();

        // 从文件中读取数据
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()) { // 检查是否有更多数据
                String key = scanner.next();
                if (key.length() < minlen) continue;
                words++;
                if (st.contains(key)) {
                    st.put(key, st.get(key) + 1);
                }
                else {
                    st.put(key, 1);
                    distinct++;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("文件未找到: " + fileName);
            return;
        }

        // 寻找出现频率最高的单词
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }

        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
    }
}
