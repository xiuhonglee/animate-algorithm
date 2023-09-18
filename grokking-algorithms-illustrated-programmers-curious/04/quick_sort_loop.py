def quicksort(arr):
    """
    使用循环实现快速排序
    :param arr: 待排序的数组
    :return: 排序后的数组
    """
    if len(arr) <= 1:  # 如果数组为空或只有一个元素，直接返回
        return arr
    stack = [(0, len(arr) - 1)]  # 用栈来记录需要排序的子数组的起始和终止索引
    while stack:
        start, end = stack.pop()  # 取出一个需要排序的子数组
        if start >= end:  # 如果子数组中只有一个元素，或者已经按照pivot分好区了，跳过这个子数组
            continue
        pivot_index = partition(arr, start, end)  # 分区
        stack.append((start, pivot_index - 1))  # 将左半部分的子数组需要排序的区间入栈
        stack.append((pivot_index + 1, end))  # 将右半部分的子数组需要排序的区间入栈
    return arr


def partition(arr, start, end):
    """
    对arr[start:end+1]进行分区，返回pivot的索引
    :param arr: 待分区的数组
    :param start: 子数组的起始索引
    :param end: 子数组的终止索引
    :return: pivot的索引
    """
    pivot = arr[end]  # 取最后一个元素作为pivot
    i = start - 1  # i指向小于pivot区间的末尾
    for j in range(start, end):  # 遍历整个子数组（除了pivot）
        if arr[j] < pivot:  # 如果当前元素小于pivot，就把它和小于pivot区间的下一个元素交换
            i += 1
            arr[i], arr[j] = arr[j], arr[i]
    arr[i + 1], arr[end] = arr[end], arr[i + 1]  # 将pivot放到正确的位置上
    return i + 1


# 测试用例
assert quicksort([]) == []  # 空数组
assert quicksort([1]) == [1]  # 只有一个元素的数组
assert quicksort([5, 4, 3, 2, 1]) == [1, 2, 3, 4, 5]  # 逆序数组
assert quicksort(list(range(10))) == list(range(10))  # 有序数组
assert quicksort([4, 2, 2, 8, 3, 3, 1, 6, 7, 5]) == [1, 2, 2, 3, 3, 4, 5, 6, 7, 8]  # 包含重复元素的数组
