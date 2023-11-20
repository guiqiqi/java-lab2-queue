package ru.spbstu.telematics.java;

/**
 * Deque interface's implementation using data structure LinkedList.
 */
public class ListDeque<E> extends ListQueue<E> implements Deque<E> {
    /**
     * Try to get an object from deque tail - which means tail of LinkedList.
     * Like <pre>removeFirst</pre> method of Queue.
     * @return object if popped successfully from LinkedList, else null.
     */
    public E getTail() {
        try {
            return this.pop();
        } catch (IndexOutOfBoundsException error) {
            return null;
        }
    }

    /**
     * Put a new object into deque head - which means insert it as head to LinkedList.
     * Like <pre>offerFirst</pre> method of Queue.
     * @param data element going to be pushed.
     * @return if data object inserted or not.
     */
    public boolean putHead(E data) {
        if (this._maxsize > 0 && this.size() >= this._maxsize)
            return false;
        this.insert(data, 0);
        return true;
    }

}
