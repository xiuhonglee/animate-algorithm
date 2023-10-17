## 动画解析《算法图解》代码仓库

欢迎来到本 GitHub 仓库！这里包含了与我的 B站 视频系列“动画解析《算法图解》”相关的所有代码。每个章节都有对应的 Python 代码示例，帮助你更好地理解和掌握算法。

## **视频地址**
  - [bilibili](https://www.bilibili.com/video/BV1Aw411U7mf/?spm_id_from=333.999.0.0&vd_source=42e7dc7e5ebb14d27d2434b00ff63157)
  - [YouTube](https://www.youtube.com/watch?v=cSRP53XTAUg&list=PLQ7NyNO3w6Ad5M7Am0suoK4XFng6_KEa0)

## 目录

1. [第一章节 - 算法简介](#第一章节---二分查找)
2. [第二章节 - 选择排序](#第二章节---选择排序)
3. [第三章节 - 递归](#第三章节---递归)
4. [第四章节 - 快速排序](#第四章节---快速排序)
5. [第五章节 - 散列表](#第五章节---散列表)
6. [第六章节 - 广度优先搜索](#第六章节---广度优先搜索)
7. [第七章节 - 迪杰斯特拉算法](#第七章节---迪杰斯特拉算法)
8. [第八章节 - 贪心算法](#第八章节---贪心算法)
9. [第九章节 - 动态规划](#第九章节---动态规划)
9. [第十章节 - KNN](#第十章节---K-近邻算法)

---

### 第一章节 - 算法简介

- **代码**: [二分查找](01/binary_search.py)

---

### 第二章节 - 选择排序

- **代码**:
  - [选择排序-数组实现(不稳定)](02/selection_sort_arr.py)
  - [选择排序-链表实现(稳定)](02/selection_sort_linked_list.py)

---

### 第三章节 - 递归

- **代码**:
  - [求第 N 个斐波那契数-递归形式](03/fibonacci.py)
  - [求第 N 个斐波那契数-循环形式](03/fibonacci_loop.py)
  - [求第 N 个斐波那契数-尾递归形式](03/fibonacci_tail.py)

---

### 第四章节 - 快速排序

- **代码**:
  - [归并排序](04/merge_sort.py)
  - [快速排序-原地(in-place)排序法](04/quick_sort.py)
  - [快速排序-替代递归的循环方式](04/quick_sort_loop.py)

---

### 第五章节 - 散列表

- **代码**:
  - [散列表-python自带](05/demo.py)
  - [散列表-(开散列)拉链法实现](05/ChaingHashTable.py)
  - [选择排序-(闭散列)线性探测法](05/LinearProbeHashTable.py)
  - [RabinKarp算法-模式匹配](05/RabinKarp.py)

---

### 第六章节 - 广度优先搜索

- **图相关代码**:
  - [图的实现](06/graph/graph.py)
  - [有向图的实现](06/graph/DirectedGraph.py)
  - [权重图的实现](06/graph/WeightedGraph.py)
  - [图的存储-链表](06/graphStore/graph_list.py)
  - [图的存储-矩阵(二维数组)](06/graphStore/graph_matrix.py)

- **队列相关代码**:
  - [简单的队列实现](06/bfs/simpleQueue.py)

- **广度优先搜索(BFS)相关代码**:
  - [BFS-搜索](06/bfs/bfs-search.py)
  - [BFS-遍历](06/bfs/bfs-traverse.py)

- **关于图的第三方库代码**:
  - [igraph-demo](06/graphLibOfPython/igraph_demo1.py)
  - [networkx-demo1](06/graphLibOfPython/networkx_demo1.py)
  - [networkx-demo2](06/graphLibOfPython/networkx_demo2.py)
---

### 第七章节 - 迪杰斯特拉算法

- **代码**:
  - [Dijstra(simple): 只计算最短距离，不存储最短路径](07/dijkstra.py)
  - [Dijstra: 计算最短距离且保存最短路径](07/dijkstra2.py)

---

### 第八章节 - 贪心算法


- **教室排课代码**:
  - [教室排课](08/classroom_scheduling.py)
  - [教室排课-按结束时间(正确)](08/sort_by_end_time.py)
  - [教室排课-按开始时间(错误)](08/sort_by_start_time.py)

- **0-1背包问题代码**:
  - [背包问题-贪心算法实现](08/knapsack_greedy.py)
  - [背包问题-动态规划实现](08/knapsack_dp.py)

- **集合覆盖问题代码**:
  - [集合覆盖问题-简单方式(慢，结果准确)](08/simple_way_solve_set_covering_problem.py)
  - [集合覆盖问题-贪心算法(快，结果近似，不保证完全正确)](08/greedy_algorithm_solve_set_covering_problem.py)

- **旅行商(TSP)问题代码**:
  - [旅行商问题-贪心算法实现](08/travle.py)

---

### 第九章节 - 动态规划

- **Fibonacci相关代码**:
  - [fibonacci-递归实现](09/fibonacci.py)
  - [fibonacci-备忘录法优化](09/fibonacci_memo.py)
  - [fibonacci-自底向上优化](09/fibonacci_bottom_up.py)

- **背包问题相关代码**:
  - [0-1背包-二维DP表实现](09/knapsack.py)
  - [0-1背包-二维DP表(颠倒横纵轴)实现](09/knapsack_transposed.py)
  - [0-1背包-一维DP表实现](09/knapsack_1D.py)
  - [0-1背包(分割等和子集)](09/canPartition.py)

  - [完全背包(硬币找零)-二维DP表实现](09/coinChange_2D.py)
  - [完全背包(硬币找零)-一维DP表实现](09/coinChange.py)

  - [分数背包-实现](09/fractional_knapsack.py)

- **最长公共子序列(LCS)相关代码**:
  - [LCS-二维DP表实现](09/longestCommonSubsequence_with_path.py)
  - [LCS-一维DP表实现](09/longestCommonSubsequence_with_path_1D.py)
  - [LCS-只判断是否存在最长公共子序列](09/longestCommonSubsequence_without_path_1D.py)

---

### 第十章节 - K-近邻算法

- **电影推荐系统相关代码**
  - [电影推荐系统-计算距离](10/distance.py)
  - [电影推荐系统-预测评分](10/recommendation_system.py)


## 如何使用本仓库

1. 克隆本仓库到你的计算机。
2. 打开你感兴趣的章节文件夹，比如 `01`。
3. 查看对应的 Python 代码文件，如 `binary_search.py`。
4. 与视频内容进行对照，加深理解。

## 联系和反馈

如果你有任何问题或者建议，随时通过[我的 B站 频道](https://www.bilibili.com/video/BV1Aw411U7mf/?spm_id_from=333.788&vd_source=42e7dc7e5ebb14d27d2434b00ff63157)留言，或者在这里开一个 Issue。
