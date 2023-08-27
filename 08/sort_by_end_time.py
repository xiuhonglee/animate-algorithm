# 定义活动列表，每个活动由开始时间和结束时间组成
activities = [(1, 10), (2, 3), (4, 5), (6, 7), (8, 9)]

# 按照结束时间排序
activities.sort(key = lambda x:x[1])

# 初始化当前活动的结束时间为0
current_time = 0
# 初始化计数器
count = 0

# 遍历活动列表
for activity in activities:
    # 如果活动的开始时间大于等于当前时间，说明可以进行这个活动
    if activity[0] >= current_time:
        # 更新当前时间为活动的结束时间
        current_time = activity[1]
        # 计数器加1
        count += 1

print("按照结束时间排序，可以进行的最多活动数为:", count)

