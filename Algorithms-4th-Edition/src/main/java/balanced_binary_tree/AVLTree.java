package balanced_binary_tree;

public class AVLTree {

    // AVL树的节点类定义
    public class Node {
        int key;        // 节点的键
        int height;     // 节点的高度
        int balanceFactor; // 节点的平衡因子
        Node left;      // 指向左子节点
        Node right;     // 指向右子节点

        Node(int key) {
            this.key = key;
            this.height = 0;
            this.balanceFactor = 0;
        }
    }

    private Node root; // AVL树的根节点

    // 查找方法：按键查找节点
    public Node get(int key) {
        Node current = root;
        while (current != null) {
            if (current.key == key) {
                break;
            }
            current = current.key < key ? current.right : current.left;
        }
        return current;
    }

    // 插入方法：在AVL树中插入新的键
    public void put(int key) {
        root = put(root, key);
    }

    // 删除方法：从AVL树中删除键
    public void delete(int key) {
        root = delete(root, key);
    }

    // 获取根节点
    public Node getRoot() {
        return root;
    }

    // 获取树的高度
    public int height() {
        return root == null ? -1 : root.height;
    }

    // 私有插入方法：在指定的节点下插入新键
    private Node put(Node root, int key) {
        if (root == null) {
            return new Node(key);
        } else if (root.key > key) {
            root.left = put(root.left, key);
        } else if (root.key < key) {
            root.right = put(root.right, key);
        } else {
            throw new RuntimeException("duplicate Key!");
        }
        return rebalance(root);
    }

    // 更新节点的高度和平衡因子
    private void updateHeightAndBalance(Node n) {
        int leftHeight = height(n.left);
        int rightHeight = height(n.right);
        n.height = 1 + Math.max(leftHeight, rightHeight);
        n.balanceFactor = rightHeight - leftHeight;
    }

    // 私有删除方法：从指定节点下删除键
    private Node delete(Node node, int key) {
        if (node == null) {
            return node;
        } else if (node.key > key) {
            node.left = delete(node.left, key);
        } else if (node.key < key) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                Node mostLeftChild = mostLeftChild(node.right);
                node.key = mostLeftChild.key;
                node.right = delete(node.right, node.key);
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        return node;
    }

    // 找到最左侧的子节点
    private Node mostLeftChild(Node node) {
        Node current = node;
        // 循环找到最左侧的叶子节点
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // 重新平衡节点
    private Node rebalance(Node z) {
        // 更新节点z的高度和平衡因子
        updateHeightAndBalance(z);
        // 获取z的平衡因子
        int balance = z.balanceFactor;

        // 检测并修正RR（右右）或RL（右左）不平衡的情况
        if (balance > 1) {
            // RR情况：z的右子树比左子树高，且z的右子节点的右子树比左子树高
            if (height(z.right.right) > height(z.right.left)) {
                z = rotateLeft(z); // 进行左旋转
            } else {
                // RL情况：z的右子树比左子树高，但z的右子节点的左子树比右子树高
                z.right = rotateRight(z.right); // 先对z的右子节点进行右旋转
                z = rotateLeft(z); // 然后对z进行左旋转
            }
        }
        // 检测并修正LL（左左）或LR（左右）不平衡的情况
        else if (balance < -1) {
            // LL情况：z的左子树比右子树高，且z的左子节点的左子树比右子树高
            if (height(z.left.left) > height(z.left.right)) {
                z = rotateRight(z); // 进行右旋转
            } else {
                // LR情况：z的左子树比右子树高，但z的左子节点的右子树比左子树高
                z.left = rotateLeft(z.left); // 先对z的左子节点进行左旋转
                z = rotateRight(z); // 然后对z进行右旋转
            }
        }

        // 返回经过旋转调整后的新根节点
        return z;
    }


    // 右旋转
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node z = x.right;
        x.right = y;
        y.left = z;

        updateHeightAndBalance(y);
        updateHeightAndBalance(x);
        return x;
    }

    // 左旋转
    private Node rotateLeft(Node y) {
        Node x = y.right;
        Node z = x.left;
        x.left = y;
        y.right = z;

        updateHeightAndBalance(y);
        updateHeightAndBalance(x);
        return x;
    }

    // 更新节点的高度
    private void updateHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));

    }

    // 获取指定节点的高度
    private int height(Node n) {
        return n == null ? -1 : n.height;
    }

    // 获取节点的平衡因子
    public int getBalance(Node n) {
        return (n == null) ? 0 : height(n.right) - height(n.left);
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();

        // 测试插入
        avlTree.put(10);
        avlTree.put(20);
        avlTree.put(30);
        avlTree.put(40);
        avlTree.put(50);
        avlTree.put(25);

        // 测试查找
        System.out.println("查找键为30的节点: " + avlTree.get(30).key);

        // 输出树的根节点和高度
        System.out.println("树的根节点: " + avlTree.getRoot().key);
        System.out.println("树的高度: " + avlTree.height());

        // 测试删除
        avlTree.delete(20);
        System.out.println("删除键为20的节点后的树的根节点: " + avlTree.getRoot().key);
        System.out.println("删除键为20的节点后的树的高度: " + avlTree.height());
    }
}

