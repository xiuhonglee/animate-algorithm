# 动画解析《算法-第四版》代码仓库

这里包含了与我的 B站 视频系列“动画解析《算法-第四版》”相关的所有代码。每个章节都有对应的 java 代码示例。


> 说明：本仓库中的代码可能会跟本书的官网代码有出入，本书中完整的代码请移步 [官网](https://algs4.cs.princeton.edu/code/) 


## 目录

1. [【 第一章节 - 算法分析(上) 】](#第一章节---算法分析(上))
2. [【 第二章节 - 算法分析(中) 】](#第二章节---算法分析(中))

---

### 第一章节 - 算法分析(上)

- **ThreeSum-暴力求解**: 
  - [【算法】计算文件中三元组和为 0 的数量](./src/main/java/analysis_of_algorithms/ThreeSum.java)


- **倍率实验**
  - [线性回归-确认数学模型 (python)](scripts/analysis_of_algorithms/power_law_model_estimator.py)
  - [线性回归-确认数学模型 (java)](./src/main/java/analysis_of_algorithms/LinearRegression.java)
   
---

### 第二章节 - 算法分析(中)

<span style="color:red;font-weight:bold"><i>注意：本章节所有实验数据取决于运行环境，运行结果如有差异请自行判断</i></bold></span>

- **ThreeSum改进**
  - [ThreeSumFast-第一次改进](./src/main/java/analysis_of_algorithms/ThreeSumFast.java)
  - [ThreeSumFinal-第二次改进 (双指针技术)](./src/main/java/analysis_of_algorithms/ThreeSumFinal.java)
  

- **线性回归-拟合数学模型**
  - [计算ThreeSumFast-模型 (python)](scripts/analysis_of_algorithms/ThreeSumFast_model_fitting.py)
  - [计算ThreeSumFast-模型 (java)](scripts/analysis_of_algorithms/ThreeSumFast_model_fitting.py)

  - [计算ThreeSumFinal-模型 (python)](scripts/analysis_of_algorithms/ThreeSumFast_model_fitting.py)
  - [计算ThreeSumFinal-模型 (java)](src/main/java/analysis_of_algorithms/ThreeSumFinalModelFitting.java)


- **实验测试代码**
  - [倍率实验-验证模型(输入数字随机)](src/main/java/analysis_of_algorithms/DoublingTest.java)
  - [小数组-验证线性搜索快于二分查找(平均)](src/main/java/analysis_of_algorithms/SearchComparison.java)
  - [小数组-验证冒泡快于快排(平均)](src/main/java/analysis_of_algorithms/SortComparison.java)
  - [模拟网络编程案例-批量请求vs分批次请求](src/main/java/analysis_of_algorithms/DatabaseQueryComparison.java)


- **曲线图绘制脚本**
  - [ThreeSumFast 性能曲线](scripts/analysis_of_algorithms/plot_math_model.py)
  - [ThreeSumFinal 性能曲线](scripts/analysis_of_algorithms/plot_math_model2.py)
  - [合并绘制到一张图上](scripts/analysis_of_algorithms/plot_math_model3.py)


### 第三章节 - 算法分析(下)

- **数据类型大小**
  - [java中常见的数据类型大小](src/main/java/analysis_of_algorithms/MemoryAnalysis.java)


- **求第 N 个斐波那契数**
  - [递归的方式](src/main/java/analysis_of_algorithms/FibonacciRecursive.java)
  - [迭代法](src/main/java/analysis_of_algorithms/Fibonacci.java)
  - [矩阵指数法](src/main/java/analysis_of_algorithms/MatrixExponentiation.java)