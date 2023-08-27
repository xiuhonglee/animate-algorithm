def partition(arr, low, high):
    # 选择第一个元素作为基准
    pivot = arr[low]
    # 挖坑的初始位置
    left = low
    right = high

    while left < right:
        # 从右边开始找小于基准的元素
        while left < right and arr[right] >= pivot:
            right -= 1
        # 将小于基准的元素填入左边的坑
        arr[left] = arr[right]

        # 从左边开始找大于基准的元素
        while left < right and arr[left] <= pivot:
            left += 1
        # 将大于基准的元素填入右边的坑
        arr[right] = arr[left]

    # 基准元素放入最后的坑
    arr[left] = pivot
    # 返回基准元素的索引
    return left

def quicksort(arr, low, high):
    if low < high:
        # 找到分割点
        pivot_index = partition(arr, low, high)
        # 递归对分割点左侧部分进行快速排序
        quicksort(arr, low, pivot_index - 1)
        # 递归对分割点右侧部分进行快速排序
        quicksort(arr, pivot_index + 1, high)

# 测试代码
arr = [5, 2, 9, 1, 7, 6, 3, 8, 4]
print("原始数组:", arr)
quicksort(arr, 0, len(arr) - 1)
print("排序后的数组:", arr)
