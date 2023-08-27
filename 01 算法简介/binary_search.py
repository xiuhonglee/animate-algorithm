# 二分查找法函数实现
def binary_search(arr, target):
    """
    在排序数组 arr 中查找目标值 target，返回其索引。
    如果目标值不存在，则返回 -1。
    
    参数：
    arr -- 排序数组
    target -- 目标值
    
    返回：
    目标值的索引，或者 -1（目标值不存在）
    """
    # 初始化左右指针
    left, right = 0, len(arr) - 1

    # 当左指针小于或等于右指针时进行查找
    while left <= right:
        # 计算中间索引
        mid = left + (right - left) // 2

        # 找到目标值，返回索引
        if arr[mid] == target:
            return mid
        # 如果目标值小于中间值，将右指针移动到中间索引的左侧
        elif arr[mid] > target:
            right = mid - 1
        # 如果目标值大于中间值，将左指针移动到中间索引的右侧
        else:
            left = mid + 1

    # 目标值不存在
    return -1

# 测试用例
if __name__ == "__main__":
    arr1 = [1, 3, 5, 7, 9]
    target1 = 5
    print("Test Case 1: ", binary_search(arr1, target1))  # 应返回 2

    arr2 = [1, 3, 5, 7, 9]
    target2 = 6
    print("Test Case 2: ", binary_search(arr2, target2))  # 应返回 -1

    arr3 = []
    target3 = 1
    print("Test Case 3: ", binary_search(arr3, target3))  # 应返回 -1

    arr4 = [1]
    target4 = 1
    print("Test Case 4: ", binary_search(arr4, target4))  # 应返回 0
