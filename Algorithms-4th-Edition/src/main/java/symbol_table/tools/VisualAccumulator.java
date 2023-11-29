package symbol_table.tools;

import edu.princeton.cs.algs4.StdDraw;

/**
 * an abstract data type for accumulating data values (visual version)
 */

public class VisualAccumulator {
	private double total;
	private int N;

	public VisualAccumulator(int trials, double max) {
		// 设置画布尺寸以适应长方形区域
		StdDraw.setCanvasSize(1024, (int)(1024 * (max / trials)));
		StdDraw.setXscale(0, trials);
		StdDraw.setYscale(0, max);
		StdDraw.setPenRadius(.005);
	}

	public void addDataValue(double val) {
		N++;
		total += val;
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.point(N, val);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.point(N, total/N);
	}

	public double mean() {
		return total / N;
	}

	public String toString() {
		return "Mean (" + N + " values): " + String.format("%7.5f", mean());
	}

}