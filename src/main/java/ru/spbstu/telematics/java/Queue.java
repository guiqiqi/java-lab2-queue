package ru.spbstu.telematics.java;

/**
 * Deque interface.
 * @param <E> contained object type.
 */
public interface Queue<E> extends Iterable<E> {

    /**
     * Head element of current queue.
     * @return head element of current queue.
     */
    E head();

    /**
     * Tail element of current queue.
     * @return tail element of current queue.
     */
    E tail();

    /**
     * Remove and return head element of current queue.
     * @return head element of current queue.
     */
    E get();

    /**
     * Put a new element into queue's tail.
     * @param data element going to be pushed.
     * @return if put operation succeed.
     */
    boolean put(E data);

    /**
     * Queue size.
     * @return numbers of element inside queue.
     */
    int size();

    /**
     * Check if queue contains given object.
     * @param data item going to be checked existing.
     * @return if exists in queue.
     */
    boolean contains(E data);

    /**
     * Return queue's maxsize.
     * @return maxsize of current queue if size limited, otherwise -1.
     */
    int maxsize();
}
