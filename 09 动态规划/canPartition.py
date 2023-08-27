def canPartition(nums):
    """
    判断是否可以将nums分割为两个和相等的子集
    :param nums: 整数列表
    :return: 布尔值，True表示可以分割，False表示不能
    """
    total = sum(nums)
    # 如果总和是奇数，则不能将其分割为两个和相等的子集
    if total % 2 != 0:
        return False

    target = total // 2
    n = len(nums)
    # dp[i][j]表示使用前i个数字，是否可以得到和为j
    dp = [[False for _ in range(target + 1)] for _ in range(n + 1)]
    for i in range(n + 1):
        dp[i][0] = True

    for i in range(1, n + 1):
        for j in range(1, target + 1):
            # 不选择第i个数字
            dp[i][j] = dp[i-1][j]
            # 选择第i个数字，条件是j不小于nums[i-1]
            if j >= nums[i-1]:
                dp[i][j] = dp[i][j] or dp[i-1][j-nums[i-1]]

    return dp[n][target]

nums = [1, 5, 11, 5]
canPartition(nums)
