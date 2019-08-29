package com.github.southernkasaist.util;

import java.util.NoSuchElementException;

/**
 * Implementation of immutable stack based on linked nested immutable stacks.
 *
 * @param <T> The underlying element.
 */
final class LinkedImmutableStack<T> implements Stack<T> {
    /**
     * The top element value.
     */
    private final T val;

    /**
     * The linked stack of elements pushed before top element.
     */
    private final Stack<T> next;

    LinkedImmutableStack() {
        this.val = null;
        this.next = null;
    }

    private LinkedImmutableStack(T val, Stack<T> next) {
        this.val = val;
        this.next = next;
    }

    /**
     * When pushes a new element into the stack, nest original stack and add new element as new top.
     * Time complexity: O(1).
     *
     * @param t New element, non-null.
     *
     * @return The new stack.
     */
    @Override
    public Stack<T> push(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Null element is not allowed.");
        }
        return new LinkedImmutableStack<>(t, this);
    }

    /**
     * When popes the top element, return the nested stack as new one.
     * Time complexity: O(1).
     *
     * @return The nested stack.
     */
    @Override
    public Stack<T> pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Use isEmpty to check first.");
        }
        return this.next;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Use isEmpty to check first.");
        }
        return this.val;
    }

    @Override
    public boolean isEmpty() {
        return this.val == null && this.next == null;
    }

    /**
     * Create the reversed immutable stack.
     * Time complexity: O(N).
     *
     * @return The reversed stack.
     */
    @Override
    public Stack<T> reverse() {
        if (this.isEmpty()) {
            return this;
        }
        Stack<T> reversed = new LinkedImmutableStack<>();
        Stack<T> current = this;
        while (!current.isEmpty()) {
            reversed = reversed.push(current.peek());
            current = current.pop();
        }
        return reversed;
    }
}
