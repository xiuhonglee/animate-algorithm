def quick_sort(arr):
    """
    实现快速排序算法

    Args:
        arr: 输入的列表

    Returns:
        排序后的列表
    """
    if len(arr) <= 1:
        # 当列表长度小于等于1时直接返回原列表
        return arr
    else:
        # 选取第一个元素作为基准值
        pivot = arr[0]
        # 分别处理大于和小于基准值的两个子列表
        return quick_sort([x for x in arr[1:] if x < pivot]) + \
               [pivot] + \
               quick_sort([x for x in arr[1:] if x >= pivot])

# 测试例子1：对整数列表进行排序
input_list1 = [4, 2, 8, 3, 1, 5, 7, 6]
output_list1 = quick_sort(input_list1)
print(output_list1)  # 输出 [1, 2, 3, 4, 5, 6, 7, 8]

# 测试例子2：对浮点数列表进行排序
input_list2 = [3.4, 3.2, 1.5, 4.8, 2.1, 5.6, 7.8, 6.5]
output_list2 = quick_sort(input_list2)
print(output_list2)  # 输出 [1.5, 2.1, 3.2, 3.4, 4.8, 5.6, 6.5, 7.8]

# 测试例子3：对字符串列表进行排序
input_list3 = ['banana', 'apple', 'orange', 'cherry', 'grape']
output_list3 = quick_sort(input_list3)
print(output_list3)  # 输出 ['apple', 'banana', 'cherry', 'grape', 'orange']

