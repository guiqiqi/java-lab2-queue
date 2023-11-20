package ru.spbstu.telematics.java;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for LinkedList
 */
public class LinkedListTest extends TestCase {

    public static Test suite() {
        return new TestSuite( LinkedListTest.class );
    }

    public LinkedListTest( String testName ) {
        super( testName );
    }

    public void testInsert() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(0);
        list.append(2);
        list.append(3);
        list.insert(-1, 0);  // Insert into head, now length 4
        list.insert(1, 2);  // Insert into body, now length 5.
        list.insert(4, 5);  // Insert into tail, now length 6.

        // Check if all elements inserted.
        assertEquals(list.size(), 6);
        assertEquals(-1, (int) list.first());
        assertEquals(4, (int) list.last());
        assertEquals(1, (int) list.get(2));
    }

    public void testIterator() {
        LinkedList<Integer> list = new LinkedList<>();
        list.append(0);
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);

        // Check if iterator works functionally.
        int start_value = 0;
        for (Integer value : list)
            assertEquals(start_value++, (int) value);
    }

    public void testRemove() {
        LinkedList<Integer> list = new LinkedList<>();

        list.append(0);
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);

        assertEquals(0, (int) list.remove(0));  // Test remove head
        assertEquals(4, (int) list.pop());  // Test remove tail
        assertEquals(2, (int) list.remove(1));  // Test remove from body
        assertEquals(2, list.size());  // Test if really removed.
    }

    public void testClear() {
        LinkedList<Integer> list = new LinkedList<>();

        list.append(0);
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);

        list.clear();
        assertEquals(0, list.size());  // Test if list cleared
    }

    public void testContains() {
        LinkedList<Integer> list = new LinkedList<>();

        list.append(0);
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);

        assertTrue(list.contains(0));
        assertTrue(list.contains(2));
        assertFalse(list.contains(5));
    }
}
