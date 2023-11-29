package symbol_table.tools;

import edu.princeton.cs.algs4.Accumulator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class TestAccumulator {
    public static void main(String[] args) {
        Accumulator stats = new Accumulator();

        while(!StdIn.isEmpty()) {
            double x = StdIn.readDouble();
            stats.addDataValue(x);
        }

        /**
         * 计算: 平均值(mean)、方差(var)、标准差(stddev)
         * 输入: 85, 90, 95, 100, 105
         * 输出: mean = 95.0, stddev = 7.90569, var = 62.50000
         */
        StdOut.printf("n      = %d\n", new Object[]{stats.count()});
        StdOut.printf("mean   = %.5f\n", new Object[]{stats.mean()});
        StdOut.printf("stddev = %.5f\n", new Object[]{stats.stddev()});
        StdOut.printf("var    = %.5f\n", new Object[]{stats.var()});
        StdOut.println(stats);

    }
}
