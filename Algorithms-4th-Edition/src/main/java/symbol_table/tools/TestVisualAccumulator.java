package symbol_table.tools;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * java TestVisualAccumulator 2000
 */

public class TestVisualAccumulator {
	public static void main(String[] args) {
		int T = Integer.parseInt(args[0]);
		VisualAccumulator a = new VisualAccumulator(T, 1.0);
		for (int t = 0; t < T; t++) {
			a.addDataValue(StdRandom.random());
		}
		// Mean (2000 values): 0.4972
		StdOut.println(a);
	}
}