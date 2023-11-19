package ru.spbstu.telematics.java;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ListDequeTest extends TestCase {

    public static Test suite() {
        return new TestSuite( LinkedListTest.class );
    }

    public ListDequeTest( String testName ) {
        super( testName );
    }

    public void testPutTailDeque() {
        Deque<Integer> deque = new ListDeque<>();
        deque.put(1);
        deque.put(2);
        deque.putTail(0);
        assertEquals(0, (int) deque.get());
        assertEquals(1, (int) deque.get());
        assertEquals(2, (int) deque.get());
    }

    public void testGetTailDeque() {
        Deque<Integer> deque = new ListDeque<>();
        deque.put(1);
        deque.put(2);
        deque.putTail(0);
        assertEquals(2, (int) deque.getTail());
    }

    public void testBehaviourWithStandardDeque() {
        Deque<Integer> deque_my_version = new ListDeque<>();
        java.util.Deque<Integer> deque_standard = new java.util.LinkedList<>();

        // Test put
        deque_my_version.put(1);
        deque_standard.offer(1);
        deque_my_version.putTail(2);
        deque_standard.offerLast(2);

        // Test head
        assertEquals(deque_standard.peek(), deque_my_version.head());

        // Test getTail
        assertEquals(deque_standard.pollLast(), deque_my_version.getTail());

        // Test size
        assertEquals(deque_standard.size(), deque_my_version.size());
    }

}
