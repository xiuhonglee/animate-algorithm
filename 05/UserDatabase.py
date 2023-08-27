class User:
    def __init__(self, id, name, email):
        self.id = id
        self.name = name
        self.email = email

    def __str__(self):
        return f"User(id={self.id}, name={self.name}, email={self.email})"


class UserDatabase:
    def __init__(self):
        self.users = {}

    def add_user(self, user):
        self.users[user.id] = user

    def get_user(self, user_id):
        return self.users.get(user_id, None)


# 创建一个用户数据库实例
user_db = UserDatabase()

# 添加用户
user1 = User(1, "Alice", "alice@example.com")
user2 = User(2, "Bob", "bob@example.com")
user3 = User(3, "Charlie", "charlie@example.com")

user_db.add_user(user1)
user_db.add_user(user2)
user_db.add_user(user3)

# 根据用户ID检索用户
user = user_db.get_user(2)
print(user)  # 输出: User(id=2, name=Bob, email=bob@example.com)
