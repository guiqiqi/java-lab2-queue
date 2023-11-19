package ru.spbstu.telematics.java;

/**
 * Deque interface's implementation using data structure LinkedList.
 */
public class ListDeque<E> extends ListQueue<E> implements Deque<E> {

    /**
     * Get head object of deque tail - which means head object of LinkedList.
     * Like <pre>peekLast</pre> method of Queue.
     * @return data object if exists, else null.
     */
    public E tail() {
        if (this.size() > 0)
            return this.head();
        return null;
    }

    /**
     * Try to get an object from deque tail - which means head of LinkedList.
     * Like <pre>removeLast</pre> method of Queue.
     * @return object if popped successfully from LinkedList, else null.
     */
    public E getTail() {
        try {
            return this.remove(0);
        } catch (IndexOutOfBoundsException error) {
            return null;
        }
    }

    /**
     * Put a new object into deque tail - which means insert it as head to LinkedList.
     * Like <pre>offerLast</pre> method of Queue.
     * @param data element going to be pushed.
     * @return if data object inserted or not.
     */
    public boolean putTail(E data) {
        if (this._maxsize > 0 && this.size() >= this._maxsize)
            return false;
        this.append(data);
        return true;
    }

}
