package balanced_binary_tree;

class AVLTree {

    public class Node {
        int key, height;
        Node left, right;

        Node(int d) {
            key = d;
            height = 1;
        }
    }
    private Node root;

    // 一个工具函数，用于获取树的高度
    int height(Node N) {
        if (N == null)
            return 0;

        return N.height;
    }

    // 一个工具函数，用于获取两个整数的最大值
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // 一个工具函数，用于对以y为根的子树进行右旋
    // 参见上文给出的图示。
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // 执行旋转
        x.right = y;
        y.left = T2;

        // 更新高度
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // 返回新的根节点
        return x;
    }

    // 一个工具函数，用于对以x为根的子树进行左旋
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // 执行旋转
        y.left = x;
        x.right = T2;

        // 更新高度
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // 返回新的根节点
        return y;
    }

    // 获取节点N的平衡因子
    int getBalance(Node N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    Node insert(Node node, int key) {

        /* 1. 执行正常的BST插入 */
        if (node == null)
            return (new Node(key));

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else // 不允许重复键
            return node;

        /* 2. 更新这个祖先节点的高度 */
        node.height = 1 + max(height(node.left),
                height(node.right));

        /* 3. 获取这个祖先节点的平衡因子
            以检查这个节点是否变得不平衡 */
        int balance = getBalance(node);

        /** 情况1: 左左情况 (T1, T2, T3和T4是子树)
         *        z                                       y
         *       / \                                    /   \
         *      y   T4          右旋 (z)               x      z
         *     / \          - - - - - - - - ->      /  \    /  \
         *    x   T3                               T1  T2  T3  T4
         *   / \
         * T1   T2
         */
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        /** 情况2: 右右情况
         *    z                                y
         *   /  \                            /   \
         *  T1   y     Left Rotate(z)       z      x
         *      /  \   - - - - - - - ->    / \    / \
         *     T2   x                     T1  T2 T3  T4
         *         / \
         *       T3  T4
         */
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        /** 情况3: 左右情况
         *       z                               z                           x
         *      / \                            /   \                        /  \
         *     y   T4  Left Rotate (y)        x    T4  Right Rotate(z)    y      z
         *    / \      - - - - - - - - ->    /  \      - - - - - - - ->  / \    / \
         *  T1   x                          y    T3                    T1  T2 T3  T4
         *      / \                        / \
         *    T2   T3                    T1   T2
         */
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        /** 情况4: 右左情况
         *    z                            z                            x
         *   / \                          / \                          /  \
         * T1   y   Right Rotate (y)    T1   x      Left Rotate(z)   z      y
         *     / \  - - - - - - - - ->     /  \   - - - - - - - ->  / \    / \
         *    x   T4                      T2   y                  T1  T2  T3  T4
         *   / \                              /  \
         * T2   T3                           T3   T4
         */
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* 返回（未改变的）节点指针 */
        return node;
    }

    // 一个工具函数，用于打印树的前序遍历
    // 该函数还打印每个节点的高度
    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 25); 

        /** 构造出的AVL树如下
                30
                / \
               20  40
              / \	 \
             10 25   50
        */
        System.out.println("构造的树的前序遍历为：");
        tree.preOrder(tree.root);
    }
}
