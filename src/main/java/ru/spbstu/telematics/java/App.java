package ru.spbstu.telematics.java;

public class App {
    public static void main(String[] args) {
        Queue<Integer> queue = new ListQueue<>();
        queue.put(1);
        queue.put(2);
        queue.put(3);
        queue.put(4);
        for (Integer value: queue) {
            System.out.println(value);
        }
    }
}
