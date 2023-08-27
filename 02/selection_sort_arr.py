def selection_sort(arr):
    """
    选择排序函数，用于对数组进行排序
    :param arr: 将要进行排序的数组
    :return: 排序后的数组
    """
    # 遍历数组
    for i in range(len(arr)):
        # 初始化最小值索引为当前遍历位置
        min_idx = i
        # 遍历未排序部分的数组
        for j in range(i+1, len(arr)):
            # 查找最小值的索引
            if arr[min_idx] > arr[j]:
                min_idx = j
        # 交换当前遍历位置与最小值位置的元素，将最小值归位
        arr[i], arr[min_idx] = arr[min_idx], arr[i]
    return arr


# 测试
newArr = selection_sort([2,3,1,5,4,6,9,8,0])
print(newArr)
