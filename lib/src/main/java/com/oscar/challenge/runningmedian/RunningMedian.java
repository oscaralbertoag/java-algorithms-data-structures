package com.oscar.challenge.runningmedian;

import java.util.PriorityQueue;

public class RunningMedian {

    private final PriorityQueue<Integer> maxHeap;
    private final PriorityQueue<Integer> minHeap;
    private double runningMedian;

    public RunningMedian() {
        this.maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        this.minHeap = new PriorityQueue<>();
        this.runningMedian = 0;
    }

    public void add(int toAdd) {
        if (toAdd < runningMedian) {
            maxHeap.add(toAdd);
        } else {
            minHeap.add(toAdd);
        }
        balance();
    }

    private void balance() {
        // Balance if the difference is greater than 1 element on any side
        if (Math.abs(minHeap.size() - maxHeap.size()) > 1) {
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.poll());
            } else {
                minHeap.add(maxHeap.poll());
            }
        }
    }

    public double getRunningMedian() {
        if (minHeap.size() == maxHeap.size() && minHeap.size() > 0) {
            runningMedian = (minHeap.peek() + maxHeap.peek()) / 2.0;
        } else if (minHeap.size() > maxHeap.size()) {
            runningMedian = minHeap.peek();
        } else if (maxHeap.size() > minHeap.size()) {
            runningMedian = maxHeap.peek();
        }
        return runningMedian;
    }
}
