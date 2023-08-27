# 定义链表节点类
class Node:
    def __init__(self, data):
        self.data = data    # 节点的数据
        self.next = None    # 下一个节点的指针

# 定义链表类
class LinkedList:
    def __init__(self):
        self.head = None    # 头结点

    # 在链表尾部插入节点
    def append(self, data):
        new_node = Node(data)

        # 如果链表为空，将新节点设为头结点
        if self.head == None:
            self.head = new_node
            return

        # 遍历链表到最后一个节点
        last_node = self.head
        while last_node.next:
            last_node = last_node.next

        # 将新节点插入到最后一个节点之后
        last_node.next = new_node

    # 打印链表中的所有数据值
    def display(self):
        current_node = self.head
        while current_node:
            print(current_node.data)
            current_node = current_node.next

    # 选择排序
    def selection_sort(self):
        # 如果链表为空，直接返回
        if self.head == None:
            return

        # 排序过程
        current_node = self.head
        while current_node.next:
            min_value = current_node.data     # 默认当前节点为最小值
            min_node = current_node           # 最小值节点
            search_node = current_node.next   # 从当前节点的下一个节点开始搜索

            while search_node:
                # 如果搜索到的节点的数据值比最小值小，更新最小值和最小值节点
                if search_node.data < min_value:
                    min_value = search_node.data
                    min_node = search_node
                search_node = search_node.next  # 继续搜索下一个节点

            # 交换当前节点和最小值节点的数据值
            temp = current_node.data
            current_node.data = min_node.data
            min_node.data = temp

            current_node = current_node.next  # 排序下一个节点

# 测试
if __name__ == '__main__':
    linked_list = LinkedList()
    linked_list.append(6)
    linked_list.append(3)
    linked_list.append(8)
    linked_list.append(2)
    linked_list.append(1)

    print("排序前：")
    linked_list.display()

    linked_list.selection_sort()

    print("排序后：")
    linked_list.display()
