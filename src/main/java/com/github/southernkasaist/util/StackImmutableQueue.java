package com.github.southernkasaist.util;

import java.util.NoSuchElementException;

/**
 * Implementation of immutable queue based on {@link LinkedImmutableStack}.
 *
 * @param <T> The underlying element.
 */
final class StackImmutableQueue<T> implements Queue<T> {
    /**
     * The dequeue stack stores outgoing elements in FIFO order.
     * This stack should not be empty when the whole queue is not empty, which means
     * we must always reverse "in" stack to become "out" stack when "out" stack is empty.
     */
    private final Stack<T> out;

    /**
     * The enqueue stack stores incoming elements in FILO order.
     */
    private final Stack<T> in;

    StackImmutableQueue() {
        this(new LinkedImmutableStack<>(), new LinkedImmutableStack<>());
    }

    private StackImmutableQueue(Stack<T> out, Stack<T> in) {
        this.out = out;
        this.in = in;
    }

    /**
     * Enqueue element.
     * As we have to make sure the "out" stack be non-empty, when "out" stack is empty,
     * we need to reverse the "in" stack to be "out" stack with the new element.
     * Time complexity: O(1) in average.
     *
     * @param t New element, non-null.
     *
     * @return The new immutable queue.
     */
    @Override
    public Queue<T> enQueue(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Null element is not allowed.");
        }
        if (out.isEmpty()) {
            // ensure out stack is non-empty
            return new StackImmutableQueue<>(in.reverse().push(t), new LinkedImmutableStack<>());
        } else {
            return new StackImmutableQueue<>(out, in.push(t));
        }
    }

    /**
     * Dequeue element.
     * As we have to make sure the "out" stack be non-empty, when "out" stack is empty after popping
     * top element, we need to reverse the "in" stack to be "out" stack and pop the top element.
     * Time complexity: O(1) in average.
     *
     * @return The new immutable queue.
     */
    @Override
    public Queue<T> deQueue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Use isEmpty to check first.");
        }
        if (out.isEmpty()) {
            // ensure out stack is non-empty
            return new StackImmutableQueue<>(in.reverse().pop(), new LinkedImmutableStack<>());
        } else {
            final Stack<T> newOut = out.pop();
            if (newOut.isEmpty()) {
                // ensure out stack is non-empty
                return new StackImmutableQueue<>(in.reverse(), new LinkedImmutableStack<>());
            } else {
                return new StackImmutableQueue<>(newOut, in);
            }
        }
    }

    @Override
    public T head() {
        if (isEmpty()) {
            throw new NoSuchElementException("Use isEmpty to check first.");
        }
        return out.peek();
    }

    @Override
    public boolean isEmpty() {
        return out.isEmpty() && in.isEmpty();
    }
}
