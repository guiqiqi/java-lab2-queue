package ru.spbstu.telematics.java;

/**
 * Queue interface's implementation using data structure LinkedList.
 * Queue using LinkedList as data container, which grids like:
 * <pre>
 *     0 1 2 3 4 5 6 7
 *     ^             ^
 *     |             Tail of LinkedList and head of Queue
 *     |
 *     Head of LinkedList and tail of Queue
 * </pre>
 * Deque will extend this implementation.
 */
public class ListQueue<E> extends LinkedList<E> implements Queue<E> {

    final int _maxsize;  // Queue's maxsize.

    /**
     * Initialize queue with maxsize.
     * @param maxsize maxsize of queue.
     */
    public ListQueue(int maxsize) {
        this._maxsize = maxsize;
    }

    /**
     * Initialize queue's maxsize with -1 by default.
     */
    public ListQueue() {
        this._maxsize = -1;
    }

    /**
     * Give maxsize of current queue.
     * If queue is not size limited, return -1.
     * @return maxsize of current queue.
     */
    public int maxsize() {
        return this._maxsize;
    }

    /**
     * Get head object of queue - which means tail object of LinkedList.
     * Like <pre>peek</pre> method of Queue.
     * @return data object if exists, else null.
     */
    public E head() {
        if (this.size() > 0)
            return this.tail();
        return null;
    }

    /**
     * Put a new object into queue - which means insert it as head to LinkedList.
     * Like <pre>offer</pre> method of Queue.
     * @param data element going to be pushed.
     * @return if data object inserted or not.
     */
    public boolean put(E data) {
        if (this._maxsize > 0 && this.size() >= this._maxsize)
            return false;
        this.insert(data, 0);
        return true;
    }

    /**
     * Try to get an object from queue - which means tail of LinkedList.
     * Like <pre>remove</pre> method of Queue.
     * @return object if popped successfully from LinkedList, else null.
     */
    public E get() {
        try {
            return this.pop();
        } catch (IndexOutOfBoundsException error) {
            return null;
        }
    }

}
