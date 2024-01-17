package balanced_binary_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author swapnil
 * https://github.com/swapnil0399/2-3-Trees/tree/master
 */
public class TwoThreeTree {

    // Private member variables of the class
    private int size;
    private TreeNode root;
    private boolean successfulInsertion;
    private boolean successfulDeletion;
    private boolean split;
    private boolean underflow;
    private boolean first;
    private boolean singleNodeUnderflow;

    private enum Nodes {
        LEFT, MIDDLE, RIGHT, DUMMY;
    }

    // Default Constructor
    public TwoThreeTree() {
        // Initializing everything to default values
        size = 0;
        root = null;
        successfulInsertion = false;
        successfulDeletion = false;
        underflow = false;
        singleNodeUnderflow = false;
        split = false;
        first = false;
    } // constructor ends here

    // Node class
    private class Node {

    }

    // TreeNode class inherits from Node
    private class TreeNode extends Node {

        // Member variables
        int keys[];             // keys for searching
        Node children[];        // references to the 2 or 3 children
        int degree;             // number of children each node has 

        // Default constructor
        TreeNode() {
            // Initializing member variables
            keys = new int[2];          // keys[0] = min key in middle subtree
            children = new Node[3];     // references to children of leaves
            degree = 0;                 // for printing, overflow, and split operations
        } // constructor ends here

        // Print method
        void print() {

            if (degree == 1) {
                System.out.print("(-,-)");
            } else if (degree == 2) {
                System.out.print("(" + keys[0] + ",-) ");
            } else {
                System.out.print("(" + keys[0] + "," + keys[1] + ") ");
            }
        } // print() ends here
    } // TreeNode ends here

    // LeafNode class inherits from Node 
    private class LeafNode extends Node {

        int key;    // to store the value

        LeafNode(int key) {
            this.key = key;
        }

        // Print method
        void print() {
            System.out.print(key + " ");
        } // print() ends here
    } // LeafNode ends here

    // Merge Root: If the root node has to split, it merges it
    // back to a single TreeNode
    private void insertKey(int key) {
        // Create an array of Node of size 2
        // to capture the 2 nodes
        Node[] array = new Node[2];
        // Call the method to insert the key
        array = insert(key, root);

        // Check if the second value in the array is null or not
        if (array[1] == null) {
            // If it is null, it means there is no need to merge the array
            root = (TreeNode) array[0]; // Simply assign the root to the first value
        } else {
            // We got two values in the array
            // and now we have to merge those into a single node

            // Create a new TreeNode and attach first and second element in the
            // array as a reference in the newly created node
            TreeNode treeRoot = new TreeNode();
            treeRoot.children[0] = array[0];
            treeRoot.children[1] = array[1];
            updateTree(treeRoot);   // Update the new node
            root = treeRoot;        // Assign root to this node
        } // if else ends here
    } // insertKey() ends here

    // Insert: Returns an array of nodes(max 2 nodes)
    // after inserting the value 
    private Node[] insert(int key, Node n) {

        // Create a new Node array of size 2
        Node array[] = new Node[2];     // [0] old Node, [1] = new Node

        // This array of node stores the result after the recursive insert has returned
        Node catchArray[] = new Node[2];

        TreeNode t = null;  // Initialize t to null

        // If the node is a TreeNode
        if (n instanceof TreeNode) {
            t = (TreeNode) n;
        }

        // If the root is null, this means it is the first node
        if (root == null && !first) {
            first = true;   // Switch to make this if false for next recursive call

            // Create a new TreeNode
            TreeNode newNode = new TreeNode();
            t = newNode;
            // Call insert with the given value
            t.children[0] = insert(key, t.children[0])[0];
            updateTree(t);  // Update the tree

            // We will return this array
            // Make first element in the array store the reference of this TreeNode
            array[0] = t;
            array[1] = null;    // Make second element null

        } // If the node on which insert was called is a treeNode and
        // stores references to TreeNodes
        else if (t != null && !(t.children[0] instanceof LeafNode)) {
            // If the key to be inserted is less than the first key
            if (key < t.keys[0]) {
                // Recursively call insert on the left children node
                catchArray = insert(key, t.children[0]);
                // Store the reference returned by the call
                t.children[0] = catchArray[0];

                // If split has occurred
                if (split) {
                    // If the degree of current node is less than or equal to 2
                    if (t.degree <= 2) {
                        // Make split false we will handle it right here
                        split = false;
                        // Attach new nodes and update the tree
                        t.children[2] = t.children[1];
                        t.children[1] = catchArray[1];
                        updateTree(t);
                        array[0] = t;
                        array[1] = null;
                    } else if (t.degree > 2) {
                        // In this case, we are gonna pass the two nodes back
                        // up the chain
                        TreeNode newNode = new TreeNode();
                        newNode.children[0] = t.children[1];
                        newNode.children[1] = t.children[2];
                        updateTree(newNode);
                        t.children[1] = catchArray[1];
                        t.children[2] = null;
                        updateTree(t);
                        array[0] = t;
                        array[1] = newNode;
                    }
                } else {
                    // If there was no split simply update the tree and
                    // pass the reference up the tree
                    updateTree(t);
                    array[0] = t;
                    array[1] = null;
                } // if else for split ends here
            } // If for key < t.keys[0] ends here
            // If the key to be inserted is less than the first and greater than the second
            // or second is null
            else if (key >= t.keys[0] && (t.children[2] == null || key < t.keys[1])) {
                // Recursively call insert on the middle children node
                catchArray = insert(key, t.children[1]);
                // Store the reference returned by the call
                t.children[1] = catchArray[0];

                // If split has occurred
                if (split) {
                    // If the degree of current node is less than or equal to 2
                    if (t.degree <= 2) {
                        // Make split false we will handle it right here
                        split = false;
                        // Attach new nodes and update the tree
                        t.children[2] = catchArray[1];
                        updateTree(t);
                        array[0] = t;
                        array[1] = null;
                    } else if (t.degree > 2) {
                        // In this case, we are gonna pass the two nodes back
                        // up the chain hoping it will be handled there
                        TreeNode newNode = new TreeNode();
                        newNode.children[0] = catchArray[1];
                        newNode.children[1] = t.children[2];
                        updateTree(newNode);
                        t.children[2] = null;
                        updateTree(t);
                        array[0] = t;
                        array[1] = newNode;
                    }
                } else {
                    // If there was no split simply update the tree and
                    // pass the reference up the tree
                    updateTree(t);
                    array[0] = t;
                    array[1] = null;
                } // if else for split ends here
            } // If for key >= t.keys[0] ends here
            // If the key to be inserted is greater than second key
            else if (key >= t.keys[1]) {
                // Recursively call insert on the right children node
                catchArray = insert(key, t.children[2]);
                // Store the reference returned by the call
                t.children[2] = catchArray[0];

                // If split has occurred
                if (split) {
                    if (t.degree > 2) {
                        // In this case, we are gonna pass the two nodes back
                        // up the chain hoping it will be handled there
                        TreeNode newNode = new TreeNode();
                        newNode.children[0] = catchArray[0];
                        newNode.children[1] = catchArray[1];
                        updateTree(newNode);
                        t.children[2] = null;
                        updateTree(t);
                        array[0] = t;
                        array[1] = newNode;

                    }
                } else {
                    // If there was no split simply update the tree and
                    // pass the reference up the tree
                    updateTree(t);
                    array[0] = t;
                    array[1] = null;
                } // if else for split ends here
            } // If for key >= t.keys[1] ends here
        } // If the node on which insert was called is a treeNode and
        // stores references to LeafNodes
        else if (t != null && t.children[0] instanceof LeafNode) {
            // Just get the references of all the children
            LeafNode l1 = null, l2 = null, l3 = null;
            if (t.children[0] != null && t.children[0] instanceof LeafNode) {
                l1 = (LeafNode) t.children[0];
            }
            if (t.children[1] != null && t.children[1] instanceof LeafNode) {
                l2 = (LeafNode) t.children[1];
            }
            if (t.children[2] != null && t.children[2] instanceof LeafNode) {
                l3 = (LeafNode) t.children[2];
            }

            // If the degree of current TreeNode is less than or equal to 2
            if (t.degree <= 2) {
                // Simply find the place and insert the value
                if (t.degree == 1 && key > l1.key) {
                    LeafNode leaf = new LeafNode(key);
                    t.children[1] = leaf;
                } else if (t.degree == 1 && key < l1.key) {
                    LeafNode leaf = new LeafNode(key);
                    t.children[1] = l1;
                    t.children[0] = leaf;
                } else if (t.degree == 2 && key < l1.key) {
                    LeafNode leaf = new LeafNode(key);
                    t.children[2] = l2;
                    t.children[1] = l1;
                    t.children[0] = leaf;
                } else if (t.degree == 2 && key < l2.key && key > l1.key) {
                    LeafNode leaf = new LeafNode(key);
                    t.children[2] = l2;
                    t.children[1] = leaf;
                } else if (t.degree == 2) {
                    LeafNode leaf = new LeafNode(key);
                    t.children[2] = leaf;
                }

                // Upadate the tree and pass the array back
                updateTree(t);
                array[0] = t;
                array[1] = null;
            } // If the degree is greater than 2
            else if (t.degree > 2) {
                // Now we have to split the given treeNode
                split = true;
                // After split both nodes will have two children

                // Now separate the leaf nodes and attach it to the TreeNode and
                // pass the references of both the TreeNodes back
                if (key < l1.key) {
                    // If the new node to be inserted is less than the first key
                    // Store the new key node and the first key in one array and
                    // the remaining in the second array
                    // Pass both the array back
                    LeafNode leaf = new LeafNode(key);
                    TreeNode newNode = new TreeNode();
                    t.children[0] = leaf;
                    t.children[1] = l1;
                    t.children[2] = null;
                    updateTree(t);
                    newNode.children[0] = l2;
                    newNode.children[1] = l3;
                    updateTree(newNode);
                    array[0] = t;
                    array[1] = newNode;
                } else if (key >= l1.key && key < l2.key) {
                    // If the new node to be inserted is between the first key and the second key
                    // Store the new key node and the first key in one array and
                    // the remaining in the second array
                    // Pass both the array back
                    LeafNode leaf = new LeafNode(key);
                    TreeNode newNode = new TreeNode();
                    t.children[1] = leaf;
                    t.children[2] = null;
                    updateTree(t);
                    newNode.children[0] = l2;
                    newNode.children[1] = l3;
                    updateTree(newNode);
                    array[0] = t;
                    array[1] = newNode;
                } else if (key >= l2.key && key < l3.key) {
                    // If the new node to be inserted is between the third key and the second key
                    // Store the new key node and the third key in one array and
                    // the remaining in the second array
                    // Pass both the array back
                    LeafNode leaf = new LeafNode(key);
                    t.children[2] = null;
                    updateTree(t);
                    TreeNode newNode = new TreeNode();
                    newNode.children[0] = leaf;
                    newNode.children[1] = l3;
                    updateTree(newNode);
                    array[0] = t;
                    array[1] = newNode;
                } else if (key >= l3.key) {
                    // If the new node to be inserted is greater than the third key
                    // Store the new key node and the third key in one array and
                    // the remaining in the second array
                    // Pass both the array back
                    LeafNode leaf = new LeafNode(key);
                    t.children[2] = null;
                    updateTree(t);
                    TreeNode newNode = new TreeNode();
                    newNode.children[0] = l3;
                    newNode.children[1] = leaf;
                    updateTree(newNode);
                    array[0] = t;
                    array[1] = newNode;
                } // if else ends here
            } // outer else if ends here
            // If we have made it till here we have successfully inserted the value
            successfulInsertion = true;
        } else if (n == null) {
            // If the node was null it means there is no leafNode
            // Pass the reference to the new leafNode back
            successfulInsertion = true;
            array[0] = new LeafNode(key);
            array[1] = null;
            return array;
        }

        // Return the array
        return array;
    } // recursive insert ends here

    // This recursive method removes the given key from the tree
    private Node remove(int key, Node n) {

        // If the given node is the instance of a TreeNode
        TreeNode t = null;
        if (n instanceof TreeNode) {
            t = (TreeNode) n;
        }

        // If the node is null return null else continue
        if (n == null) {
            return null;
        }

        // If the children is the instance of TreeNode
        // This means we haven't reached the node where we want to perform delete
        if (t != null && t.children[0] instanceof TreeNode) {
            // If the given key to be deleted is less than the first key
            if (key < t.keys[0]) {
                // Perform deletion on first child with the same key
                t.children[0] = remove(key, t.children[0]);

                // If after deletion there is a degree 1 node in the tree
                if (singleNodeUnderflow) {
                    // Get the reference of both of the child nodes
                    TreeNode child = (TreeNode) t.children[0];
                    TreeNode rightChild = (TreeNode) t.children[1];

                    // If the right child has a degree of 2
                    // Attach the child to the rightchild and update the tree
                    if (rightChild.degree == 2) {
                        rightChild.children[2] = rightChild.children[1];
                        rightChild.children[1] = rightChild.children[0];
                        rightChild.children[0] = child;
                        updateTree(rightChild);
                        t.children[0] = rightChild;
                        t.children[1] = t.children[2];
                        t.children[2] = null;

                        // If the degree of the current TreeNode is 2
                        // then it will also underflow after this
                        if (t.degree == 2) {
                            singleNodeUnderflow = true;
                            t = (TreeNode) t.children[0];
                        } else {
                            singleNodeUnderflow = false;
                        }
                    } // If the right child has a degree of 3
                    // Borrow one child from rightchild and attach it to
                    // newly created tree node which has 2 children
                    // one a child from rightchild and the other child of 
                    // the Tree node which underflew
                    else if (rightChild.degree == 3) {
                        TreeNode newNode = new TreeNode();
                        newNode.children[0] = t.children[0];
                        newNode.children[1] = rightChild.children[0];
                        t.children[0] = newNode;
                        updateTree(newNode);
                        rightChild.children[0] = rightChild.children[1];
                        rightChild.children[1] = rightChild.children[2];
                        rightChild.children[2] = null;
                        updateTree(rightChild);
                        singleNodeUnderflow = false;
                    }
                } // if for singleNodeUnderflow ends here 
                // Else if there was an underflow
                else if (underflow) {
                    // We will handle it here
                    underflow = false;
                    // Get the references of children nodes
                    TreeNode child = (TreeNode) t.children[0];
                    TreeNode rightChild = (TreeNode) t.children[1];

                    // If the degree of rightchild is 3 then borrow one
                    // child from the rightchild and attach it to the child
                    if (rightChild.degree == 3) {
                        Node reference = rightChild.children[0];
                        rightChild.children[0] = rightChild.children[1];
                        rightChild.children[1] = rightChild.children[2];
                        rightChild.children[2] = null;
                        updateTree(rightChild);
                        child.children[1] = reference;
                        updateTree(child);
                    } // If the degree of rightchild is 2 then merge child
                    // and rightchild into one node
                    else if (rightChild.degree == 2) {
                        Node reference = child.children[0];
                        rightChild.children[2] = rightChild.children[1];
                        rightChild.children[1] = rightChild.children[0];
                        rightChild.children[0] = reference;
                        updateTree(rightChild);
                        t.children[0] = rightChild;

                        // Now if the degree of the treeNode was 3
                        // we are good this won't underflow after merge
                        if (t.degree == 3) {
                            t.children[1] = t.children[2];
                            t.children[2] = null;
                        } // But if it's less than 3, it will certainly underflow
                        // In this case we are gonna pass the problem up the tree
                        else {
                            Node ref = t.children[0];
                            t = (TreeNode) ref;
                            singleNodeUnderflow = true;
                        } // nested inner if else ends here
                    } // inner if else ends here
                } // outer if else ends here
                updateTree(t); // update the tree
            } // If the given key to be deleted is in between first and second key
            else if (key >= t.keys[0] && (t.children[2] == null || key < t.keys[1])) {
                // Perform deletion on second child with the same key
                t.children[1] = remove(key, t.children[1]);

                // If after deletion there is a degree 1 node in the tree
                if (singleNodeUnderflow) {

                    // Get references of all the child nodes
                    TreeNode leftChild = (TreeNode) t.children[0];
                    TreeNode child = (TreeNode) t.children[1];
                    TreeNode rightChild = (TreeNode) t.children[2];

                    // If the left child has a degree of 2
                    // Attach the child to the leftchild and update the tree
                    if (leftChild.degree == 2) {
                        leftChild.children[2] = child;
                        t.children[1] = rightChild;
                        t.children[2] = null;
                        updateTree(leftChild);

                        // If the degree of the current TreeNode is 2
                        // then it will also underflow after this merge
                        if (t.degree == 2) {
                            singleNodeUnderflow = true;
                            t = (TreeNode) t.children[0];
                        } else {
                            singleNodeUnderflow = false;
                        }
                    } // If the right child has a degree of 2
                    // Attach the child to the rightchild and update the tree
                    else if (rightChild != null && rightChild.degree == 2) {
                        rightChild.children[2] = rightChild.children[1];
                        rightChild.children[1] = rightChild.children[0];
                        rightChild.children[0] = child;
                        updateTree(rightChild);
                        t.children[1] = rightChild;
                        t.children[2] = null;
                        singleNodeUnderflow = false;

                    } // If the degree of leftchild is 3 then borrow one
                    // child from the leftchild and attach it to the newly
                    // created node which has a child of leftchild and the
                    // child which underflew
                    else if (leftChild.degree == 3) {
                        TreeNode newNode = new TreeNode();
                        newNode.children[0] = leftChild.children[2];
                        newNode.children[1] = child;
                        t.children[1] = newNode;
                        updateTree(newNode);
                        updateTree(leftChild);
                        singleNodeUnderflow = false;
                    } // If the degree of rightchild is 3 then borrow one
                    // child from the rightchild and attach it to the newly
                    // created node which has a child of rightchild and the
                    // child which underflew
                    else if (rightChild != null && rightChild.degree == 3) {
                        TreeNode newNode = new TreeNode();
                        newNode.children[0] = child;
                        newNode.children[1] = rightChild.children[0];
                        rightChild.children[0] = rightChild.children[1];
                        rightChild.children[1] = rightChild.children[2];
                        rightChild.children[2] = null;
                        t.children[1] = newNode;
                        updateTree(newNode);
                        updateTree(rightChild);
                        singleNodeUnderflow = false;
                    }
                } // Else if there was an underflow
                else if (underflow) {
                    // We will handle it here
                    underflow = false;

                    // Get the references of children nodes
                    TreeNode leftChild = (TreeNode) t.children[0];
                    TreeNode child = (TreeNode) t.children[1];
                    TreeNode rightChild = (TreeNode) t.children[2];

                    // If the degree of leftchild is 3 then borrow one
                    // child from the leftchild and attach it to the child
                    if (leftChild.degree == 3) {
                        Node reference = leftChild.children[2];
                        leftChild.children[2] = null;
                        child.children[1] = child.children[0];
                        child.children[0] = reference;
                        updateTree(leftChild);
                        updateTree(child);
                    } // If the degree of rightchild is 3 then borrow one
                    // child from the rightchild and attach it to the child
                    else if (rightChild != null && rightChild.degree == 3) {
                        Node reference = rightChild.children[0];
                        rightChild.children[0] = rightChild.children[1];
                        rightChild.children[1] = rightChild.children[2];
                        rightChild.children[2] = null;
                        updateTree(rightChild);
                        child.children[1] = reference;
                        updateTree(child);
                    } // If the degree of leftchild is 2 then merge child
                    // and rightchild into one node
                    else if (leftChild.degree == 2) {
                        Node reference = child.children[0];
                        leftChild.children[2] = reference;
                        updateTree(leftChild);
                        t.children[1] = null;

                        // Now if the degree of the treeNode is 3
                        // we are good this won't underflow after merge
                        if (t.degree == 3) {
                            t.children[1] = t.children[2];
                            t.children[2] = null;
                        } // But if it's less than 3, it will certainly underflow
                        // In this case we are gonna pass the problem up the tree
                        else {
                            singleNodeUnderflow = true;
                            t = (TreeNode) t.children[0];
                        }
                    } // If the degree of rightchild is 2 then merge child
                    // and rightchild into one node
                    else if (rightChild != null && rightChild.degree == 2) {
                        Node reference = child.children[0];
                        rightChild.children[2] = rightChild.children[1];
                        rightChild.children[1] = rightChild.children[0];
                        rightChild.children[0] = reference;
                        updateTree(rightChild);
                        t.children[1] = rightChild;
                        t.children[2] = null;
                        singleNodeUnderflow = false;
                    } // inner if else ends here
                } // outer if else ends here
                updateTree(t); // update the tree
            } // If the given key to be deleted is greater than the second key
            else if (key >= t.keys[1]) {
                // Perform deletion on third child with the same key
                t.children[2] = remove(key, t.children[2]);

                // If after deletion there is a degree 1 node in the tree
                if (singleNodeUnderflow) {
                    // Get the reference of both of the child nodes
                    TreeNode child = (TreeNode) t.children[2];
                    TreeNode leftChild = (TreeNode) t.children[1];

                    // If the left child has a degree of 2
                    // Attach the child to the leftchild and update the tree
                    if (leftChild.degree == 2) {
                        leftChild.children[2] = child;
                        t.children[2] = null;
                        updateTree(leftChild);
                    } // If the left child has a degree of 3
                    // Borrow one child from leftchild and attach it to
                    // newly created tree node which has 2 children
                    // one a child from leftchild and the other child of 
                    // the Tree node which underflew
                    else if (leftChild.degree == 3) {
                        TreeNode newNode = new TreeNode();
                        newNode.children[0] = leftChild.children[2];
                        newNode.children[1] = t.children[2];
                        t.children[2] = newNode;
                        updateTree(newNode);
                        updateTree(leftChild);
                    }
                    // We have handled singleNodeUnderflow here
                    singleNodeUnderflow = false;
                } // Else if there was an underflow
                else if (underflow) {
                    // We will handle it here
                    underflow = false;
                    // Get the references of children nodes
                    TreeNode leftChild = (TreeNode) t.children[1];
                    TreeNode child = (TreeNode) t.children[2];

                    // If the degree of leftchild is 3 then borrow one
                    // child from the leftchild and attach it to the child
                    if (leftChild.degree == 3) {
                        Node reference = leftChild.children[2];
                        leftChild.children[2] = null;
                        child.children[1] = child.children[0];
                        child.children[0] = reference;
                        updateTree(leftChild);
                        updateTree(child);
                    } // If the degree of leftchild is 2 then merge child
                    // and leftchild into one node
                    else if (leftChild.degree == 2) {
                        Node reference = child.children[0];
                        leftChild.children[2] = reference;
                        updateTree(leftChild);
                        t.children[2] = null;
                    } // inner if else ends here
                } // outer if else ends here
                updateTree(t); // update the tree
            } // outer outer if else ends here
        } // If the tree node has its children as instance of leaf node
        else if (t != null && t.children[0] instanceof LeafNode) {

            // Get the reference of all the leaves in leaf node objects
            LeafNode l1 = null, l2 = null, l3 = null;
            if (t.children[0] != null && t.children[0] instanceof LeafNode) {
                l1 = (LeafNode) t.children[0];
            }
            if (t.children[1] != null && t.children[1] instanceof LeafNode) {
                l2 = (LeafNode) t.children[1];
            }
            if (t.children[2] != null && t.children[2] instanceof LeafNode) {
                l3 = (LeafNode) t.children[2];
            }

            // If all the three leaves are present in the tree node
            if (t.degree == 3) {
                // Find which key to be deleted and adjust the leaves to maintain the property of the tree
                if (key == l1.key) {
                    t.children[0] = l2;
                    t.children[1] = l3;
                    t.children[2] = null;
                } else if (key == l2.key) {
                    t.children[1] = l3;
                    t.children[2] = null;
                } else if (key == l3.key) {
                    t.children[2] = null;
                }
                // Update tree after deletion
                updateTree(t);
            } // If there are just two leaves present in the tree node
            else if (t.degree == 2) {
                // The given node is going to underflow after deletion
                underflow = true;

                // Find the key to be deleted and adjust the leaves to maintain the property of the tree
                if (l1.key == key) {
                    t.children[0] = l2;
                    t.children[1] = null;
                } else if (l2.key == key) {
                    t.children[1] = null;
                }
            } // If there is just one leaf node in the tree node
            else if (t.degree == 1) {
                // Just delete the key if it matches the key to be deleted
                if (l1.key == key) {
                    t.children[0] = null;
                }
            } // inner if else ends here

            // The deletion is successful
            successfulDeletion = true;
        } // outer if else ends here
        return t;
    } // remove() ends here

    // This mehtod updates the info stored in the TreeNodes
    private void updateTree(TreeNode t) {

        // If t is not null
        if (t != null) {
            // Check how many children does the current node has
            if (t.children[2] != null && t.children[1] != null && t.children[0] != null) {
                // If it has three children
                t.degree = 3;
                // Get values for both the keys
                t.keys[0] = getValueForKey(t, Nodes.LEFT);
                t.keys[1] = getValueForKey(t, Nodes.RIGHT);
            } else if (t.children[1] != null && t.children[0] != null) {
                // If it has two children
                t.degree = 2;
                // Get value for the first key
                t.keys[0] = getValueForKey(t, Nodes.LEFT);
                t.keys[1] = 0;
            } else if (t.children[0] != null) {
                // If it has a single child
                t.degree = 1;
                // Make both keys equal to 0
                t.keys[1] = t.keys[0] = 0;
            } // if else ends here
        } // outer if ends here
    } // updateTree() ends here

    // This method returns the value for the keys of the TreeNode
    private int getValueForKey(Node n, Nodes whichVal) {
        // Initialize key to -1
        int key = -1;

        // To determine the type of the node
        TreeNode t = null;
        LeafNode l = null;
        if (n instanceof TreeNode) {
            t = (TreeNode) n;
        } else {
            l = (LeafNode) n;
        }

        // If it is a leaf node just return the key
        if (l != null) {
            key = l.key;
        }
        // If it is a TreeNode
        if (t != null) {
            // Check for which key we want to get value
            if (null != whichVal) {
                switch (whichVal) {
                    // If it is the left key
                    case LEFT:
                        // Go in the middle node and then keep going towards
                        // 1st children using recursive calls
                        key = getValueForKey(t.children[1], Nodes.DUMMY);
                        break;
                    // If it is the right key
                    case RIGHT:
                        // Go in the right node and then keep going towards
                        // 1st children using recursive calls
                        key = getValueForKey(t.children[2], Nodes.DUMMY);
                        break;
                    // This case executes after we have determined which node
                    // we want to go for
                    case DUMMY:
                        // Way to 1st children of the treeNode
                        key = getValueForKey(t.children[0], Nodes.DUMMY);
                        break;
                    default:
                        break;
                } // switch ends here
            } // inner if ends here
        } // outer if ends here

        // Return value for the key
        return key;
    } // getValueForKey() ends here

    // This method searches the given key in the tree
    private boolean search(int key, Node n) {
        // Let found to be false
        boolean found = false;

        // Determine the type of node
        TreeNode t = null;
        LeafNode l = null;
        if (n instanceof TreeNode) {
            t = (TreeNode) n;
        } else {
            l = (LeafNode) n;
        }

        // If it is a TreeNode
        if (t != null) {
            // If it has a degree of 1
            if (t.degree == 1) {
                // Continue search in left node
                found = search(key, t.children[0]);
            } // If it has a degree of 2 and the value of key is less than 1st key
            else if (t.degree == 2 && key < t.keys[0]) {
                // Continue search in left node
                found = search(key, t.children[0]);
            } // If it has a degree of 2 and the value of key is greater than 1st key
            else if (t.degree == 2 && key >= t.keys[0]) {
                // Continue search in middle node
                found = search(key, t.children[1]);
            } // If it has a degree of 3 and the value to be searched for is less than 1st key
            else if (t.degree == 3 && key < t.keys[0]) {
                // Continue search in left node
                found = search(key, t.children[0]);
            } // If it has a degree of 3 and the value to be searched for is in between 1st and 2nd key
            else if (t.degree == 3 && key >= t.keys[0] && key < t.keys[1]) {
                // Continue search in middle node
                found = search(key, t.children[1]);
            } // If it has a degree of 3 and the value to be searched for is greater than 2nd key
            else if (t.degree == 3 && key >= t.keys[1]) {
                // Continue search in right node
                found = search(key, t.children[2]);
            } // inner if else ends here
        } // If it is a leaf node and value matches the one we are searching
        else if (l != null && key == l.key) {
            return true;
        } // outer if else ends here

        return found;
    } // search() ends here

    // This method prints the keys stored in the leafNodes in order
    private void keyOrderList(Node n) {

        // Determine the type of node
        TreeNode t = null;
        LeafNode l = null;
        if (n instanceof TreeNode) {
            t = (TreeNode) n;
        } else {
            l = (LeafNode) n;
        }

        // If it is a TreeNode
        if (t != null) {
            // If the first child is not null
            if (t.children[0] != null) {
                // Continue recursive call on 1st child
                keyOrderList(t.children[0]);
            }

            // If the second child is not null
            if (t.children[1] != null) {
                // Continue recursive call on 2nd child
                keyOrderList(t.children[1]);
            }

            // If the third child is not null
            if (t.children[2] != null) {
                // Continue recursive call on 3rd child
                keyOrderList(t.children[2]);
            }
        } // If it is LeafNode
        else if (l != null) {
            // Just print the given key
            System.out.print(l.key + " ");
        } // outer if else ends here
    } // keyOrderList() ends here

    // This method just prints the keys on each level
    private void bfsList(Node n) {
        // Make two new queues to store each alternate levels in the queue
        Queue<Node> queueOne = new LinkedList<>();
        Queue<Node> queueTwo = new LinkedList<>();

        // If the first node is null quit else continue
        if (n == null) {
            return;
        }

        // Add the first node to the first queue
        queueOne.add(n);

        // Just declare two Node variables to hold the nodes
        Node first = null;
        TreeNode t = null;

        // Loop until both the queues are non-empty
        while (!queueOne.isEmpty() || !queueTwo.isEmpty()) {

            // If the first queue is not empty
            while (!queueOne.isEmpty()) {
                // Take the first element from the queue
                first = queueOne.poll();
                // If it is a tree node print the node 
                if (first instanceof TreeNode) {
                    t = (TreeNode) first;
                    t.print();
                }

                // Now add all the children on the next level to second queue
                if (t.children[0] != null && !(t.children[0] instanceof LeafNode)) {
                    queueTwo.add(t.children[0]);
                }
                if (t.children[1] != null && !(t.children[1] instanceof LeafNode)) {
                    queueTwo.add(t.children[1]);
                }
                if (t.children[2] != null && !(t.children[2] instanceof LeafNode)) {
                    queueTwo.add(t.children[2]);
                }

            } // first inner loop ends here
            // Leave a blank line after printing a level
            if (!queueOne.isEmpty() || !queueTwo.isEmpty()) {
                System.out.println();
            }

            // If the second queue is not empty
            while (!queueTwo.isEmpty()) {
                // Take the first element from the queue
                first = queueTwo.poll();

                // If it is a tree node print the node
                if (first instanceof TreeNode) {
                    t = (TreeNode) first;
                    t.print();
                }

                // Now add all the children on the next level to first queue
                if (t.children[0] != null && !(t.children[0] instanceof LeafNode)) {
                    queueOne.add(t.children[0]);
                }
                if (t.children[1] != null && !(t.children[1] instanceof LeafNode)) {
                    queueOne.add(t.children[1]);
                }
                if (t.children[2] != null && !(t.children[2] instanceof LeafNode)) {
                    queueOne.add(t.children[2]);
                }

            } // second inner loop ends here
            // Leave a blank line after printing a level
            if (!queueOne.isEmpty() || !queueTwo.isEmpty()) {
                System.out.println();
            }
        } // outer loop ends here

        // Print blank lines before and after printing the keys 
        System.out.println();
        keyOrderList(root);
        System.out.println();
    } // bfsList() ends here

    // This method determines the height of the tree
    private int height(Node n) {

        // Determine the type of the node
        TreeNode t = null;
        LeafNode l = null;
        if (n instanceof TreeNode) {
            t = (TreeNode) n;
        } else {
            l = (LeafNode) n;
        }

        // If the node is a Tree node and is not null
        if (t != null) {
            // Calculate height of left child and add 1
            return 1 + height(t.children[0]);
        }

        return 0;
    } // height() ends here

    // This is a public method to call insert
    public boolean insert(int key) {
        // Let insert to false
        boolean insert = false;
        // make all the variables related to insert have their default values
        split = false;

        // Check if the key is present or not in the tree
        if (!search(key)) {
            // If it is not there insert it
            insertKey(key);
        }

        // If the insertion was successful
        if (successfulInsertion) {
            // Increment the size and return success
            size++;
            insert = successfulInsertion;
            successfulInsertion = false;
        }
        return insert;
    } // insert() ends here

    // This is a public method to call search
    public boolean search(int key) {
        // Just return the search result
        return search(key, root);
    } // search() ends here

    // This is a public method to call remove
    public boolean remove(int key) {
        // Assign all the variables related to remove to their default values
        boolean delete = false;
        singleNodeUnderflow = false;
        underflow = false;

        // If the element is in the tree
        if (search(key)) {
            // Delete the element
            root = (TreeNode) remove(key, root);

            // If the root has a degree of 1
            if (root.degree == 1 && root.children[0] instanceof TreeNode) {
                // Make root's first children as the root
                root = (TreeNode) root.children[0];
            } // if ends here
        }

        // If the deletion was successful
        if (successfulDeletion) {
            // Decrease the size by 1 and return success 
            size--;
            delete = successfulDeletion;
            successfulDeletion = false;
        } // if ends here

        return delete;
    } // remove() ends here

    // This is a public method to call keyOrderList
    public void keyOrderList() {
        // Format the output and call the method
        System.out.println("Keys");
        keyOrderList(root);
        System.out.println();
    } // keyOrderlist() ends here

    // This is a public method to call bfsList
    public void bfsList() {
        // Format the output and call the method
        System.out.println("Tree");
        bfsList(root);
    } // bfsList() ends here

    // This method returns the number of the keys stored in the tree
    public int numberOfNodes() {
        // Return the number of values present in the tree
        return size;
    } // numberOfNodes() ends here

    // This is a public method to call height
    public int height() {
        // Return the height of the tree
        return height(root);
    } // height() ends here

} // TwoThreeTree class definition ends here
