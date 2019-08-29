package com.github.southernkasaist.util;

import java.util.NoSuchElementException;

/**
 * The immutable stack interface which supports operations on stacks
 * without making changes to original instance by creating new instance.
 *
 * @param <T> The underlying type of element.
 */
public interface Stack<T> {
    /**
     * Create a new immutable stack contains all elements of
     * original stack and the new element on top of others.
     *
     * @param t New element, non-null.
     *
     * @return A new stack contains elements with new element on top of others.
     *
     * @throws IllegalArgumentException If the specified element is null.
     */
    Stack<T> push(T t);

    /**
     * Create a new immutable stack contains all elements of
     * original stack except for the top element.
     *
     * @return A new stack contains elements without the top element.
     *
     * @throws NoSuchElementException If original stack is empty. Use {@link #isEmpty()} to check beforehand.
     */
    Stack<T> pop();

    /**
     * Return the top element of stack.
     *
     * @return Top element, non-null.
     *
     * @throws NoSuchElementException If stack is empty. Use {@link #isEmpty()} to check beforehand.
     */
    T peek();

    /**
     * Check if stack is empty.
     *
     * @return {@code true} if the stack has no element.
     */
    boolean isEmpty();

    /**
     * Create a new stack with elements in reverse order compared to original stack.
     *
     * @return A new stack with elements in reverse order.
     */
    Stack<T> reverse();
}
