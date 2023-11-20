package ru.spbstu.telematics.java;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ListQueueTest extends TestCase {

    public static Test suite() {
        return new TestSuite( LinkedListTest.class );
    }

    public ListQueueTest( String testName ) {
        super( testName );
    }

    public void testMaxsize() {
        Queue<Integer> queue_limited = new ListQueue<>(5);
        assertEquals(5, queue_limited.maxsize());  // Check if maxsize set

        Queue<Integer> queue_unlimited = new ListQueue<>();
        assertEquals(-1, queue_unlimited.maxsize());  // Check if maxsize unlimited
    }

    public void testHeadInEmptyQueue() {
        Queue<Integer> queue = new ListQueue<>();
        assertNull(queue.head());
        assertNull(queue.get());
    }

    public void testHeadQueue() {
        Queue<Integer> queue = new ListQueue<>();
        queue.put(1);
        assertEquals(1, (int) queue.head());
        assertEquals(1, (int) queue.get());
    }

    /**
     * Check if out version got same behaviour with standard queue.
     */
    public void testBehaviourWithStandardQueue() {
        Queue<Integer> queue_my_version = new ListQueue<>();
        java.util.Queue<Integer> queue_standard = new java.util.LinkedList<>();

        // Test put
        queue_my_version.put(1);
        queue_standard.offer(1);
        queue_my_version.put(2);
        queue_standard.offer(2);
        queue_my_version.put(3);
        queue_standard.offer(3);

        // Test head
        assertEquals(queue_standard.peek(), queue_my_version.head());

        // Test get
        assertEquals(queue_standard.poll(), queue_my_version.get());

        // Test size
        assertEquals(queue_standard.size(), queue_my_version.size());

        // Test contains
        assertEquals(queue_standard.contains(1), queue_my_version.contains(1));
        assertEquals(queue_standard.contains(2), queue_my_version.contains(2));
    }
}
