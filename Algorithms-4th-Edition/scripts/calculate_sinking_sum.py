import math

def calculate_sinking_sum(N):
    log_N = int(math.log2(N))
    total_sinking = 0
    for i in range(log_N):
        nodes = N / (2 ** (i + 2))  # 第 i 层的节点数
        sinking_per_node = i + 1    # 每个节点的下沉次数
        total_sinking += nodes * sinking_per_node
    return total_sinking

# 计算 N = 1000 和 N = 10000 的情况
sinking_sum_1000 = calculate_sinking_sum(1000)
sinking_sum_10000 = calculate_sinking_sum(10000)

print(sinking_sum_1000, sinking_sum_10000)

