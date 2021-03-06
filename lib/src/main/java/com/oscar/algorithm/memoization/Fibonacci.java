package com.oscar.algorithm.memoization;

import java.util.Map;

public class Fibonacci {

    public long fibonacci(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public long memoizedFibonacci(int n, Map<Integer, Long> processed) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        if (!processed.containsKey(n)) {
            processed.put(n, memoizedFibonacci(n - 1, processed) + memoizedFibonacci(n - 2, processed));
        }

        return processed.get(n);
    }
}
