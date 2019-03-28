/*
 * Code used from the 7th Edition from Java Structures
 */

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import java.util.PriorityQueue;
import java.util.Vector;

public class VectorHeap<E extends Comparable<E>> implements iPriorityQueue<E> {

    private Vector<E> data; // the data, kept in heap order

    public VectorHeap()
    // post: constructs a new priority queue
    {
        data = new Vector<E>();
    }

    /**
     * @param v is a vector that will be converted on a VectorHeap, type Vector<E>
     */
    public VectorHeap(@NotNull Vector<E> v)
    // post: constructs a new priority queue from an unordered vector
    {
        int i;
        data = new Vector<E>(v.size()); // we know ultimate size
        for (i = 0; i < v.size(); i++) { // add elements to heap
            add(v.get(i));
        }
    }

    /**
     * @param i the index of the position, the type is int. The position can´t be greater than
     *          0 and lesser than the size of the size.
     * @return the position of the parent of the node at the location i
     */
    @Contract(pure = true)
    public static int parent(int i)
    // pre: 0 <= i < size
    // post: returns parent of node at location i
    {
        return (i - 1) / 2;
    }

    /**
     * @param i the index of the position, the type is int. The position can´t be greater than
     *          0 and lesser than the size of the size.
     * @return the position of the left child at the location i
     */
    @Contract(pure = true)
    public static int left(int i)
    // pre: 0 <= i < size
    // post: returns index of left child of node at location i
    {
        return 2 * i + 1;
    }

    /**
     * @param i the index of the position, the type is int. The position can´t be greater than
     *          0 and lesser than the size of the size.
     * @return the position of the right child at the location i
     */
    @Contract(pure = true)
    public static int right(int i)
    // pre: 0 <= i < size
    // post: returns index of right child of node at location i
    {
        return (2 * i + 1) + 1;
    }

    /**
     * @param leaf The number of the leaf inside the Vector, has to be int. The value of the leaf
     *             has to be greater than 0 and lesser than the size of the Vector.
     */
    public void percolateUp(int leaf)
    // pre: 0 <= leaf < size
    // post: moves node at index leaf up to appropriate position
    {
        int parent = parent(leaf);
        E value = data.get(leaf);
        while (leaf > 0 &&
                (value.compareTo(data.get(parent)) < 0)) {
            data.set(leaf, data.get(parent));
            leaf = parent;
            parent = parent(leaf);
        }
        data.set(leaf, value);
    }

    /**
     * @param value The value of any type that wants to be added to de Vector, the type can´t
     *              be null
     */
    @Override
    public void add(E value)
    // pre: value is non-null comparable
    // post: value is added to priority queue
    {
        data.add(value);
        percolateUp(data.size() - 1);
    }

    /**
     * @return true if the Vector is empty, false if it doesn´t
     */
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * @return The size of the Vector
     */
    @Override
    public int size() {
        return data.size();
    }

    /**
     * clears the Vector
     */
    @Override
    public void clear() {
        data.clear();

    }

    /**
     * @param root the position of the root in where it is, type int. Has
     *             to be greater than 0 and lesser than the size of the Vector
     */
    protected void pushDownRoot(int root)
    // pre: 0 <= root < size
    // post: moves node at index root down
    // to appropriate position in subtree
    {
        int heapSize = data.size();
        E value = data.get(root);
        while (root < heapSize) {
            int childpos = left(root);
            if (childpos < heapSize) {
                if ((right(root) < heapSize) &&
                        ((data.get(childpos + 1)).compareTo
                                (data.get(childpos)) < 0)) {
                    childpos++;
                }
                // Assert: childpos indexes smaller of two children
                if ((data.get(childpos)).compareTo
                        (value) < 0) {
                    data.set(root, data.get(childpos));
                    root = childpos; // keep moving down
                } else { // found right location
                    data.set(root, value);
                    return;
                }
            } else { // at a leaf! insert and halt
                data.set(root, value);
                return;
            }
        }
    }

    /*
     * @return the first element of the Vector
     */
    @Override
    public E getFirst() {
        return data.firstElement();
    }

    /**
     * @return The value with the highest priority
     * Removes the item with the highest priority
     */
    @Override
    public E remove()
    // pre: !isEmpty()
    // post: returns and removes minimum value from queue
    {
        E minVal = getFirst();
        data.set(0, data.get(data.size() - 1));
        data.setSize(data.size() - 1);
        if (data.size() > 1) pushDownRoot(0);
        return minVal;
    }

}
