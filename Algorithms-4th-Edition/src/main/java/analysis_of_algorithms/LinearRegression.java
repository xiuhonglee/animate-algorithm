package analysis_of_algorithms;

import org.apache.commons.math3.linear.*;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

public class LinearRegression {

    public static void main(String[] args) {

        // 给定数据点【可以换成自己的数据】
        double[][] data = {
                {1, 0.175},
                {2, 1.213},
                {4, 9.41},
                {8, 74.941}
        };

        double[] log_n = new double[data.length];
        double[] log_T = new double[data.length];

        for (int i = 0; i < data.length; i++) {
            double n = data[i][0];
            double T = data[i][1];
            log_n[i] = Math.log(n);
            log_T[i] = Math.log(T);
        }

        // 构建设计矩阵
        RealMatrix designMatrix = new Array2DRowRealMatrix(data.length, 2);
        for (int i = 0; i < log_n.length; i++) {
            designMatrix.setEntry(i, 0, 1.0);  // 第一列全是1，用于计算截距
            designMatrix.setEntry(i, 1, log_n[i]);  // 第二列是log_n
        }

        // 执行线性回归
        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
        regression.setNoIntercept(true);
        regression.newSampleData(log_T, designMatrix.getData());

        // 提取参数
        double[] parameters = regression.estimateRegressionParameters();
        double log_a = parameters[0]; // 截距，即log(a)
        double k = parameters[1];  // 斜率，即k

        // 计算a的值
        double a = Math.exp(log_a);

        // 输出k和a
        System.out.println("k: " + k + ", a: " + a);
        // RESULT: k: 2.9182384662267347, a: 0.16830073785100344
    }
}
