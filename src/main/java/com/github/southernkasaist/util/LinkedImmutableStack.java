package com.github.southernkasaist.util;

import java.util.NoSuchElementException;

final class LinkedImmutableStack<T> implements Stack<T> {
    private final T val;
    private final Stack<T> next;

    LinkedImmutableStack() {
        this.val = null;
        this.next = null;
    }

    private LinkedImmutableStack(T val, Stack<T> next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public Stack<T> push(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Null element is not allowed.");
        }
        return new LinkedImmutableStack<>(t, this);
    }

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
