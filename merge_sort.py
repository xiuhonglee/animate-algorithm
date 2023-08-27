def merge_sort(arr):
    """
    归并排序函数，输入一个数组，返回排序后的新数组。
    """
    if len(arr) <= 1:
        return arr

    # 将数组分为两部分，分别进行递归排序
    mid = len(arr) // 2
    left_arr = merge_sort(arr[:mid])
    right_arr = merge_sort(arr[mid:])

    # 合并两个有序子数组
    return merge(left_arr, right_arr)

def merge(left, right):
    """
    合并两个有序数组的函数，输入两个有序数组，返回一个新的有序数组。
    """
    result = []
    i = j = 0
    while i < len(left) and j < len(right):
        if left[i] <= right[j]:
            result.append(left[i])
            i += 1
        else:
            result.append(right[j])
            j += 1
    result += left[i:]
    result += right[j:]
    return result


# 测试用例1：常规情况
test_arr1 = [8, 4, 6, 2, 1, 9, 7, 5, 3]
print(merge_sort(test_arr1))  # 输出结果为 [1, 2, 3, 4, 5, 6, 7, 8, 9]

# 测试用例2：特殊情况-空数组
test_arr2 = []
print(merge_sort(test_arr2))  # 输出结果为 []

# 测试用例3：特殊情况-输入数组为空或只含有一个元素
test_arr3 = [9]
print(merge_sort(test_arr3))  # 输出结果为 [9]

