
# 题目：假设你是一名软件工程师，负责开发一个新的社交媒体平台。
# 你的用户可以添加朋友，形成一个大的社交网络。现在，你的任务是实现一个功能：给定一个用户，找出离这个用户最近的所有用户。

# 具体来说，你需要实现以下两个功能：

# 用户可以添加朋友。
# 给定一个用户，你的程序能够返回离这个用户最近的所有用户。也就是说，你的程序应该能够返回这个用户的朋友，朋友的朋友，等等。


from collections import deque

class SocialNetwork:
    def __init__(self):
        """
        初始化一个空的社交网络。
        我们使用字典来表示这个网络，
        字典的每个键是一个用户，对应的值是这个用户的朋友列表。
        """
        self.network = {}

    def add_friend(self, user, friend):
        """
        在社交网络中添加一个朋友关系。
        这个方法会在两个用户的朋友列表中都添加对方。
        """
        if user not in self.network:
            self.network[user] = []
        if friend not in self.network:
            self.network[friend] = []
        self.network[user].append(friend)
        self.network[friend].append(user)

    def find_all_friends(self, user):
        """
        找出离一个用户最近的所有用户。
        这个方法使用广度优先搜索实现。
        """
        visited = []
        queue = deque([user])

        while queue:
            user = queue.popleft()
            if user not in visited:
                visited.append(user)
                friends = self.network[user]
                unvisited_friends = list(set(friends) - set(visited))
                queue.extend(unvisited_friends)

        # 去除用户自身
        visited.remove(user)

        return visited


# 测试 SocialNetwork 类
SN = SocialNetwork()
SN.add_friend("Alice", "Bob")
SN.add_friend("Alice", "Charlie")
SN.add_friend("Bob", "David")
SN.add_friend("Charlie", "Edward")
print(SN.find_all_friends("Alice"))  # 输出: ['Bob', 'Charlie', 'David', 'Edward']

