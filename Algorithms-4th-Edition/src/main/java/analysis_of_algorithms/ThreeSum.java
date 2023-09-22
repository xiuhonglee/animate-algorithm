package analysis_of_algorithms;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 *  {@code ThreeSum} 类提供了用于计数和打印整数数组中求和为0的三元组的静态方法
 *  （忽略整数溢出）。
 *  这个实现使用了三重嵌套循环，时间复杂度与n^3成正比，
 *  其中n是整数的数量。
 *  有关额外文档，请参见 <a href="https://algs4.cs.princeton.edu/14analysis">Section 1.4</a> 
 */
public class ThreeSum {

    // 不要实例化。
    private ThreeSum() { }

    /**
     * 将满足{@code i < j < k}且{@code a[i] + a[j] + a[k] == 0}的(i, j, k)打印到标准输出。
     *
     * @param a 整数数组
     */
    public static void printAll(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                for (int k = j+1; k < N; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        cnt++;
                    }
                }
            }
        }
    }

    /**
     * 返回满足{@code i < j < k}且{@code a[i] + a[j] + a[k] == 0}的三元组的数量。
     *
     * @param  a 整数数组
     * @return   满足{@code i < j < k}且{@code a[i] + a[j] + a[k] == 0}的三元组的数量
     */
    public static int count(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * 从文件中读取一系列整数，文件是作为命令行参数指定的；
     * 计算三个整数之和恰好为零的数量；打印出执行计算所需的时间。
     *
     * @param args 命令行参数
     */
    public static void main(String[] args)  {
        In in = new In(args[0]);
        int[] a = in.readAllInts();

        Stopwatch timer = new Stopwatch();
        int count = count(a);
        StdOut.println("耗时 = " + timer.elapsedTime());
        StdOut.println(count);
    }
}
