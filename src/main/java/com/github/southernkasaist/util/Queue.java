package com.github.southernkasaist.util;

import java.util.NoSuchElementException;

/**
 * The immutable queue interface which supports operations on queues
 * without making changes to original instance by creating new instance.
 *
 * @param <T> The underlying type of element.
 */
public interface Queue<T> {
    /**
     * Create a new immutable queue contains all elements of
     * original queue and the new element as the tail.
     *
     * @param t New element, non-null.
     *
     * @return A new queue contains elements with new element as the tail.
     *
     * @throws IllegalArgumentException If the specified element is null.
     */
    Queue<T> enQueue(T t);

    /**
     * Create a new immutable queue contains all elements of
     * original queue except for the head element.
     *
     * @return A new queue contains elements without the head element.
     *
     * @throws NoSuchElementException If original queue is empty. Use {@link #isEmpty()} to check beforehand.
     */
    Queue<T> deQueue();

    /**
     * Return the head element of queue.
     *
     * @return Head element, non-null.
     *
     * @throws NoSuchElementException If queue is empty. Use {@link #isEmpty()} to check beforehand.
     */
    T head();

    /**
     * Check if queue is empty.
     *
     * @return {@code true} if the queue has no element.
     */
    boolean isEmpty();
}
