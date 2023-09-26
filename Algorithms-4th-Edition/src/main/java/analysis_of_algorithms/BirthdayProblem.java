package analysis_of_algorithms;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashSet;


/**
 * 对应书中习题 1.4.44 Birthday problem.
 */

public class BirthdayProblem {

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]); // 从命令行读取N的值
        int count = 0; // 记录生成的随机数数量
        HashSet<Integer> set = new HashSet<>(); // 记录已经生成的随机数

        while (true) {
            int num = StdRandom.uniform(N); // 生成0到N-1之间的随机整数
            count++;
            if (set.contains(num)) { // 如果该随机数已经存在，则跳出循环
                break;
            }
            set.add(num); // 将该随机数添加到集合中
        }

        StdOut.println("Generated numbers before first duplicate: " + count);
        StdOut.println("Theoretical value: " + Math.sqrt(Math.PI * N / 2));
    }
}
