# 定义二分查找函数
def binary_search(arr, target):
    """
    二分查找算法

    参数:
        arr: 一个已排序的列表
        target: 要查找的目标值

    返回:
        目标值在列表中的索引，如果没有找到则返回-1
    """
    # 初始化左指针和右指针
    left = 0
    right = len(arr) - 1

    # 当左指针小于或等于右指针时，执行循环
    while left <= right:
        # 计算中间索引
        mid = (left + right) // 2

        # 如果中间元素等于目标值，则返回中间索引
        if arr[mid] == target:
            return mid
        # 如果中间元素小于目标值，调整左指针到mid+1
        elif arr[mid] < target:
            left = mid + 1
        # 如果中间元素大于目标值，调整右指针到mid-1
        else:
            right = mid - 1

    # 如果没有找到目标值，返回-1
    return -1

# 测试用例
if __name__ == '__main__':
    # 已排序的列表
    test_arr = [1, 3, 5, 7, 9, 11, 13, 15, 17]

    # 测试查找存在的元素
    assert binary_search(test_arr, 7) == 3

    # 测试查找不存在的元素
    assert binary_search(test_arr, 8) == -1

    # 测试查找列表首元素
    assert binary_search(test_arr, 1) == 0

    # 测试查找列表尾元素
    assert binary_search(test_arr, 17) == 8

    print("所有测试用例通过!")

