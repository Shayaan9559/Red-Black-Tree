/**
 * RBT
 * Red-Black Tree Insert
 * @author SS2697
 */
import java.util.*;

import org.w3c.dom.Node;


public class RBT {
    public Node root;

    public RBT() {}

    public boolean isRed(Node x) {
        if (x == null) return false;
        return x.getColor() == Node.Color.RED;
    }
    
    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(int x) {
        return nodeContainsData(root, x);
    }

    private boolean nodeContainsData(Node r, int x) {
        while (r != null) {
            if (r.getData() - x < 0) {
                r = r.getLeft();
            } else if (r.getData() - x > 0) {
                r = r.getRight();
            } else {
                return true;
            }
        }
        return false;
    }

    public List<Integer> serializeTree() {
        return serializeTree(root);
    }

    private List<Integer> serializeTree(Node r) {
        if (r == null) return new LinkedList<>();
        int data = r.getData();
        List<Integer> left = serializeTree(r.getLeft());
        List<Integer> right = serializeTree(r.getRight());
        left.add(data);
        left.addAll(right);
        return left;
    }

    public int maxHeight() {
        return maxHeight(root);
    }

    private int maxHeight(Node r) {
        if (r==null) return 0;        
        return 1 + Math.max(maxHeight(r.getLeft()), maxHeight(r.getRight()));
    }

    // ************************************************************************
    // * INSERT INTO RED-BLACK TREE
    // ************************************************************************
    
    
/**
* Inserts a new node with the given value into the Red-Black Tree.
*
* @param x The value to be inserted into the Red-Black Tree.
*/
    public void insert(int x) {
        root = nodeInsertData(root, x);
        root.setColor(Node.Color.BLACK);
    }
/**
 * Recursively inserts a new node with the given value into the Red-Black Tree.
 *
 * @param r The current node being considered during the insertion process.
 * @param x The value to be inserted into the Red-Black Tree.
 * @return The root of the modified Red-Black Tree after insertion.
 */
    private Node nodeInsertData(Node r, int x) {
        //if current node is null it will create a new one.
        if (r == null) {
            return new Node(x, Node.Color.RED);
        } 
        else{
            //if x is less than the data in the current node it will insert it in the left subtree.
            if(x < r.getData()){
                r.setLeft(nodeInsertData(r.getLeft(), x));
            }
            //if x is greater than the data in the current node it will insert it in the right subtree.
            else if(x > r.getData()){
                r.setRight(nodeInsertData(r.getRight(), x));
            }
        }
        
        r = fixTree(r);
        return r;
    }
/**
 * This method is used to fix the Red-Black Tree after any modifications such as insertions or deletions.
 * It ensures that the tree maintains its properties:
 * 1. Every node is either red or black.
 * 2. The root is black.
 * 3. All leaves (null) are black.
 * 4. If a node is red, then both its children are black.
 * 5. Every path from a node to its descendant leaves contains the same number of black nodes.
 *
 * @param r The root node of the tree or subtree that needs to be fixed.
 * @return The root node of the fixed tree or subtree.
 */

private Node fixTree(Node r) {
        if (isRed(r.getLeft())){
            if (isRed(r.getLeft().getLeft()) || isRed(r.getLeft().getRight()))
            {
                if (isRed(r.getRight()))
                {
                    flipColors(r);
                }
                else if (isRed(r.getLeft().getRight())) {
                    r.setLeft(rotateLeft(r.getLeft()));

                }
                r = rotateRight(r);
            }
        }

        if (isRed(r.getRight())) {
            if (isRed(r.getRight().getRight()) || isRed(r.getRight().getLeft()))
            {
                if (isRed(r.getLeft()))
                {
                    flipColors(r);
                }
                else if (isRed(r.getRight().getLeft()))
                {
                    r.setRight(rotateRight(r.getRight()));
                }
                r = rotateLeft(r);
            }
        }

        return r;
    }

/**
 * Rotates a given node to the right, fixing Red-Black Tree violations.
 *
 * @param currentNode The node to be rotated.
 * @return The new root of the subtree after rotation.
 */

private Node rotateRight(Node h) {
    if (h == null || h.getLeft() == null || !isRed(h.getLeft())) {
        return h;
    }
    Node x = h.getLeft();
    h.setLeft(x.getRight());
    x.setRight(h);
    x.setColor(h.getColor());
    h.setColor(Node.Color.RED);
    return x;
}

/**
 * Rotates a given node to the left, fixing Red-Black Tree violations.
 *
 * @param h The node to be rotated.
 * @return The new parent node after rotation.
 */
private Node rotateLeft(Node h) {
    if (h == null || h.getRight() == null || !isRed(h.getRight())) {
        return h;
    }
    Node x = h.getRight();
    h.setRight(x.getLeft());
    x.setLeft(h);
    x.setColor(h.getColor());
    h.setColor(Node.Color.RED);
    return x;

}

/**
 * Helper method to update colors after rotation.
 *
 * @param oldRoot The original root before rotation.
 * @param newRoot The new root after rotation.
 */
    private void updateColorsAfterRotation(Node oldRoot, Node newRoot) {
        newRoot.setColor(oldRoot.getColor());
        oldRoot.setColor(Node.Color.RED);
    }
        
/**
 * Flips the colors of a node and its two children, fixing Red-Black Tree violations.
 *
 * @param node The node to have its colors flipped.
 */
    private void flipColors(Node node) {
        if (node != null) {
            node.setColor(Node.Color.RED);
            setChildColor(node.getLeft(), Node.Color.BLACK);
            setChildColor(node.getRight(), Node.Color.BLACK);
        }   
        }
/**
 * Sets the color of a child node to the specified color.
 *
 * @param child The child node whose color is to be set.
 * @param color The color to set for the child node.
 */
        private void setChildColor(Node child, Node.Color color) {
            if (child != null) {
                child.setColor(color);
            }
        }
    }
