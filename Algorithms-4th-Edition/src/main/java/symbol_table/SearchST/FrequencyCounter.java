package symbol_table.SearchST;

import edu.princeton.cs.algs4.StdOut;
import symbol_table.tools.VisualAccumulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FrequencyCounter {

    // 不要实例化这个类。
    private FrequencyCounter() {
    }

    /**
     * 从文件输入流读取数据
     * 打印出长度超过阈值且出现频率最高的单词。
     * 同时打印出长度超过阈值的单词总数和不同单词的数量。
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int minlen = Integer.parseInt(args[0]); // 最小单词长度作为第一个参数
        String fileName = args[1]; // 文件路径作为第二个参数
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        VisualAccumulator va = st.getVisualAccumulator();

        // 从文件中读取数据
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()) {
                String key = scanner.next();
                if (key.length() < minlen) continue;  // 忽略长度小于阈值的单词
                words++;
                if (st.contains(key)) {
                    st.put(key, st.get(key) + 1);
                } else {
                    st.put(key, 1);
                    distinct++;
                }
            }
        } catch (FileNotFoundException e) {
            StdOut.println("文件未找到: " + fileName);
            return;
        }

        // 找到出现频率最高的单词
        String max = "";
        st.put(max, 0);

        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) max = word;
        }

        StdOut.println(max + " " + st.get(max));
        StdOut.println("不同的单词数量 = " + distinct);
        StdOut.println("总单词数量 = " + words);

        // 打印最后一个红点的值(平均值)
        if (va != null) {
            System.out.println("最后一个平均值: " + va.toString());
        }
    }
}
