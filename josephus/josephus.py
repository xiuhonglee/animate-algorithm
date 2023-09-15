def josephus(n, k):
    # 创建一个列表，用来表示所有的人。编号从1开始到n（总共n个人）。
    people = list(range(1, n + 1))
    index = 0  # index用来记录当前应该从哪个位置开始数数。
    
    
    # 当列表中的人数大于2时，继续进行淘汰。
    while len(people) > 2:
        # 找出下一个需要被淘汰的人的位置。
        # （index + m - 1） % len(people) 这个表达式实现了循环计数和淘汰。
        index = (index + k - 1) % len(people)
        
        # 将这个位置的人淘汰（从列表中删除）。
        people.pop(index)
    
    # 返回最后剩下的两个人。
    return people

# n = 41（人数），m = 3（每数到第3个人就淘汰）。
remaining = josephus(41, 3)

print("最后存活下来的两个人的编号是:", remaining)
