package analysis_of_algorithms;

import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class ThreeSumFinal {

    // 不要实例化。
    private ThreeSumFinal() { }


    /**
     * 第二次优化：采用双指针技术
     * 时间复杂度：O(N^2)
     */
    public static long count(int[] a) {
        Arrays.sort(a);
        int N = a.length;
        long cnt = 0;
        for (int i = 0; i < N - 2; i++) {
            int start = i + 1;
            int end = N - 1;
            while (start < end) {
                if (a[i] + a[start] + a[end] == 0) {
                    cnt++;
                    start++;
                    end--;
                } else if (a[i] + a[start] + a[end] < 0) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] a = in.readAllInts();

        Stopwatch timer = new Stopwatch();
        long count = count(a);
        StdOut.println("耗时 = " + timer.elapsedTime());
        StdOut.println(count);
    }
}
