package analysis_of_algorithms;

import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

public class ThreeSumFinalModelFitting {
    public static void main(String[] args) {

        // 这里换成你的测试数据
        double[][] data = { {1000, 0.005}, {2000,0.012}, {4000,0.031}, {8000,0.098}, {16000,0.358}, {32000,1.389} };
        
        WeightedObservedPoints obs = new WeightedObservedPoints();
        for (double[] datum : data) {
            obs.add(datum[0], datum[1]);
        }
        
        PolynomialCurveFitter fitter = PolynomialCurveFitter.create(2); // 2 表示二次多项式
        double[] coeff = fitter.fit(obs.toList());
        
        System.out.println("y = " + coeff[2] + " * N^2 + " + coeff[1] + " * N + " + coeff[0]);

        // RESULT: y = 1.318221890610448E-9 * N^2 + 1.085747925662513E-6 * N + 0.004204166666666693

    }
}
