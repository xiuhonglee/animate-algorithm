# 动画解析《算法-第四版》代码仓库

这里包含了与我的 B站 视频系列“[动画解析《算法-第四版》](https://space.bilibili.com/472759461/channel/collectiondetail?sid=1719279&ctype=0)”相关的所有代码。每个章节都有对应的 java 代码示例。


> 说明：本仓库中的代码可能会跟本书的官网代码有出入，本书中完整的代码请移步 [官网](https://algs4.cs.princeton.edu/code/) 


## 目录

1. [【 第一章节 - 算法分析(上) 】](#第一章节---算法分析上)
2. [【 第二章节 - 算法分析(中) 】](#第二章节---算法分析中)
3. [【 第三章节 - 算法分析(下) 】](#第三章节---算法分析下)
4. [【 第四章节 - 优先队列 】](#第四章节---优先队列)
5. [【 第五章节 - 堆排序 】](#第五章节---堆排序)

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

---

### 第三章节 - 算法分析(下)

- **数据类型大小**
  - [java中常见的数据类型大小](src/main/java/analysis_of_algorithms/MemoryAnalysis.java)

- **栈内存**
  - [估计最大递归深度](src/main/java/analysis_of_algorithms/InfiniteRecursion.java)

- **求第 N 个斐波那契数**
  - [递归的方式](src/main/java/analysis_of_algorithms/FibonacciRecursive.java)
  - [迭代法](src/main/java/analysis_of_algorithms/FibonacciLoop.java)
  - [矩阵指数法](src/main/java/analysis_of_algorithms/MatrixExponentiation.java)

---

### 第四章节 - 优先队列

- **优先队列 (PQ) 接口**
  - [PQ 简单接口定义](src/main/java/priority_queues/PriorityQueue.java)

- **简单实现 PQ**
  * [无序数组](src/main/java/priority_queues/UnorderedArrayPQ.java)
  * [无序数组-接口实现(下同)](src/main/java/priority_queues/UnorderedArrayPQImpl.java)
  * [有序数组](src/main/java/priority_queues/UnorderedArrayPQ.java)
  * [无序链表](src/main/java/priority_queues/UnorderedArrayPQ.java)
  * [有序链表](src/main/java/priority_queues/UnorderedArrayPQ.java)
  

- **二叉堆实现 PQ**
  - [二叉堆实现-简单实现](src/main/java/priority_queues/BinaryHeapPQ.java)
  - [索引二叉堆实现-简单实现](src/main/java/priority_queues/IndexedBinaryHeap.java)
  - [java标准库实现-java.util.PriorityQueue](src/main/java/priority_queues/PriorityQueueExample.java)

- **性能测试**
  - [测试代码，测试用例为【实验题 2.4.36】](src/main/java/priority_queues/PerformanceTest.java)
  - [测试用例同上，单独为java标准库 PQ 用的测试代码](src/main/java/priority_queues/PerformanceTest2.java)
   
### 第五章节 - 堆排序

- **两种建堆 (大堆) 方式**
  * [自上而下](src/main/java/heap_sort/HeapTopDown.java)
  * [自下而上-常用](src/main/java/heap_sort/HeapTopDown.java)

- **两种堆排序方式(用大堆进行升序排序)**
  * [标准版堆排序实现](src/main/java/heap_sort/HeapSortOrigin.java)
  * [Floyd 改进版本-较少比较次数](src/main/java/heap_sort/HeapSortFloyd.java)

- **计算Floyd优化后和标准对排序比较、交换次数**
  * [统计标准实现元素比较/交换次数](src/main/java/heap_sort/HeapSortStandardCount.java) 
  * [统计Floyd优化后元素比较/交换次数](src/main/java/heap_sort/HeapSortFloydCount.java) 

- **对两种堆排序进行性能测试**
  * [输入-普通随机整数](src/main/java/heap_sort/HeapSortPerformanceTest.java)
  * [输入-长字符串](src/main/java/heap_sort/HeapSortStringPerformanceTest.java)


