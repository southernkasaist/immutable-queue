package com.github.southernkasaist.util;

import java.util.NoSuchElementException;

final class StackImmutableQueue<T> implements Queue<T> {
    private final Stack<T> out;
    private final Stack<T> in;

    StackImmutableQueue() {
        this(new LinkedImmutableStack<>(), new LinkedImmutableStack<>());
    }

    private StackImmutableQueue(Stack<T> out, Stack<T> in) {
        this.out = out;
        this.in = in;
    }

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
