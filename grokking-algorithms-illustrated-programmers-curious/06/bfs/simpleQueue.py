class Queue:
    def __init__(self):
        """
        初始化一个空队列
        """
        self.items = []

    def isEmpty(self):
        """
        检查队列是否为空
        返回True，如果队列为空
        """
        return self.items == []

    def enqueue(self, item):
        """
        把一个元素添加到队尾
        """
        self.items.append(item)

    def dequeue(self):
        """
        从队首移除一个元素
        返回被移除的元素
        """
        if self.isEmpty():
            raise Exception("队列为空，不能执行出队操作")
        return self.items.pop(0)

    def size(self):
        """
        返回队列中元素的数量
        """
        return len(self.items)

# 创建一个队列对象
q = Queue()

# 检查新创建的队列是否为空
print(q.isEmpty())  # 输出: True

# 把一些元素加入到队列中
q.enqueue(1)
q.enqueue(2)
q.enqueue(3)

# 检查队列是否为空
print(q.isEmpty())  # 输出: False

# 查看队列的大小
print(q.size())  # 输出: 3

# 执行一次出队操作，并打印出队的元素
print(q.dequeue())  # 输出: 1

# 查看队列的大小
print(q.size())  # 输出: 2

# 执行一次出队操作，并打印出队的元素
print(q.dequeue())  # 输出: 2

# 查看队列的大小
print(q.size())  # 输出: 1
