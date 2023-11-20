package ru.spbstu.telematics.java;

public class App {
    public static void main(String[] args) {
        Deque<Integer> deque = new ListDeque<>();
        System.out.println("Put value to Deque tail: 1");
        deque.put(1);
        System.out.println("Put value to Deque head: 0");
        deque.putHead(0);
        System.out.println("Put value to Deque tail: 2");
        deque.put(2);
        for (Integer value : deque)
            System.out.printf("Get value from Deque: %s %n", value);
    }
}
