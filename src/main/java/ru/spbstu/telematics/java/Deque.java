package ru.spbstu.telematics.java;

/**
 * Deque interface.
 * @param <E> contained object type.
 */
public interface Deque<E> extends Queue<E> {
    /**
     * Return and remove last element in current queue.
     * @return last element reference.
     */
    E getTail();

    /**
     * Put a new element into queue's head.
     * @param data element going to be pushed.
     * @return if put operation succeed.
     */
    boolean putHead(E data);
}
