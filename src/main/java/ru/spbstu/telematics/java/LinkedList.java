package ru.spbstu.telematics.java;

import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {
    /**
     * Node of LinkedList.
     * Maintains two pointers to previous node and next node.
     * Once this node is the first node in LinkedList, pointer of previous node will be set as null.
     * Once this node is the last node in LinkedList, pointer of next node will be set as null.
     * @param <E> Element type of data stored in Node.
     */
    private static class Node<E> {
        public Node<E> prev;
        public Node<E> next;
        public E data;

        public Node(E data, Node<E> prev, Node<E> next) {
            this.prev = prev;
            this.next = next;
            this.data = data;
        }

        /**
         * Partially initialize new node with previous and next node set to <pre>null</pre>.
         * @param data: data going to be stored in Node.
         */
        public Node(E data) {
            this(data, null, null);
        }
    }

    /**
     * Iterator of LinkedList.
     * Maintains a node currently accessing.
     */
    public static class NodeIterator<E> implements Iterator<E> {

        private Node<E> current;

        private NodeIterator(Node<E> current) {
            this.current = current;
        }

        /**
         * If current node reference to null, means we get into the last node of LinkedList.
         * @return true if current not have next node.
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Move current node step forward and return data stored in it.
         * @return data stored in current node.
         */
        @Override
        public E next() {
            E data = current.data;
            current = current.next;
            return data;
        }
    }

    private Node<E> _head;  // Head Node reference of LinkedList
    private Node<E> _last;  // Last Node reference of LinkedList
    private int _length;  // Length of LinkedList, DO NOT forget when changing LinkedList!

    public LinkedList() {
        this._head = null;
        this._last = null;
        this._length = 0;
    }

    /**
     * Locate node in LinkedList by its index starts from 0.
     * For locate it quicker:
     * <ul>
     *     <li>If given index closer to last position, using the last node and step backward;</li>
     *     <li>If given index closer to head position, using the head node and step forward.</li>
     * </ul>
     * <pre>
     *     Node:  A B C D E F...
     *     Index: 0 1 2 3 4 5...
     * </pre>
     * This method given O(N) performance in average - O(N/2) actually.
     * Called should ensure that index exists in LinkedList <pre>(index >= 0 && index < LinkedList.size())</pre>.
     * @param target node index.
     * @return node with given index.
     */
    private Node<E> indexNode(int target) {
        Node<E> current;
        if (target < this._length / 2) {
            int index = 0;
            current = this._head;
            while (index++ < target)
                current = current.next;
        } else {
            int index = this._length - 1;
            current = this._last;
            while (index-- > target)
                current = current.prev;
        }
        return current;
    }

    /**
     * Length maintained inside LinkedList with an int variable.
     * @return length of current LinkedList.
     */
    public int size() {
        return this._length;
    }

    /**
     * Insert a new Node with given data into last index of current LinkedList.
     * Same as calling <pre>LinkedList.insert(data, LinkedList.size())</pre>.
     * @param data data going to be store in LinkedList.
     */
    public void append(E data) {
        this.insert(data, this._length);
    }

    /**
     * Inserts a new created node with data BEFORE the node at the given index.
     * Behavior of this method:
     * <ul>
     *     <li>If current list is empty: the new created node will be set as both head and last node.</li>
     *     <li>If current list is not empty: the new created node will placed into given index.</li>
     * </ul>
     * @param data element going to be stored in LinkedList.
     * @param index index of storing, starts from zero.
     * @throws IndexOutOfBoundsException if given index greater than size of current LinkedList or smaller than 0.
     */
    public void insert(E data, int index) {
        // If given index greater than size of current LinkedList.
        if (index > this._length || index < 0)
            throw new IndexOutOfBoundsException(index);
        Node<E> node = new Node<>(data);

        // If current LinkedList empty and given index is 0, replace head and last nodes with new generated node.
        // Returns immediately to avoid execution below branches.
        if (this._head == null) {
            this._head = node;
            this._last = node;
            this._length = 1;
            return;
        }

        // If insert to index 0, means replace the head node.
        if (index == 0) {
            node.next = this._head;
            this._head.prev = node;
            this._head = node;
            this._length += 1;
            return;
        }

        // If insert to last index, means replace the last node.
        if (index == this._length) {
            this._last.next = node;
            node.prev = this._last;
            this._last = node;
            this._length += 1;
            return;
        }

        // Insert into given position
        Node<E> next = this.indexNode(index);
        Node<E> prev = next.prev;
        next.prev = node;
        prev.next = node;
        node.prev = prev;
        node.next = next;
        this._length += 1;
    }

    /**
     * Clear all elements current LinkedList.
     * We do not need to manually delete all created Node objects as in C++ (which JVM will concern).
     * So just reset the head and last pointer of current LinkedList and reset its length.
     */
    public void clear() {
        this._head = null;
        this._last = null;
        this._length = 0;
    }

    /**
     * Return first element stored in LinkedList.
     * @return first stored element.
     * @throws IndexOutOfBoundsException if current LinkedList empty.
     */
    public E head() {
        if (this._head == null)
            throw new IndexOutOfBoundsException(0);
        return this._head.data;
    }

    /**
     * Return last element stored in LinkedList.
     * @return last stored element.
     * @throws IndexOutOfBoundsException if current LinkedList empty.
     */
    public E tail() {
        if (this._head == null)
            throw new IndexOutOfBoundsException(0);
        return this._last.data;
    }

    /**
     * Retrieve data stored in LinkedList with index.
     * @param index index of target node.
     * @return data of node with given index.
     * @throws IndexOutOfBoundsException if index not exists in LinkedList.
     */
    public E get(int index) {
        if (index >= this._length || index < 0)
            throw new IndexOutOfBoundsException(index);
        Node<E> node = this.indexNode(index);
        return node.data;
    }

    /**
     * Remove node with given index.
     * @param index index of node going to be removed.
     * @return data stored in node with given index.
     * @throws IndexOutOfBoundsException if index not exists in LinkedList.
     */
    public E remove(int index) {
        if (index >= this._length || index < 0)
            throw new IndexOutOfBoundsException(index);

        // If current LinkedList only have 1 element, remove it and reset LinkedList.
        if (this._length == 1) {
            Node<E> orphan = this._head;
            this._head = null;
            this._last = null;
            this._length -= 1;
            return orphan.data;
        }

        // If index equals 0, means remove head node.
        if (index == 0) {
            Node<E> head = this._head;
            this._head = head.next;
            this._length -= 1;
            return head.data;
        }

        // If index equals <pre>LinkedList.size() - 1</pre>, means remove last node.
        if (index == this._length - 1) {
            Node<E> last = this._last;
            this._last = this._last.prev;
            this._last.next = null;
            this._length -= 1;
            return last.data;
        }

        // Remove located node
        Node<E> node = this.indexNode(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;
        prev.next = next;
        next.prev = prev;
        this._length -= 1;
        return node.data;
    }

    /**
     * Pop the last element of current LinkedList.
     * @return the last element stored in LinkedList.
     */
    public E pop() {
        return this.remove(this._length - 1);
    }

    /**
     * Check if data item exists in LinkedList.
     * @param data data object going to be checked
     * @return if given data object exists in LinkedList
     */
    public boolean contains(E data) {
        for (E item : this)
            if (item == data) return true;
        return false;
    }

    /**
     * Support of implementation <pre>Iterable<E></pre>.
     * @return Iterator of current LinkedList from head node.
     */
    public NodeIterator<E> iterator() {
        return new NodeIterator<>(this._head);
    }
}
