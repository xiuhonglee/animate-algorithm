package analysis_of_algorithms;

import org.apache.commons.math3.analysis.MultivariateMatrixFunction;
import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.ArrayRealVector;

public class ThreeSumFastModelFitting {

    public static void main(String[] args) {
        // 给定数据点
        double[] N_values = new double[]{1000, 2000, 4000, 8000, 16000, 32000};
        double[] T_values = new double[]{0.015, 0.064, 0.296, 1.238, 5.118, 21.611};

        // 定义模型函数
        MultivariateVectorFunction vectorFunction = (point) -> {
            double a = point[0];
            double b = point[1];
            double c = point[2];
            double d = point[3];
            double[] values = new double[N_values.length];
            for (int i = 0; i < values.length; i++) {
                values[i] = a * N_values[i] * N_values[i] * Math.log(N_values[i])
                        + b * N_values[i] * Math.log(N_values[i])
                        + c * N_values[i]
                        + d;
            }
            return values;
        };

        // 导数（雅可比矩阵）的计算
        MultivariateMatrixFunction matrixFunction = (point) -> {
            double[][] jacobian = new double[N_values.length][4];
            for (int i = 0; i < jacobian.length; i++) {
                jacobian[i][0] = N_values[i] * N_values[i] * Math.log(N_values[i]);
                jacobian[i][1] = N_values[i] * Math.log(N_values[i]);
                jacobian[i][2] = N_values[i];
                jacobian[i][3] = 1;
            }
            return jacobian;
        };

        // 构建最小二乘问题
        LeastSquaresBuilder builder = new LeastSquaresBuilder();
        builder.model(vectorFunction, matrixFunction);
        builder.target(new ArrayRealVector(T_values, false));
        builder.start(new double[]{0.0, 0.0, 0.0, 0.0});
        builder.maxEvaluations(Integer.MAX_VALUE); // 设置最大评估次数
        builder.maxIterations(Integer.MAX_VALUE); // 设置最大迭代次数

        // 解决最小二乘问题
        LeastSquaresOptimizer optimizer = new LevenbergMarquardtOptimizer();
        LeastSquaresOptimizer.Optimum optimum = optimizer.optimize(builder.build());

        // 获取并打印参数
        RealVector optimumPoint = optimum.getPoint();
        System.out.println("a = " + optimumPoint.getEntry(0));
        System.out.println("b = " + optimumPoint.getEntry(1));
        System.out.println("c = " + optimumPoint.getEntry(2));
        System.out.println("d = " + optimumPoint.getEntry(3));

        // a = 2.0160189155176637E-9
        // b = -3.558980015271123E-6
        // c = 4.378181546967302E-5
        // d = -0.024067014663248048
    }
}
