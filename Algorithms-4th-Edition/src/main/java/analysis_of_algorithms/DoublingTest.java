/******************************************************************************
 *  Compilation:  javac DoublingTest.java
 *  Execution:    java DoublingTest
 *  Dependencies: ThreeSum.java Stopwatch.java StdRandom.java StdOut.java
 *
 *  % java DoublingTest
 *      250     0.0
 *      500     0.0
 *     1000     0.1
 *     2000     0.6
 *     4000     4.5
 *     8000    35.7
 *  ...
 *
 ******************************************************************************/

package analysis_of_algorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 *  The {@code DoublingTest} class provides a client for measuring
 *  the running time of a method using a doubling test.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/14analysis">Section 1.4</a>
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class DoublingTest {
    private static final int MAXIMUM_INTEGER = 1000000;

    // This class should not be instantiated.
    private DoublingTest() { }

    /**
     * Returns the amount of time to call {@code ThreeSum.count()} with <em>n</em>
     * random 6-digit integers.
     * @param n the number of integers
     * @return amount of time (in seconds) to call {@code ThreeSum.count()}
     *   with <em>n</em> random 6-digit integers
     */
    public static double timeTrial(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
        }
        Stopwatch timer = new Stopwatch();
        // 原始方法
        // int ignore = ThreeSum.count(a);


        // 第一次优化
        /**
         * 硬件环境 (MacBookPro Apple M1 pro)
         *
         *  模型：T(N) = 2.016 \times 10^{-9} N^2 \log(N) - 3.559 \times 10^{-6} N \log(N) + 4.378 \times 10^{-5} N - 0.0241
         * ================================================
         *   输入规模   T(实际)   T(理论值)
         *     250     0.0    ~0.0
         *     500     0.0    ~0.0
         *    1000     0.0    ~0.0
         *    2000     0.1    0.0739
         *    4000     0.3    0.367
         *    8000     1.3    1.63
         *   16000     5.1    7.09
         *   32000    20.8    30.57
         *   64000    86.0    130.98
         *  128000   355.1    558.24
         */
         int ignore = ThreeSumFast.count(a);

        // 第二次优化
        /**
         *  硬件环境 (MacBookPro Apple M1 pro)
         *  模型：T(N) = 1.32 \times 10^{-9} N^2 + 1.09 \times 10^{-6} N + 0.0042
         * ================================================
         *   输入规模   T(实际)   T(理论值)
         *     250     0.0      0.0 (近似)
         *     500     0.0      0.0 (近似)
         *    1000     0.0      0.0 (近似)
         *    2000     0.0      0.0117
         *    4000     0.0      0.0297
         *    8000     0.1      0.0974
         *   16000     0.4      0.3596
         *   32000     1.4      1.3908
         *   64000     5.5      5.4807
         *  128000    22.1      21.7706
         */
//        long ignore = ThreeSumFinal.count(a);

        return timer.elapsedTime();
    }

    /**
     * Prints table of running times to call {@code ThreeSum.count()}
     * for arrays of size 250, 500, 1000, 2000, and so forth.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        for (int n = 250; true; n += n) {
            double time = timeTrial(n);
            StdOut.printf("%7d %7.1f\n", n, time);
        }
    }
}

/******************************************************************************
 *  Copyright 2002-2022, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/